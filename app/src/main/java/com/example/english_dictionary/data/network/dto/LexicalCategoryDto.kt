package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LexicalCategoryDto(
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("text")
    @Expose
    val text: String? = null,
)