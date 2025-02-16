package com.example.newsappassignment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsappassignment.data.model.NewsResponse
import com.example.newsappassignment.screens.NewsDetailWebViewScreen
import com.example.newsappassignment.screens.NewsListScreen
import com.example.newsappassignment.screens.SavedNewsScreen
import com.google.gson.Gson

@Composable
fun BottomNavGraph(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = Screens.Home.route){
        composable(Screens.Home.route){
            NewsListScreen(navHostController)
        }
        composable(Screens.SavedNews.route){
            SavedNewsScreen(navHostController)
        }

        composable(
            route = "${Screens.NewsDetail.route}/{articleJson}",
            arguments = listOf(navArgument("articleJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val articleJson = backStackEntry.arguments?.getString("articleJson")
            val article = articleJson?.let {
                Gson().fromJson(it, NewsResponse.Article::class.java)
            }

            if (article != null) {
                NewsDetailWebViewScreen(navHostController, article)
            }
        }
    }
}