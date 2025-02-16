package com.example.newsappassignment.di

import com.example.newsappassignment.data.remote.NewsApiService
import com.example.newsappassignment.data.repository.NewsRepositoryImpl
import com.example.newsappassignment.domain.repository.NewsRepository
import com.example.newsappassignment.domain.use_cases.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NewsApiService =
        retrofit.create(NewsApiService::class.java)


    @Provides
    @Singleton
    fun provideNewsRepository(apiService: NewsApiService,): NewsRepository =
        NewsRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideNewsUseCase(repository: NewsRepository): GetNewsUseCase =
        GetNewsUseCase(repository)


}