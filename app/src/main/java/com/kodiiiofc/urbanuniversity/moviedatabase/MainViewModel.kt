package com.kodiiiofc.urbanuniversity.moviedatabase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.MovieRepository
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

}