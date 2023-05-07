package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LexicalEntryDto(
    @SerializedName("entries")
    @Expose
    val entries: List<EntryDto>? = null,
    @SerializedName("language")
    @Expose
    val language: String? = null,
    @SerializedName("lexicalCategory")
    @Expose
    val lexicalCategory: LexicalCategoryDto? = null,
    @SerializedName("phrasalVerbs")
    @Expose
    val phrasalVerbs: List<PhrasalVerbDto>? = null,
    @SerializedName("phrases")
    @Expose
    val phrases: List<PhraseDto>? = null,
    @SerializedName("text")
    @Expose
    val text: String? = null,
)