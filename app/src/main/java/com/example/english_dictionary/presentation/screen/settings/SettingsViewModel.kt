package com.example.english_dictionary.presentation.screen.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.english_dictionary.domain.repository.SettingsRepository
import com.example.english_dictionary.domain.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val supervisorJob = MutableStateFlow<Job?>(null)

    private val _selectedTheme = MutableStateFlow<Int?>(0);
    val selectedTheme = _selectedTheme.asStateFlow()

    private val _selectedSourceLanguage = MutableStateFlow<Int?>(0)
    val selectedSourceLanguage = _selectedSourceLanguage.asStateFlow()

    init {
        getThemePreferences()
        getSourceLanguagePreferences()
    }

    fun savePreferencesSelection(key: String, selection: Int) {
        val job = viewModelScope.launch {
            settingsRepository.savePreferenceSelection(key = key, selection = selection)
        }
        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    private fun getThemePreferences() {
        val job = viewModelScope.launch {
            settingsRepository.getThemePreferences().collectLatest {
                _selectedTheme.value = it
            }
        }
        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }

    private fun getSourceLanguagePreferences() {
        val job = viewModelScope.launch {
            settingsRepository.getSourceLanguagePreferences().collectLatest {
                _selectedSourceLanguage.value = it
                savedStateHandle[Constant.SOURCE_LANG_ARG_KEY] = it
            }
        }
        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value = null
        }
    }
}