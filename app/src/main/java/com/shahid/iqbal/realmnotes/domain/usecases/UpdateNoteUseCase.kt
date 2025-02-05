package com.shahid.iqbal.realmnotes.domain.usecases

import com.shahid.iqbal.realmnotes.domain.NotesRepo
import org.mongodb.kbson.ObjectId

/**
 * Created by shahid-iqbal on 2/5/25
 */
class UpdateNoteUseCase(private val notesRepo:NotesRepo) {

    suspend operator fun invoke(id:ObjectId,title: String, description: String) {
        notesRepo.updateNote(id,title, description)
    }
}