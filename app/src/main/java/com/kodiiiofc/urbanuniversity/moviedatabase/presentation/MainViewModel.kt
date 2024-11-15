package com.kodiiiofc.urbanuniversity.moviedatabase.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.MovieRepository
import com.kodiiiofc.urbanuniversity.moviedatabase.presentation.paging.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject constructor(private val repository: MovieRepository)
 : ViewModel() {

    val liveData = MutableLiveData<String>()

    fun getRandomMovie() = viewModelScope.launch {

        val response = repository.getRandomMovie()
        if (response.isSuccessful) {
            response.body().let { body ->
                liveData.postValue(body.toString())
            }
        }

    }

    val data = Pager(
        PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            initialLoadSize = 10
        )
    ) {
        MoviePagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}