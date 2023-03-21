package com.example.english_dictionary.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun savePreferenceSelection(key: String, selection: Int)

    suspend fun getThemePreferences(): Flow<Int?>
}