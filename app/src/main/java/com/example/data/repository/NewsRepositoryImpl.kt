package com.example.data.repository

import com.example.data.api.model.newsResponse.News
import com.example.newsapp.dataSource.NewsDataSource
import com.example.newsapp.repository.newsRepositroy.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(@NewsOnlineDataSource private val newsDataSource: NewsDataSource) : NewsRepository{
    override suspend fun getNews(sourceId: String): List<News?>? {
        return  newsDataSource.getNews(sourceId)
    }
}