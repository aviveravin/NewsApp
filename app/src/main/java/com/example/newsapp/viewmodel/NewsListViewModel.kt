package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.NewResponse
import com.example.newsapp.domain.use_case.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase
) : ViewModel(){

    private val _newsList = MutableStateFlow<List<NewResponse.Article>>(emptyList())
    val newList: StateFlow<List<NewResponse.Article>> = _newsList

    init {
       fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            _newsList.value = newsUseCase()
        }
    }
}