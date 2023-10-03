package com.example.data.repository

import com.example.data.dataSourceImp.NewsOfflineDataSourceImpl
import com.example.data.dataSourceImp.NewsOnlineDataSourceImpl
import com.example.data.dataSourceImp.SourceOnlineDataSourceImpl
import com.example.newsapp.dataSource.NewsDataSource
import com.example.newsapp.dataSource.SourcesDataSource
import com.example.newsapp.repository.newsRepositroy.NewsRepository
import com.example.newsapp.repository.sourcesRepository.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {  // mdam bt3md 3la Interface wa Implementation bta3o esht8l abstract class a7sn m3a @Binds

    @Binds
    abstract fun provideSourcesRepository(
        repo:SourcesRepositoryImpl
    ) : SourcesRepository

    @Binds
    abstract fun provideSourcesDataSource(
        dataSource : SourceOnlineDataSourceImpl
    ): SourcesDataSource

    @Binds
    abstract fun provideNewsRepository(
        repo : NewsRepositoryImpl
    ) : NewsRepository


    @NewsOnlineDataSource
    @Binds
    abstract fun provideNewsDataSource(
        dataSource : NewsOnlineDataSourceImpl
    ) : NewsDataSource


    @NewsOfflineDataSource
    @Binds
    abstract fun provideNewsOfflineDataSource(
        dataSource: NewsOfflineDataSourceImpl
    ):NewsDataSource
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsOfflineDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsOnlineDataSource