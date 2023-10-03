package com.example.data.dataSourceImp

import com.example.data.api.WebServices
import com.example.data.api.model.newsResponse.News
import com.example.newsapp.dataSource.NewsDataSource
import javax.inject.Inject

class NewsOfflineDataSourceImpl @Inject constructor(val webServices: WebServices) : NewsDataSource {
    override suspend fun getNews(sourceId: String): List<News?> {
            return listOf()
        }
}
