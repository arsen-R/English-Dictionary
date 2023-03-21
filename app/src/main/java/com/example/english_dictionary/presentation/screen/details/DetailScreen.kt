package com.example.english_dictionary.presentation.screen.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(
    wordId: String
) {
    Text(text = wordId)
}