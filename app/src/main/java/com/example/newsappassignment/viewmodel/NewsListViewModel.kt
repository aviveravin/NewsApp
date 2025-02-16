package com.example.newsappassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.domain.repository.NewsRepository
import com.example.newsappassignment.domain.use_cases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase,
    private val repository: NewsRepository
) : ViewModel() {

    private val _newsList = MutableStateFlow<List<NewsResponse.Article>>(emptyList())
    val newList: StateFlow<List<NewsResponse.Article>> = _newsList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            _newsList.value = newsUseCase()
        }
    }
}