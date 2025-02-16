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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.newsappassignment.data.model.SavedArticle
import com.example.newsappassignment.navigation.Screens
import com.example.newsappassignment.util.toNewsResponseArticle
import com.example.newsappassignment.viewmodel.NewsListViewModel
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun SavedNewsScreen(
    navHostController: NavHostController,
    viewModel: NewsListViewModel = hiltViewModel()
) {
    val savedArticles by viewModel.savedArticles.collectAsState(emptyList())

    LazyColumn {
        items(savedArticles) { article ->
            SavedNewsItem(
                article = article,
                onDeleteClick = {
                    viewModel.deleteArticle(article.toNewsResponseArticle())
                },
                onClick = {
                    val articleJson = Gson().toJson(article.toNewsResponseArticle())
                    val encodedArticleJson = URLEncoder.encode(articleJson, StandardCharsets.UTF_8.toString())
                    navHostController.navigate("${Screens.NewsDetail.route}/$encodedArticleJson")
                }
            )
        }
    }
}

@Composable
fun SavedNewsItem(
    article: SavedArticle,
    onDeleteClick: () -> Unit,
    onClick: () -> Unit
) {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable(onClick = onClick)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(article.urlToImage),
                    contentDescription = "News Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                article.title?.let { Text(it) }
                Text(article.description ?: "")
                Button(onClick = onClick) {
                    Text("Read More")
                }
            }
        }
        Button(onClick = onDeleteClick) {
            Text(text = "Delete")
        }
    }
}

