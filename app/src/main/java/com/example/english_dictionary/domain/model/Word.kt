package com.example.english_dictionary.domain.model

data class Word(
    val id: String? = null,
    val metadata: WordMetadata? = null,
    val results: List<WordResult>? = null,
    val word: String? = null,
)