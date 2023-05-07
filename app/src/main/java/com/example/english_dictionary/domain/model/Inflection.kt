package com.example.english_dictionary.domain.model

data class Inflection(
    val grammaticalFeatures: List<GrammaticalFeature>? = null,
    val inflectedForm: String? = null,
    val pronunciations: List<Pronunciation>? = null,
)