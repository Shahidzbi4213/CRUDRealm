package com.shahid.iqbal.realmnotes.domain.usecases

import com.shahid.iqbal.realmnotes.domain.NotesRepo

/**
 * Created by shahid-iqbal on 2/5/25
 */
class DeleteAllNotesUseCase(private val notesRepo: NotesRepo) {

    suspend operator fun invoke() {
        notesRepo.deleteAllNotes()
    }
}