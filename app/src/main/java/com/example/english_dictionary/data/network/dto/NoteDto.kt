package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NoteDto(
    @SerializedName("text")
    @Expose
    val text: String? = null,
    @SerializedName("type")
    @Expose
    val type: String? = null,
)