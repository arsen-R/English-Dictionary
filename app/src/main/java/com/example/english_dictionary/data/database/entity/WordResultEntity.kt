package com.example.english_dictionary.data.database.entity

import androidx.room.Entity
import com.example.english_dictionary.domain.model.LexicalEntry

//@Entity(tableName = "word_result_table")
data class WordResultEntity(
    val id: String? = null,
    val language: String? = null,
    //val lexicalEntries: List<LexicalEntry>? = null,
    val type: String? = null,
    val word: String? = null,
)
