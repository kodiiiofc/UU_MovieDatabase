package com.kodiiiofc.urbanuniversity.moviedatabase.data

import com.kodiiiofc.urbanuniversity.moviedatabase.BuildConfig
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieService {

    @Headers(
        "accept: application/json",
        "X-API-KEY: ${BuildConfig.apiKey}"
    )
    @GET("/v1.4/movie/random")
    suspend fun getRandomMovie() : Response<Movie>

}