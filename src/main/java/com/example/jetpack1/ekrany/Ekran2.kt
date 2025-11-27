package com.example.jetpack1.ekrany

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Ekran2(
    onBackClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Wydział Elektroniki, Fotoniki i Mikrosystemów ",
            style = MaterialTheme.typography.headlineMedium


        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBackClick) {
            Text("Wróć do: Nazwa Uczelni")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNextClick) {
            Text("Przejdź do:  Kierunek studiów")
        }
    }
}
