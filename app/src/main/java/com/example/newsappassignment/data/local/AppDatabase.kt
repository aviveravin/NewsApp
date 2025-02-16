package com.example.newsappassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsappassignment.data.model.SavedArticle

@Database(entities = [SavedArticle::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
