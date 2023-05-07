package com.example.english_dictionary.domain.util

import androidx.compose.ui.res.stringArrayResource
import com.example.english_dictionary.R
import com.example.english_dictionary.presentation.screen.settings.SettingsViewModel
import java.util.Locale

fun String.capitalize(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
        else it.toString()
    }
}


