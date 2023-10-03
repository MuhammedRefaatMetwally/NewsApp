package com.example.newsapp.dataSource

import com.example.data.api.model.sourceResponse.Source

interface SourcesDataSource {
 suspend fun getSource() : List<Source?>?
}