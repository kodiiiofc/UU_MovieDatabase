package com.kodiiiofc.urbanuniversity.moviedatabase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainViewModel : ViewModel() {

    val liveData = MutableLiveData<String>()

    fun getRandomMovie() = viewModelScope.launch {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.kinopoisk.dev/v1.4/movie?page=1&limit=10")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("X-API-KEY", BuildConfig.apiKey)
            .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        liveData.postValue(response.body?.string())
                    }
                }
            })
    }

}