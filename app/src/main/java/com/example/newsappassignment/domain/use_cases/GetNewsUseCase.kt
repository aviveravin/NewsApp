package com.example.newsappassignment.domain.use_cases

import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
)  {
    suspend operator fun invoke() : List<NewsResponse.Article> {
        return repository.fetchNews()
    }
}