package com.example.newsapp.repository.newsRepositroy

import com.example.data.api.model.newsResponse.News

interface NewsRepository {
    suspend fun getNews(sourceId : String) : List<News?>?
}