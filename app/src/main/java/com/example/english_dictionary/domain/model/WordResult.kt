package com.example.english_dictionary.domain.model

data class WordResult(
    val id: String? = null,
    val language: String? = null,
    val lexicalEntries: List<LexicalEntry>? = null,
    val type: String? = null,
    val word: String? = null,
)