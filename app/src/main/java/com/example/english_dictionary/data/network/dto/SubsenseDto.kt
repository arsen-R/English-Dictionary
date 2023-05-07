package com.example.english_dictionary.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubsenseDto(
    @SerializedName("antonyms")
    @Expose
    val antonyms: List<AntonymDto>? = null,
    @SerializedName("definitions")
    @Expose
    val definitions: List<String>? = null,
    @SerializedName("domainClasses")
    @Expose
    val domainClasses: List<DomainClasseDto>? = null,
    @SerializedName("examples")
    @Expose
    val examples: List<ExampleDto>? = null,
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("notes")
    @Expose
    val notes: List<NoteDto>? = null,
    @SerializedName("regions")
    @Expose
    val regions: List<RegionDto>? = null,
    @SerializedName("registers")
    @Expose
    val registers: List<RegisterDto>? = null,
    @SerializedName("shortDefinitions")
    @Expose
    val shortDefinitions: List<String>? = null,
    @SerializedName("synonyms")
    @Expose
    val synonyms: List<SynonymDto>? = null,
    @SerializedName("thesaurusLinks")
    @Expose
    val thesaurusLinks: List<ThesaurusLinkDto>? = null,
    @SerializedName("variantForms")
    @Expose
    val variantForms: List<VariantFormDto>? = null,
)