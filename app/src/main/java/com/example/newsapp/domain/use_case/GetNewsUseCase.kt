package com.example.newsapp.domain.use_case

import com.example.newsapp.data.model.NewResponse
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
)  {
    suspend operator fun invoke() : List<NewResponse.Article> {
        return repository.fetchNews()
    }
}