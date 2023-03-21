package com.example.english_dictionary.domain.model

data class WordSearchResult(
    val metadata: Metadata? = null,
    val results: List<WordSearch?>? = null,
)