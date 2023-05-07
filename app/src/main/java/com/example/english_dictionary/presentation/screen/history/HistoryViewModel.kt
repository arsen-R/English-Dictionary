package com.example.english_dictionary.presentation.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.english_dictionary.data.repository.WordRepositoryImpl
import com.example.english_dictionary.domain.model.WordSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: WordRepositoryImpl
) : ViewModel() {
    private val supervisorJob = MutableStateFlow<Job?>(null)
    private val _uiState = MutableStateFlow<List<WordSearch>>(emptyList())
    val uiState = _uiState.asStateFlow()

    init {
        getAllRecentSearchedWords()
    }

    private fun getAllRecentSearchedWords() {
        viewModelScope.launch {
            repository.getAllRecentSearchedWords().collectLatest { words ->
                _uiState.value = words
            }
        }
    }

    fun addLatestSearchedWord(wordSearch: WordSearch) {
        val job = viewModelScope.launch {
            repository.addRecentSearchedWord(wordSearch = wordSearch)
        }
        supervisorJob.value = job
        job.invokeOnCompletion {
            supervisorJob.value
        }
    }

    fun deleteAllSearchedWord() {
        viewModelScope.launch {
            repository.deleteAllSearchedWord()
        }
    }
}

sealed interface HistoryUiState {
    object Loading : HistoryUiState
    data class Success(val words: List<WordSearch>) : HistoryUiState
    object Idle : HistoryUiState
}