package com.kodiiiofc.urbanuniversity.moviedatabase.domain

import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.Movie
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.SearchMovieResponse
import retrofit2.Response


interface MovieRepository {

    suspend fun getRandomMovie() : Response<Movie>

    suspend fun getPagedList(page: Int, limit: Int, listParams: String): Response<SearchMovieResponse>

}