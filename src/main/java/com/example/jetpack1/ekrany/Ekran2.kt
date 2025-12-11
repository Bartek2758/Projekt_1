package com.example.jetpack1.ekrany

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// ⬅️ Importy z Twojego pliku FirebaseData.kt
import com.example.jetpack1.sendNote
import com.example.jetpack1.getNotesFlow

@Composable
fun Ekran2(
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {

    var nameInput by remember { mutableStateOf("") }


    val notesState by getNotesFlow().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Wydział Elektroniki, Fotoniki i Mikrosystemów",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))



        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Podaj swoje imię") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                if (nameInput.isNotBlank()) {
                    sendNote(
                        noteTitle = nameInput,
                        noteContent = "Wysłane imię." // Uproszczona treść
                    )
                    nameInput = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = nameInput.isNotBlank()
        ) {
            Text("Wyślij Imię do Firebase")
        }

        Spacer(modifier = Modifier.height(24.dp))




        LazyColumn(modifier = Modifier.weight(1f)) {
            items(notesState) { note ->

                Text(
                    text = note.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
            }
        }



        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onBackClick) {
                Text("Wróć do: Nazwa Uczelni")
            }

            Button(onClick = onNextClick) {
                Text("Przejdź do: Kierunek studiów")
            }
        }
    }
}