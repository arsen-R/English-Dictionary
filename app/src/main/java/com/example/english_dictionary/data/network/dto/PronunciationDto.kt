package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PronunciationDto(
    @SerializedName("audioFile")
    @Expose val audioFile: String? = null,
    @SerializedName("dialects")
    @Expose val dialects: List<String>? = null,
    @SerializedName("phoneticNotation")
    @Expose val phoneticNotation: String? = null,
    @SerializedName("phoneticSpelling")
    @Expose val phoneticSpelling: String? = null,
)