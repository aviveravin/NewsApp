package com.example.newsappassignment.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val title: String, val icon: ImageVector) {
    object Home: Screens("home", "News List", Icons.Default.Home)
    object NewsDetail: Screens("news_detail", "News Detail", Icons.Default.Info)
    object SavedNews: Screens("saved_news", "Saved News", Icons.Default.Favorite)
}