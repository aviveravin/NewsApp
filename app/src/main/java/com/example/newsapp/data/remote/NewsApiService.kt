package com.example.newsapp.data.remote

import com.example.newsapp.data.model.NewResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = "us",
        @Query("apikey") apikey: String = ""
    ): NewResponse
}