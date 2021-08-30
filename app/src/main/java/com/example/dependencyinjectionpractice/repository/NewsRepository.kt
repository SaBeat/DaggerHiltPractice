package com.example.dependencyinjectionpractice.repository

import com.example.dependencyinjectionpractice.api.ApiInterface
import javax.inject.Inject


class NewsRepository @Inject constructor(private val api:ApiInterface) {

    suspend fun getApi(apiKey:String,country:String,category:String)=
        api.getHeadlines(apiKey,country,category)
}