package com.example.newsappassignment.data.repository

import com.example.newsappassignment.data.local.ArticleDao
import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.data.model.SavedArticle
import com.example.newsappassignment.data.remote.NewsApiService
import com.example.newsappassignment.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService,
    private val articleDao: ArticleDao
) : NewsRepository {

    override suspend fun fetchNews(): List<NewsResponse.Article> {
        return apiService.getHeadlines().articles ?: emptyList()
    }

    override fun saveArticle(article: SavedArticle) {
        articleDao.insertArticle(article)
    }

    override fun deleteArticle(article: SavedArticle) {
        articleDao.deleteArticle(article)
    }

    override fun getAllSavedArticles(): Flow<List<SavedArticle>> {
        return articleDao.getAllSavedArticles()
    }
}
