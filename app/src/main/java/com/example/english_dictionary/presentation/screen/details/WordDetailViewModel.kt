package com.example.english_dictionary.presentation.screen.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.english_dictionary.data.network.result.Results
import com.example.english_dictionary.data.repository.WordRepositoryImpl
import com.example.english_dictionary.domain.model.Word
import com.example.english_dictionary.presentation.navigation.WordArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    private val repository: WordRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _definitionUiState = MutableStateFlow<DefinitionUiState>(DefinitionUiState.Loading)
    val definitionUiState = _definitionUiState.asStateFlow()

    private val _thesaurusUiState = MutableStateFlow<ThesaurusUiState>(ThesaurusUiState.Loading)
    val thesaurusUiState = _thesaurusUiState.asStateFlow()

    private val wordArgs: WordArgs = WordArgs(savedStateHandle)

    init {
        getDefinitionWord()
        getThesaurusWord()
    }

    fun getDefinitionWord() {
        viewModelScope.launch {
            repository.getWordDetail(
                wordId = wordArgs.wordId
            ).collectLatest { result ->
                _definitionUiState.update {
                    when (result) {
                        is Results.Loading -> {
                            DefinitionUiState.Loading
                        }

                        is Results.Success -> {
                            DefinitionUiState.Success(result.data)
                        }

                        is Results.Error -> {
                            DefinitionUiState.Error(result.throwable)
                        }
                    }
                }
            }
        }
    }

    fun getThesaurusWord() {
        viewModelScope.launch {
            repository.getThesaurusWord(wordId = wordArgs.wordId).collectLatest { result ->
                _thesaurusUiState.update {
                    when(result) {
                        is Results.Loading -> {
                            ThesaurusUiState.Loading
                        }
                        is Results.Success -> {
                            ThesaurusUiState.Success(result.data)
                        }
                        is Results.Error -> {
                            ThesaurusUiState.Error(result.throwable)
                        }
                    }
                }
            }
        }
    }

    fun addWord(word: Word) {
        viewModelScope.launch {
            repository.addWordEntity(word)
        }
    }

    fun removeWord(wordId: String) {
        viewModelScope.launch {
            repository.removeWordEntity(wordId)
        }
    }

    fun isSavedWord(wordId: String): Boolean {
        return repository.isWordSaved(wordId)
    }
}

sealed interface DefinitionUiState {
    object Loading : DefinitionUiState
    data class Success(val word: Word) : DefinitionUiState
    data class Error(val throwable: Throwable) : DefinitionUiState
}

sealed interface ThesaurusUiState {
    object Loading : ThesaurusUiState
    data class Success(val word: Word) : ThesaurusUiState
    data class Error(val throwable: Throwable) : ThesaurusUiState
}