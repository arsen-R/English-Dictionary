package com.example.english_dictionary.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.english_dictionary.data.database.entity.WordEntity
import com.example.english_dictionary.data.database.entity.WordSearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM word_search_table ORDER BY date DESC LIMIT 20")
    fun getRecentSearchedWord(): Flow<List<WordSearchEntity>>

    @Query("SELECT * FROM word_search_table ORDER BY date DESC")
    fun getAllRecentSearchedWords(): Flow<List<WordSearchEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecentWordSearch(wordSearch: WordSearchEntity): Long

    @Query("DELETE FROM word_search_table")
    suspend fun deleteAllWordSearch()

    @Query("SELECT * FROM word_entity ORDER BY addDate DESC")
    fun getAllSavedWord(): Flow<List<WordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWordEntity(wordEntity: WordEntity): Long

    @Query("SELECT EXISTS (SELECT 1 FROM word_entity WHERE id = :wordId)")
    fun isWordSaved(wordId: String): Boolean

    @Query("DELETE FROM word_entity WHERE id = :wordId")
    suspend fun deleteWord(wordId: String)
}