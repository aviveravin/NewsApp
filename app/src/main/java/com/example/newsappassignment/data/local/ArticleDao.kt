package com.example.newsappassignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.example.newsappassignment.data.model.SavedArticle
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: SavedArticle): Long  // Return Long for Insert

    @Query("SELECT * FROM saved_articles")
    fun getAllSavedArticles(): Flow<List<SavedArticle>>

    @Delete
    fun deleteArticle(article: SavedArticle): Int  // Return Int for Delete
}

