package com.example.newsappassignment.data.repository

import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.data.remote.NewsApiService
import com.example.newsappassignment.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService,
) : NewsRepository {
    override suspend fun fetchNews(): List<NewsResponse.Article> {
        return apiService.getHeadlines().articles ?: emptyList()
    }

}