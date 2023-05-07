package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InflectionDto(
    @SerializedName("grammaticalFeatures")
    @Expose
    val grammaticalFeatures: List<GrammaticalFeatureDto>? = null,
    @SerializedName("inflectedForm")
    @Expose
    val inflectedForm: String? = null,
    @SerializedName("pronunciations")
    @Expose
    val pronunciations: List<PronunciationDto>? = null,
)