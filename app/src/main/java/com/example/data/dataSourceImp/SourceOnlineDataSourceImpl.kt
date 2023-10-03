package com.example.data.dataSourceImp

import com.example.data.api.WebServices
import com.example.data.api.model.sourceResponse.Source
import com.example.newsapp.dataSource.SourcesDataSource
import javax.inject.Inject

class SourceOnlineDataSourceImpl @Inject constructor(private val webServices: WebServices) : SourcesDataSource {
    override suspend fun getSource(): List<Source?>? {
        val response = webServices.getSources()
        return response.sources
    }

}