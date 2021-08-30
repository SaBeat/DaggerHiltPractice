package com.example.dependencyinjectionpractice.api

import com.example.dependencyinjectionpractice.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("apiKey") apiKey:String,
        @Query("country") country:String,
        @Query("category") category:String
    ): Response<NewsResponse>
}