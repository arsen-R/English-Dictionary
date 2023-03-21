package com.example.english_dictionary.data.network

import com.example.english_dictionary.data.network.dto.WordSearchResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WordService {
    @GET("search/en-us")
    suspend fun getSearchWord(
        @Query("q") query: String
    ): WordSearchResultDto
}