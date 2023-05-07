package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ThesaurusLinkDto(
    @SerializedName("entry_id")
    @Expose val entry_id: String? = null,
    @SerializedName("sense_id")
    @Expose val sense_id: String? = null,
)