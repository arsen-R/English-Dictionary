package com.example.english_dictionary.data.repository

import com.example.english_dictionary.data.database.dao.DictionaryDao
import com.example.english_dictionary.data.mapper.toWord
import com.example.english_dictionary.data.mapper.toWordSearch
import com.example.english_dictionary.data.mapper.toWordSearchEntity
import com.example.english_dictionary.data.mapper.toWordSearchResult
import com.example.english_dictionary.data.network.WordService
import com.example.english_dictionary.domain.model.WordSearchResult
import com.example.english_dictionary.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.english_dictionary.data.network.result.*
import com.example.english_dictionary.domain.model.Word
import com.example.english_dictionary.domain.model.WordSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

class WordRepositoryImpl @Inject constructor(
    private val wordService: WordService,
    private val dao: DictionaryDao
) : WordRepository {
    override suspend fun getSearchWord(query: String): Flow<Results<WordSearchResult>> = flow {
        try {
            emit(Results.Loading)
            delay(3000)
            val result = wordService.getSearchWord(query).toWordSearchResult()
            emit(Results.Success(result))
        } catch (e: HttpException) {
            emit(Results.Error(e))
        } catch (e: IOException) {
            emit(Results.Error(e))
        } catch (e: RuntimeException) {
            throw e
        } catch (e: Exception) {
            emit(Results.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getRecentSearchedWord() =
        dao.getRecentSearchedWord().map { entities -> entities.map { it.toWordSearch() }}.flowOn(Dispatchers.IO)


    override fun getAllRecentSearchedWords() =
        dao.getAllRecentSearchedWords().map { entities -> entities.map { it.toWordSearch() } }. flowOn(Dispatchers.IO)

    override suspend fun addRecentSearchedWord(wordSearch: WordSearch) {
        dao.addRecentWordSearch(wordSearch = wordSearch.toWordSearchEntity())
    }

    override fun getWordDetail(wordId: String): Flow<Results<Word>>  = flow {
        try {
            emit(Results.Loading)
            val result = wordService.getWordDetail(wordId).toWord()
            emit(Results.Success(result))
        } catch (e: HttpException) {
            emit(Results.Error(e))
        } catch (e: IOException) {
            emit(Results.Error(e))
        } catch (e: RuntimeException) {
            throw e
        } catch (e: Exception) {
            emit(Results.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getThesaurusWord(wordId: String): Flow<Results<Word>> = flow {
        try {
            emit(Results.Loading)
            val result = wordService.getThesaurusWord(wordId).toWord()
            emit(Results.Success(result))
        } catch (e: HttpException) {
            emit(Results.Error(e))
        } catch (e: IOException) {
            emit(Results.Error(e))
        } catch (e: RuntimeException) {
            throw e
        } catch (e: Exception) {
            emit(Results.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteAllSearchedWord() {
        dao.deleteAllWordSearch()
    }
}