package com.example.english_dictionary.domain.repository

import com.example.english_dictionary.domain.model.WordSearchResult
import kotlinx.coroutines.flow.Flow
import com.example.english_dictionary.data.network.result.*
import com.example.english_dictionary.domain.model.Word
import com.example.english_dictionary.domain.model.WordSearch

interface WordRepository {
    suspend fun getSearchWord(query: String): Flow<Results<WordSearchResult>>

    fun getRecentSearchedWord() : Flow<List<WordSearch>>

    fun getAllRecentSearchedWords(): Flow<List<WordSearch>>

    suspend fun addRecentSearchedWord(wordSearch: WordSearch)

    fun getWordDetail(wordId: String): Flow<Results<Word>>

    fun getThesaurusWord(wordId: String): Flow<Results<Word>>

    suspend fun deleteAllSearchedWord()
}