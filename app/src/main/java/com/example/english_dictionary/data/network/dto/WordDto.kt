package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WordDto(
    @SerializedName("id")
    @Expose val id: String? = null,
    @SerializedName("metadata")
    @Expose val metadata: WordMetadataDto? = null,
    @SerializedName("results")
    @Expose val results: List<WordResultDto>? = null,
    @SerializedName("word")
    @Expose val word: String? = null,
)