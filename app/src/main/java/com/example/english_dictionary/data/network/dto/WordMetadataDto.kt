package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WordMetadataDto(
    @SerializedName("operation")
    @Expose
    val operation: String? = null,
    @SerializedName("provider")
    @Expose
    val provider: String? = null,
    @SerializedName("schema")
    @Expose
    val schema: String? = null,
)