package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.NewResponse

interface NewsRepository {
    suspend fun fetchNews(): List<NewResponse.Article>
}