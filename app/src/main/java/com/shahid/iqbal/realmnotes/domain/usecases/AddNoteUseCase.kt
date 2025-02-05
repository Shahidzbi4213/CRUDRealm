package com.shahid.iqbal.realmnotes.domain.usecases

import com.shahid.iqbal.realmnotes.domain.NotesRepo
import com.shahid.iqbal.realmnotes.model.Note

/**
 * Created by shahid-iqbal on 2/4/25
 */

class AddNoteUseCase(private val notesRepo: NotesRepo) {

    suspend operator fun invoke(note: Note) {
        notesRepo.addNote(note)
    }
}