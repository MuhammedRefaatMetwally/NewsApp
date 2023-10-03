package com.example.data.repository

import com.example.data.api.model.sourceResponse.Source
import com.example.newsapp.dataSource.SourcesDataSource
import com.example.newsapp.repository.sourcesRepository.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(private val sourceDataSource:SourcesDataSource) : SourcesRepository {
    override suspend fun getSources(): List<Source?>? {
        return sourceDataSource.getSource()
    }
}