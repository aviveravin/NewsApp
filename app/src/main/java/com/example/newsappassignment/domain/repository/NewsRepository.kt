package com.example.newsappassignment.domain.repository

import com.example.newsappassignment.data.model.NewsResponse

interface NewsRepository {
    suspend fun fetchNews(): List<NewsResponse.Article>
}