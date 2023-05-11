package com.example.english_dictionary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.english_dictionary.data.database.dao.DictionaryDao
import com.example.english_dictionary.data.database.entity.WordEntity
import com.example.english_dictionary.data.database.entity.WordSearchEntity

@Database(
    entities = [WordSearchEntity::class, WordEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(DateConverters::class)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}