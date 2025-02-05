package com.shahid.iqbal.realmnotes.domain

import com.shahid.iqbal.realmnotes.model.Note
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

/**
 * Created by shahid-iqbal on 2/4/25
 */
interface NotesRepo {

    suspend fun addNote(note: Note)

    suspend fun updateNote(id: ObjectId, title: String, description: String)

    fun readNotes(): Flow<List<Note>>

    suspend fun deleteNote(note: Note)

    suspend fun deleteAllNotes()
}