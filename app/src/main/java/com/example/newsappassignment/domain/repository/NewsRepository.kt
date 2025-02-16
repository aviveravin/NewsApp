package com.example.newsappassignment.domain.repository

import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.data.model.SavedArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchNews(): List<NewsResponse.Article>
    fun saveArticle(article: SavedArticle)
    fun deleteArticle(article: SavedArticle)
    fun getAllSavedArticles(): Flow<List<SavedArticle>>
}
