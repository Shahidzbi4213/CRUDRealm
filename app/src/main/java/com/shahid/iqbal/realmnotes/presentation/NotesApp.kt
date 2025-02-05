package com.shahid.iqbal.realmnotes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Created by shahid-iqbal on 2/4/25
 */

@Composable
fun NotesApp(modifier: Modifier = Modifier) {

    val notesViewModel = viewModel<NotesViewModel>(factory = NotesViewModelFactory())
    val allNotes by notesViewModel.allNotes.collectAsStateWithLifecycle()
    val showNoteDialog by notesViewModel.showNotesDialog.collectAsStateWithLifecycle()
    val currentEditNote by notesViewModel.currentEditNote.collectAsStateWithLifecycle()


    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 30.dp)
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Notes App", modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight(),
                    style = MaterialTheme.typography.titleLarge
                        .copy(fontStyle = FontStyle.Italic)
                )

                TextButton(
                    onClick = notesViewModel::deleteAllNotes,
                    enabled = allNotes.isNotEmpty()
                ) {

                    Text(text = "Delete All")
                }
            }
        },
        floatingActionButton = {
            AddNotesButton(onCLick = notesViewModel::updateShowNotesDialog)
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { innerPadding ->

        AllNotes(
            notesViewModel = notesViewModel,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .windowInsetsPadding(WindowInsets.ime)
        )


        if (showNoteDialog) {
            NoteDialog(
                note = currentEditNote,
                onDismiss = notesViewModel::updateShowNotesDialog,
                onAddNewNote = { title, description ->
                    notesViewModel.addNote(title, description)
                    notesViewModel.updateShowNotesDialog()
                },
                onUpdatedNote = { id, title, description ->
                    notesViewModel.updateNote(id, title, description)
                    notesViewModel.updateShowNotesDialog()
                    notesViewModel.updateCurrentEditNote(null)
                }
            )
        }
    }
}

@Composable
private fun AddNotesButton(modifier: Modifier = Modifier, onCLick: () -> Unit) {

    SmallFloatingActionButton(
        onClick = onCLick,
        modifier = modifier,
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}