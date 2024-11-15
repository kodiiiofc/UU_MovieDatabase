package com.kodiiiofc.urbanuniversity.moviedatabase.data

import com.kodiiiofc.urbanuniversity.moviedatabase.domain.MovieRepository
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.SearchMovieResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val service: MovieService) : MovieRepository {
    override suspend fun getRandomMovie() =
        service.getRandomMovie()

    override suspend fun getPagedList(page: Int, limit: Int, listParams: String): Response<SearchMovieResponse> =
        service.getPagedList(page, limit, listParams)
}