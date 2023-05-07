package com.example.english_dictionary.data.network

import com.example.english_dictionary.data.network.dto.WordDto
import com.example.english_dictionary.data.network.dto.WordSearchResultDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WordService {
    @GET("search/en-us")
    suspend fun getSearchWord(
        @Query("q") query: String
    ): WordSearchResultDto

    @GET("entries/en-us/{wordId}")
    suspend fun getWordDetail(
        @Path("wordId") wordId: String
    ): WordDto

    @GET("thesaurus/en-us/{wordId}")
    suspend fun getThesaurusWord(
        @Path("wordId") wordId: String
    ): WordDto
}