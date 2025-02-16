package com.example.newsappassignment.screens

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.viewmodel.NewsListViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsDetailWebViewScreen(
    navController: NavHostController,
    article: NewsResponse.Article,
    viewModel: NewsListViewModel = hiltViewModel()
) {

    val url = article.url

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ){
        AndroidView(
            factory = { ctx ->
                WebView(ctx).apply {
                    webViewClient = WebViewClient()
                    webChromeClient = WebChromeClient()
                    settings.javaScriptEnabled = true
                    // Load the URL directly
                    url?.let {
                        loadUrl(it)
                    }
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        )
        Button(modifier = Modifier
            .height(50.dp)
            .padding(start = 50.dp, bottom = 80.dp)
            .fillMaxWidth()
            .weight(0.2f), onClick = {
            viewModel.saveArticle(article)
        }) {
            Text("Save")
        }
    }
}
