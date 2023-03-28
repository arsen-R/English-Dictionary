package com.example.english_dictionary.presentation.tabs

import androidx.compose.runtime.Composable

data class TabItem(
    val title:String,
    val screen: @Composable () -> Unit
)
