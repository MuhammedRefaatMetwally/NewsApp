package com.example.newsapp.repository.sourcesRepository

import com.example.data.api.model.sourceResponse.Source

interface SourcesRepository {
    suspend fun getSources() : List<Source?>?
}