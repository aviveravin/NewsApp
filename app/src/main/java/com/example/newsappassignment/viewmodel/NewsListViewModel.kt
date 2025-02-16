package com.example.newsappassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.data.model.SavedArticle
import com.example.newsappassignment.domain.repository.NewsRepository
import com.example.newsappassignment.domain.use_cases.GetNewsUseCase
import com.example.newsappassignment.util.toSavedArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase,
    private val repository: NewsRepository
) : ViewModel() {

    private val _newsList = MutableStateFlow<List<NewsResponse.Article>>(emptyList())
    val newsList: StateFlow<List<NewsResponse.Article>> = _newsList

    val savedArticles: StateFlow<List<SavedArticle>> =
        repository.getAllSavedArticles().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            _newsList.value = newsUseCase()
        }
    }

    fun saveArticle(article: NewsResponse.Article) {
        viewModelScope.launch {
            val savedArticle = article.toSavedArticle()
            withContext(Dispatchers.IO) {
                repository.saveArticle(savedArticle)
            }
        }
    }



    fun deleteArticle(article: NewsResponse.Article) {
        viewModelScope.launch {
            val savedArticle = article.toSavedArticle()
            withContext(Dispatchers.IO){
                repository.deleteArticle(savedArticle)
            }
        }
    }
}
