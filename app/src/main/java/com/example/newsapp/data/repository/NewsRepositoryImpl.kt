package com.example.newsapp.data.repository

import com.example.newsapp.data.model.NewResponse
import com.example.newsapp.data.remote.NewsApiService
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
 private val apiService: NewsApiService
) : NewsRepository{
    override suspend fun fetchNews(): List<NewResponse.Article> {
        return  apiService.getHeadlines().articles
    }
}