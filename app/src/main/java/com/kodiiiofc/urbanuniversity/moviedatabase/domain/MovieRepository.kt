package com.kodiiiofc.urbanuniversity.moviedatabase.domain

import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.random.MovieResponse
import retrofit2.Response


interface MovieRepository {

    suspend fun getRandomMovie() : Response<MovieResponse>

}