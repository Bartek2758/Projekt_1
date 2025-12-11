package com.example.jetpack1.ekrany

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Ekran3(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Inteligentna Elektronika",
            style = MaterialTheme.typography.headlineMedium
        )


        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBackClick) {
            Text("Wróć do: Nazwa uczelni")
        }
    }
}


