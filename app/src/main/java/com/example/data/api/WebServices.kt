package com.example.data.api

import com.example.data.api.model.newsResponse.NewsResponse
import com.example.data.api.model.sourceResponse.SourcesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("v2/top-headlines/sources")
   suspend fun getSources(
        @Query("apiKey") key : String = ApiConstants.API_KEY
    ) : SourcesResponse

    @GET("v2/everything")
    suspend fun getNews(
        @Query("apiKey") key : String = ApiConstants.API_KEY,
        @Query("sources") sources : String,
    ) : NewsResponse

}