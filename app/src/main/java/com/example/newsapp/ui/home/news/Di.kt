package com.example.newsapp.ui.home.news

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class Di {
    @Provides
    fun provideNewsAdapter() : NewsAdapter{
        return  NewsAdapter(emptyList())
    }
}