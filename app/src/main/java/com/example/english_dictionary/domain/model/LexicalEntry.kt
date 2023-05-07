package com.example.english_dictionary.domain.model

data class LexicalEntry(
    val entries: List<Entry>? = null,
    val language: String? = null,
    val lexicalCategory: LexicalCategory? = null,
    val phrasalVerbs: List<PhrasalVerb>? = null,
    val phrases: List<Phrase>? = null,
    val text: String? = null,
)