package com.example.english_dictionary.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}