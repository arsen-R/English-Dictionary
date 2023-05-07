package com.example.english_dictionary.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.english_dictionary.domain.repository.SettingsRepository
import com.example.english_dictionary.domain.util.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {
    override suspend fun savePreferenceSelection(key: String, selection: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = selection
        }
    }

    override suspend fun getThemePreferences(): Flow<Int?> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { preference ->
            preference[intPreferencesKey(Constant.THEME_PREFERENCES_KEY)] ?: 0
        }
    }

    override suspend fun getSourceLanguagePreferences(): Flow<Int?> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { preference ->
            preference[intPreferencesKey(Constant.SOURCE_LANGUAGE_KEY)] ?: 0
        }
    }
}