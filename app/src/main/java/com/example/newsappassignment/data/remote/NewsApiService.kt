package com.example.newsappassignment.data.remote

import com.example.newsappassignment.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String = "us",
        @Query("apikey") apikey: String = "a424e393ef074371bfb16041b72d9f1d"
    ): NewsResponse
}