package com.example.jetpack1

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore
// ⬅️ KLUCZOWE IMPORTY DO NAPRAWY BŁĘDÓW awaitClose i trySend
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
// -----------------------------------------------------------------


data class Note(
    val title: String = "",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

// Bazy danych
val db = Firebase.firestore
const val TAG = "FIREBASE_TRANSFER"


fun sendNote(noteTitle: String, noteContent: String) {
    val newNote = Note(
        title = noteTitle,
        content = noteContent,
        timestamp = System.currentTimeMillis()
    )

    db.collection("network_transfer_notes")
        .add(newNote)
        .addOnSuccessListener { documentReference ->
            Log.d(TAG, "Sukces! Dokument dodany z ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Błąd podczas dodawania dokumentu", e)
        }
}

// DANE W CZASIE RZECZYWISTYM
fun getNotesFlow() = callbackFlow<List<Note>> {

    val listenerRegistration = db.collection("network_transfer_notes")
        .orderBy("timestamp")
        .addSnapshotListener { snapshot, e ->

            if (e != null) {
                Log.w(TAG, "Błąd nasłuchu danych: ", e)
                trySend(emptyList()) // ⬅️ Działa po dodaniu importu kotlinx.coroutines.channels.awaitClose
                close(e) // if bład
                return@addSnapshotListener
            }

            if (snapshot != null) {
                val notes = snapshot.documents.mapNotNull { document ->
                    document.toObject(Note::class.java)
                }
                trySend(notes) // ⬅️ Wysyłanie danych do UI
            }
        }

    //
    awaitClose {
        listenerRegistration.remove()
    }
}