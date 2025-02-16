package com.example.newsappassignment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.newsappassignment.screens.NewsListScreen
import com.example.newsappassignment.screens.SavedNewsScreen

@Composable
fun BottomNavGraph(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = Screens.Home.route){
        composable(Screens.Home.route){
            NewsListScreen(navHostController)
        }
        composable(Screens.SavedNews.route){
            SavedNewsScreen()
        }
    }
}