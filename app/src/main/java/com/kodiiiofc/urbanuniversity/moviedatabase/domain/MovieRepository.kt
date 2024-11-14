package com.kodiiiofc.urbanuniversity.moviedatabase.domain

import com.kodiiiofc.urbanuniversity.moviedatabase.data.MovieService
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.random.MovieResponse
import retrofit2.Response
import javax.inject.Inject


interface MovieRepository {

    suspend fun getRandomMovie() : Response<MovieResponse>

}