package com.shahid.iqbal.realmnotes.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shahid.iqbal.realmnotes.model.Note
import org.mongodb.kbson.ObjectId
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by shahid-iqbal on 2/4/25
 */

@Composable
fun AllNotes(notesViewModel: NotesViewModel, modifier: Modifier = Modifier) {

    val allNotes by notesViewModel.allNotes.collectAsStateWithLifecycle()
    var isExpanded by remember { mutableStateOf(false) }

    if (allNotes.isEmpty()) {
        NoNotesPlaceholder()
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {

            items(allNotes, key = { it._id.toHexString() }) {
                SingleNotesItem(note = it, isExpanded,
                    onClick = { isExpanded = !isExpanded },
                    onEdit = {
                        notesViewModel.updateCurrentEditNote(it)
                        notesViewModel.updateShowNotesDialog()
                    },
                    onDelete = { notesViewModel.deleteNote(it) })
            }
        }
    }

}


@Composable
fun NoNotesPlaceholder(modifier: Modifier = Modifier) {

    Column(
        modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "⃝⃝⃝⃝⃝⃝⃝⃝⃝⃝⃝")
        Spacer(Modifier.height(5.dp))
        Text(text = "No Notes Added Yet!!", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun SingleNotesItem(
    note: Note, isExpanded: Boolean, onClick: () -> Unit, onEdit: () -> Unit, onDelete: () -> Unit
) {
    ElevatedCard(
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(horizontal = 10.dp)
            .border(
                width = 2.dp, color = MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(5.dp)
            ), shape = RoundedCornerShape(5.dp)
    ) {

        Row {
            Text(
                text = note.title, style = MaterialTheme.typography.titleMedium, modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp, vertical = 10.dp)

            )

            Text(
                text = convertObjectIdToDateTime(note._id),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            )

            IconButton(onEdit) {
                Icon(
                    imageVector = Icons.Default.Edit, contentDescription = null,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)

                )
            }

            IconButton(onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete, contentDescription = null,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)

                )
            }
        }

        Text(
            text = note.description, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 10.dp), maxLines = if (isExpanded) Int.MAX_VALUE else 2
        )

    }

}




fun convertObjectIdToDateTime(objectId: ObjectId): String {
    val timestamp = objectId.timestamp * 1000L // Convert seconds to milliseconds
    val date = Date(timestamp) // Convert to Date object
    val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault()) // Define format
    return formatter.format(date) // Format and return
}
