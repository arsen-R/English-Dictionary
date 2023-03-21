package com.example.english_dictionary.data.mapper

import com.example.english_dictionary.data.database.entity.WordSearchEntity
import com.example.english_dictionary.data.network.dto.*
import com.example.english_dictionary.domain.model.*

fun MetadataDto.toMetadata(): Metadata {
    return Metadata(
        limit = this.limit,
        offset = this.offset,
        operation = this.operation,
        provider = this.provider,
        schema = this.schema,
        sourceLanguage = this.sourceLanguage,
        total = this.total
    )
}

fun WordSearchDto.toWordSearch(): WordSearch {
    return WordSearch(
        id = this.id,
        label = this.label,
        matchString = this.matchString,
        matchType = this.matchType,
        region = this.region,
        score = this.score,
        word = this.word
    )
}

fun WordSearchEntity.toWordSearch(): WordSearch {
    return WordSearch(
        id = this.id,
        label = this.label,
        matchString = this.matchString,
        matchType = this.matchType,
        region = this.region,
        score = this.score,
        word = this.word
    )
}

fun WordSearch.toWordSearchEntity(): WordSearchEntity {
    return WordSearchEntity(
        id = this.id!!,
        label = this.label,
        matchString = this.matchString,
        matchType = this.matchType,
        region = this.region,
        score = this.score,
        word = this.word
    )
}

fun WordSearchResultDto.toWordSearchResult(): WordSearchResult {
    return WordSearchResult(
        metadata = this.metadata?.toMetadata(),
        results = this.results?.map { it?.toWordSearch() }
    )
}