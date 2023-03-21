package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WordSearchDto(
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("label")
    @Expose
    val label: String? = null,
    @SerializedName("matchString")
    @Expose
    val matchString: String? = null,
    @SerializedName("matchType")
    @Expose
    val matchType: String? = null,
    @SerializedName("region")
    @Expose
    val region: String? = null,
    @SerializedName("score")
    @Expose
    val score: Double? = null,
    @SerializedName("word")
    @Expose
    val word: String? = null,
)