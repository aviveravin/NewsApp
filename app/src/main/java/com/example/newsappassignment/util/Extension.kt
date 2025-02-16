package com.example.newsappassignment.util

import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.data.model.SavedArticle

fun NewsResponse.Article.toSavedArticle(): SavedArticle {
    return SavedArticle(
        url = this.url,
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        title = this.title,
        urlToImage = this.urlToImage
    )
}

fun SavedArticle.toNewsResponseArticle(): NewsResponse.Article {
    return NewsResponse.Article(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        publishedAt = this.publishedAt ?: "",
        source = NewsResponse.Article.Source(id = "", name = this.publishedAt ?: ""),
        title = this.title ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage ?: ""
    )
}


