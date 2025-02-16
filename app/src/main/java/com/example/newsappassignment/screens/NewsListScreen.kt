package com.example.newsappassignment.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.viewmodel.NewsListViewModel
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    navHostController: NavHostController,
    viewModel: NewsListViewModel = hiltViewModel()
) {
    val newsList = viewModel.newList.collectAsState()
    Scaffold(topBar = { TopAppBar(title = { Text(text = "News") }) }) { padding ->

        LazyColumn(modifier = Modifier.padding(padding)) {
            items(newsList.value) { news ->
                NewsItem(news) {
                    val articleJson = Gson().toJson(news)
                    val encodedArticleJson = URLEncoder.encode(articleJson, StandardCharsets.UTF_8.toString())
                    val encodedUrl = URLEncoder.encode(news.url, StandardCharsets.UTF_8.toString())
                    navHostController.navigate("news_detail/${encodedArticleJson}}")
                }
            }
        }

    }
}

@Composable
fun NewsItem(news: NewsResponse.Article, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(news.urlToImage),
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(news.title)
            Text(news.description ?: "")
            Button(onClick = onClick) {
                Text("Read More")
            }
        }
    }
}