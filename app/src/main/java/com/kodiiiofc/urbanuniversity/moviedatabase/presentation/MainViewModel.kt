package com.kodiiiofc.urbanuniversity.moviedatabase.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.MovieRepository
import com.kodiiiofc.urbanuniversity.moviedatabase.presentation.paging.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject constructor(private val repository: MovieRepository)
 : ViewModel() {

    val data = Pager(
        PagingConfig(
            pageSize = 3,
            enablePlaceholders = false,
            initialLoadSize = 3
        )
    ) {
        Log.d("TAG", "MainViewModel: data")
        MoviePagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}