package com.example.english_dictionary.presentation.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.english_dictionary.data.repository.WordRepositoryImpl
import com.example.english_dictionary.domain.model.WordSearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.english_dictionary.data.network.result.*
import com.example.english_dictionary.domain.model.WordSearch
import kotlinx.coroutines.flow.*

@HiltViewModel
class WordSearchViewModel @Inject constructor(
    private val repository: WordRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery
    private val _isSearched = mutableStateOf(false)
    val isSearched: State<Boolean> = _isSearched
    private val _recentSearchWord = MutableStateFlow<List<WordSearch>>(emptyList())
    val recentSearchWord = _recentSearchWord.asStateFlow()

    private var job: Job? = null

    init {
        getRecentSearchedWord()
    }
    fun onSearch() {

        job?.cancel()
        job = viewModelScope.launch {
            repository.getSearchWord(_searchQuery.value).collectLatest { result ->
                _isSearched.value = true
                _uiState.update {
                    when (result) {
                        is Results.Loading -> {
                            SearchUiState.Loading
                        }
                        is Results.Success -> {
                            SearchUiState.Success(result.data)
                        }
                        is Results.Error -> {
                            SearchUiState.Error(result.throwable)
                        }
                    }
                }
            }
        }
    }

    fun getSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun cleanSearchQuery() {
        _searchQuery.value = ""
        _isSearched.value = false
    }

    private fun getRecentSearchedWord() {
        viewModelScope.launch {
            repository.getRecentSearchedWord().collectLatest {
                _recentSearchWord.value = it
            }
        }
    }


    fun addLatestSearchedWord(wordSearch: WordSearch) {
        viewModelScope.launch {
            repository.addRecentSearchedWord(wordSearch = wordSearch)
        }
    }

    override fun onCleared() {
        super.onCleared()
        _isSearched.value = false
        _searchQuery.value = ""
    }
}

sealed interface SearchUiState {
    object Loading: SearchUiState
    data class Success(val words: WordSearchResult): SearchUiState
    data class Error(val throwable: Throwable): SearchUiState
}