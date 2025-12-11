package com.example.jetpack1.ekrany

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import com.example.jetpack1.sendNote

@Composable
fun Ekran1(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit
) {

    //Wywołuje funkcję tylko raz, gdy ekran jest ładowany
    LaunchedEffect(Unit) {
        sendNote("Ekran 1", "Transfer danych z Ekranu 1 - Test.")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Politechnika Wrocławska",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onNextClick) {
            Text("Przejdź do: Nazwa wydziału")
        }
    }
}