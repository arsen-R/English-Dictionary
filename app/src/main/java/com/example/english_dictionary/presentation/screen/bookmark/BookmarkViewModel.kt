package com.example.english_dictionary.presentation.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.english_dictionary.data.repository.WordRepositoryImpl
import com.example.english_dictionary.domain.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: WordRepositoryImpl
) : ViewModel() {
    private val _words = MutableStateFlow<List<Word>>(emptyList())
    val words = _words.asStateFlow()

    init {
        getAllSavedWords()
    }
    
    private fun getAllSavedWords() {
        viewModelScope.launch { 
            repository.getAllSavedWord().collect {
                _words.value = it
            }
        }
    }

    fun removeWord(wordId: String) {
        viewModelScope.launch {
            repository.removeWordEntity(wordId)
        }
    }
}