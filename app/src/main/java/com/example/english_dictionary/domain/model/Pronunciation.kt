package com.example.english_dictionary.domain.model

data class Pronunciation(
    val audioFile: String? = null,
    val dialects: List<String>? = null,
    val phoneticNotation: String? = null,
    val phoneticSpelling: String? = null,
)