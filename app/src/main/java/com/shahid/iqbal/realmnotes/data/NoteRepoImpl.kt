package com.shahid.iqbal.realmnotes.data

import com.shahid.iqbal.realmnotes.MyApp
import com.shahid.iqbal.realmnotes.domain.NotesRepo
import com.shahid.iqbal.realmnotes.model.Note
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId

/**
 * Created by shahid-iqbal on 2/4/25
 */
class NoteRepoImpl : NotesRepo {

    private val realm = MyApp.realm

    override suspend fun addNote(note: Note) {
        realm.write { copyToRealm(note) }
    }

    override fun readNotes(): Flow<List<Note>> {
        return realm.query<Note>()
            .asFlow()
            .map { result -> result.list }

    }

    override suspend fun deleteNote(note: Note) {
        realm.write {
            val lastNote = findLatest(note) ?: return@write
            delete(lastNote)
        }
    }

    override suspend fun updateNote(id: ObjectId, title: String, description: String) {

        realm.write {
            val queriedNote = query<Note>("_id == $0", id).find().first()
            queriedNote.apply {
                this.title = title
                this.description = description
            }
        }
    }

    override suspend fun deleteAllNotes() {
        realm.write {
            val notes = query<Note>().find()
            delete(notes)
        }
    }
}