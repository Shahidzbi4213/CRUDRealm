package com.shahid.iqbal.realmnotes.domain.usecases

import com.shahid.iqbal.realmnotes.domain.NotesRepo

/**
 * Created by shahid-iqbal on 2/4/25
 */
class ReadNotesUseCase(private val notesRepo: NotesRepo) {

   operator fun invoke() = notesRepo.readNotes()
}