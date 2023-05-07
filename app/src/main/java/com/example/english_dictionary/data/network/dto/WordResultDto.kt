package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WordResultDto(
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("language")
    @Expose
    val language: String? = null,
    @SerializedName("lexicalEntries")
    @Expose
    val lexicalEntries: List<LexicalEntryDto>? = null,
    @SerializedName("type")
    @Expose
    val type: String? = null,
    @SerializedName("wor")
    @Expose
    val word: String? = null,
)