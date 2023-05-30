package com.example.english_dictionary.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.english_dictionary.domain.model.WordMetadata
import org.jetbrains.annotations.NotNull
import java.util.Date

@Entity(tableName = "word_entity")
data class WordEntity(
    @PrimaryKey
    @NotNull
    val id: String,
    val word: String? = null,
    val addDate: Date = Date()
)
