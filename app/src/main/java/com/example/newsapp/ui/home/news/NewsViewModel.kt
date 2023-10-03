package com.example.newsapp.ui.home.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.model.newsResponse.News
import com.example.data.api.model.newsResponse.NewsResponse
import com.example.data.api.model.sourceResponse.Source
import com.example.data.api.model.sourceResponse.SourcesResponse
import com.example.data.repository.NewsOfflineDataSource
import com.example.newsapp.dataSource.NewsDataSource
import com.example.newsapp.repository.newsRepositroy.NewsRepository
import com.example.newsapp.repository.sourcesRepository.SourcesRepository
import com.example.newsapp.ui.ViewError
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject



@HiltViewModel
class NewsViewModel @Inject constructor(
    private val sourcesRepo: SourcesRepository,
    private val newsRepository: NewsRepository,
    @NewsOfflineDataSource
    private val newsOfflineDataSource: NewsDataSource
) : ViewModel() {
    val shouldShowLoading = MutableLiveData<Boolean>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()
    val newsLiveData = MutableLiveData<List<News?>?>()
    val errorLiveData = MutableLiveData<ViewError>()

    fun getNewsSources() {
        viewModelScope.launch {
            shouldShowLoading.postValue(true)
            try {
                val sources = sourcesRepo.getSources()
                sourcesLiveData.postValue(sources)
            } catch (e: HttpException) {
                val errorBodyJsonString = e.response()?.errorBody()?.string()
                val response =
                    Gson().fromJson(errorBodyJsonString, SourcesResponse::class.java)
                errorLiveData.postValue(ViewError(
                    message = response.message
                ) {
                    getNewsSources()
                })
            } catch (e: Exception) {
                errorLiveData.postValue(
                    ViewError(
                        throwable = e
                    ) {
                        getNewsSources()
                    }
                )
            } finally {
                shouldShowLoading.postValue(false)
            }

        }
//
    }

    fun getNews(sourceId: String?) {
        shouldShowLoading.postValue(true)
        viewModelScope.launch {
            try {
                val articles = newsRepository.getNews(sourceId ?: "")
                newsLiveData.postValue(articles)

            } catch (ex: HttpException) {
                val responseJsonError = ex.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(responseJsonError, NewsResponse::class.java)
                errorLiveData.postValue(
                    ViewError(
                        message = errorResponse.message
                    ) { getNews(sourceId) }
                )
            } catch (e: Exception) {
                errorLiveData.postValue(
                    ViewError(
                        throwable = e
                    ) { getNews(sourceId) }
                )
            } finally {
                shouldShowLoading.postValue(false)
            }
        }
    }


}