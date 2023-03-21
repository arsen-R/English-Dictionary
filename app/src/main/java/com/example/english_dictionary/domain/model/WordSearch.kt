package com.example.english_dictionary.domain.model

data class WordSearch(
    val id: String? = null,
    val label: String? = null,
    val matchString: String? = null,
    val matchType: String? = null,
    val region: String? = null,
    val score: Double? = null,
    val word: String? = null,
)