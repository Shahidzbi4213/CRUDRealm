package com.shahid.iqbal.realmnotes.domain.usecases

/**
 * Created by shahid-iqbal on 2/4/25
 */
data class NotesUseCases(
    val addNoteUseCase: AddNoteUseCase,
    val readNoteUseCase: ReadNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val updateNoteUseCase: UpdateNoteUseCase,
    val deleteAllNotesUseCase: DeleteAllNotesUseCase
)