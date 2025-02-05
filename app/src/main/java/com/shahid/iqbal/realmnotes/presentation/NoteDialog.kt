package com.shahid.iqbal.realmnotes.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shahid.iqbal.realmnotes.model.Note
import org.mongodb.kbson.ObjectId

/**
 * Created by shahid-iqbal on 2/4/25
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDialog(
    note: Note?,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onAddNewNote: (String, String) -> Unit,
    onUpdatedNote: (ObjectId, String, String) -> Unit
) {

    var title by remember { mutableStateOf(note?.title ?: "") }
    var description by remember { mutableStateOf(note?.description ?: "") }

    BasicAlertDialog(
        onDismiss, modifier = modifier
            .background(
                CardDefaults.cardColors().containerColor,
                shape = RoundedCornerShape(10.dp)
            )
            .wrapContentSize()
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Notes Dialog",
                color = CardDefaults.cardColors().contentColor,
                style = MaterialTheme
                    .typography.titleLarge,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            Text(
                "Title",
                color = CardDefaults.cardColors().contentColor,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 5.dp),
            )
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                maxLines = 1,
                singleLine = true,
            )

            Spacer(Modifier.height(10.dp))

            Text(
                "Description",
                color = CardDefaults.cardColors().contentColor,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 5.dp),
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                maxLines = 10
            )

            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onDismiss) {
                    Text("Cancel")
                }


                TextButton(onClick = {
                    if (note == null) onAddNewNote(title, description)
                    else onUpdatedNote(note._id, title, description)
                }) {
                    Text(if (note == null) "Add" else "Update")
                }
            }
        }
    }
}