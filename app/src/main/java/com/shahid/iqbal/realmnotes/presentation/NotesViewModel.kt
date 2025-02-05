package com.shahid.iqbal.realmnotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahid.iqbal.realmnotes.domain.usecases.NotesUseCases
import com.shahid.iqbal.realmnotes.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

/**
 * Created by shahid-iqbal on 2/4/25
 */
class NotesViewModel(private val notesUseCases: NotesUseCases) : ViewModel() {

    private var _showNotesDialog = MutableStateFlow(false)
    val showNotesDialog get() = _showNotesDialog.asStateFlow()

    private var _currentEditNote = MutableStateFlow<Note?>(null)
    val currentEditNote get() = _currentEditNote.asStateFlow()


    val allNotes = notesUseCases
        .readNoteUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun addNote(title: String, description: String) {
        viewModelScope.launch {
            val note = Note().apply {
                this.title = title
                this.description = description
            }
            notesUseCases.addNoteUseCase(note)
        }
    }

    fun updateNote(id: ObjectId, title: String, description: String) {
        viewModelScope.launch {
            notesUseCases.updateNoteUseCase(id, title, description)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            notesUseCases.deleteNoteUseCase(note)
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            notesUseCases.deleteAllNotesUseCase()
        }
    }

    fun updateShowNotesDialog() {
        _showNotesDialog.value = !_showNotesDialog.value
    }

    fun updateCurrentEditNote(note: Note?) {
        _currentEditNote.value = note
    }


}