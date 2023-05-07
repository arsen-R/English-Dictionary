package com.example.english_dictionary.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.english_dictionary.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private val _selectedTheme = MutableStateFlow<Int?>(0);
    val selectedTheme = _selectedTheme.asStateFlow()
    private val supervisorJob = MutableStateFlow<Job?>(null)

    init {
        getThemePreferences()
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
}