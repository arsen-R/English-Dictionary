package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WordSearchResultDto(
    @SerializedName("metadata")
    @Expose
    val metadata: MetadataDto? = null,
    @SerializedName("results")
    @Expose
    val results: List<WordSearchDto?>? = null,
)