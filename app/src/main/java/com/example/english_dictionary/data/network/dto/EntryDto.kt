package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EntryDto(
    @SerializedName("etymologies")
    @Expose
    val etymologies: List<String>? = null,
    @SerializedName("grammaticalFeatures")
    @Expose
    val grammaticalFeatures: List<GrammaticalFeatureDto>? = null,
    @SerializedName("inflections")
    @Expose
    val inflections: List<InflectionDto>? = null,
    @SerializedName("notes")
    @Expose
    val notes: List<NoteDto>? = null,
    @SerializedName("pronunciations")
    @Expose
    val pronunciations: List<PronunciationDto>? = null,
    @SerializedName("senses")
    @Expose
    val senses: List<SenseDto>? = null,
)