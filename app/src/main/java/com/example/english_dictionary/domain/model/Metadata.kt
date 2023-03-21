package com.example.english_dictionary.domain.model

data class Metadata(
    val limit: String? = null,
    val offset: String? = null,
    val operation: String? = null,
    val provider: String? = null,
    val schema: String? = null,
    val sourceLanguage: String? = null,
    val total: String? = null,
)