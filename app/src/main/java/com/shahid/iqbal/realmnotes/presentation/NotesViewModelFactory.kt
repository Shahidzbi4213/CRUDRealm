package com.shahid.iqbal.realmnotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shahid.iqbal.realmnotes.data.NoteRepoImpl
import com.shahid.iqbal.realmnotes.domain.NotesRepo
import com.shahid.iqbal.realmnotes.domain.usecases.AddNoteUseCase
import com.shahid.iqbal.realmnotes.domain.usecases.DeleteAllNotesUseCase
import com.shahid.iqbal.realmnotes.domain.usecases.DeleteNoteUseCase
import com.shahid.iqbal.realmnotes.domain.usecases.NotesUseCases
import com.shahid.iqbal.realmnotes.domain.usecases.ReadNotesUseCase
import com.shahid.iqbal.realmnotes.domain.usecases.UpdateNoteUseCase

@Suppress("UNCHECKED_CAST")
class NotesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {

            val notesRepo = NoteRepoImpl()
            val notesUseCases = NotesUseCases(
                addNoteUseCase = AddNoteUseCase(notesRepo = notesRepo),
                readNoteUseCase = ReadNotesUseCase(notesRepo = notesRepo),
                deleteNoteUseCase = DeleteNoteUseCase(notesRepo = notesRepo),
                updateNoteUseCase = UpdateNoteUseCase(notesRepo = notesRepo),
                deleteAllNotesUseCase = DeleteAllNotesUseCase(notesRepo = notesRepo)
            )

            return NotesViewModel(notesUseCases) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
