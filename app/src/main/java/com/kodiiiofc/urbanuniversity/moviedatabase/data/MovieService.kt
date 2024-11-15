package com.kodiiiofc.urbanuniversity.moviedatabase.data

import com.kodiiiofc.urbanuniversity.moviedatabase.BuildConfig
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.Movie
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.SearchMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieService {

    @Headers(
        "accept: application/json",
        "X-API-KEY: ${BuildConfig.apiKey}"
    )
    @GET("/v1.4/movie/random")
    suspend fun getRandomMovie() : Response<Movie>

    @Headers(
        "accept: application/json",
        "X-API-KEY: ${BuildConfig.apiKey}"
    )
    @GET("/v1.4/movie")
    suspend fun getPagedList(
        @Query("page") currentPage: Int,
        @Query("limit") limitPages: Int,
        @Query("lists") listsParams: String
    ) : Response<SearchMovieResponse>

}