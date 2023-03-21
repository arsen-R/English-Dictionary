package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MetadataDto(
    @SerializedName("limit")
    @Expose
    val limit: String? = null,
    @SerializedName("offset")
    @Expose
    val offset: String? = null,
    @SerializedName("operation")
    @Expose
    val operation: String? = null,
    @SerializedName("provider")
    @Expose
    val provider: String? = null,
    @SerializedName("schema")
    @Expose
    val schema: String? = null,
    @SerializedName("sourceLanguage")
    @Expose
    val sourceLanguage: String? = null,
    @SerializedName("total")
    @Expose
    val total: String? = null,
)