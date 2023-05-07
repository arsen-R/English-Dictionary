package com.example.english_dictionary.domain.model

data class Entry(
    val etymologies: List<String>? = null,
    val grammaticalFeatures: List<GrammaticalFeature>? = null,
    val inflections: List<Inflection>? = null,
    val notes: List<Note>? = null,
    val pronunciations: List<Pronunciation>? = null,
    val senses: List<Sense>? = null,
)