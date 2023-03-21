package com.example.english_dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.english_dictionary.presentation.screen.main.MainScreen

import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishDictionaryTheme {
                MainScreen()
            }
        }
    }
}