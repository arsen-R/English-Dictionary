package com.example.english_dictionary.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "word_search_table")
data class WordSearchEntity(
    @PrimaryKey
    val id: String,
    val label: String? = null,
    val matchString: String? = null,
    val matchType: String? = null,
    val region: String? = null,
    val score: Double? = null,
    val word: String? = null,
    val date: Date = Date()
) {

}