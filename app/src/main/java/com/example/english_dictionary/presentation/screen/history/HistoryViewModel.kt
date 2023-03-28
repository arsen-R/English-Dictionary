package com.example.english_dictionary.presentation.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.english_dictionary.data.repository.WordRepositoryImpl
import com.example.english_dictionary.domain.model.WordSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: WordRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow<HistoryUiState>(HistoryUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAllRecentSearchedWords()
    }

    private fun getAllRecentSearchedWords() {
        viewModelScope.launch {
            repository.getAllRecentSearchedWords()
                .filterNot { it.isEmpty() }
                .map<List<WordSearch>, HistoryUiState> {
                    HistoryUiState.Success(it)
                }
                .onStart { emit(HistoryUiState.Loading) }
                .collectLatest { state ->
                    _uiState.update {
                        when (state) {
                            is HistoryUiState.Loading -> {
                                HistoryUiState.Loading
                            }
                            is HistoryUiState.Success -> {
                                HistoryUiState.Success(state.words)
                            }
                        }
                    }
                }
        }
    }

    fun addLatestSearchedWord(wordSearch: WordSearch) {
        viewModelScope.launch {
            repository.addRecentSearchedWord(wordSearch = wordSearch)
        }
    }
}

sealed interface HistoryUiState {
    object Loading : HistoryUiState
    data class Success(val words: List<WordSearch>) : HistoryUiState
}