package com.kodiiiofc.urbanuniversity.moviedatabase.data

import com.kodiiiofc.urbanuniversity.moviedatabase.domain.MovieRepository
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.SearchMovieResponse
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val service: MovieService) : MovieRepository {
    override suspend fun getRandomMovie() =
        service.getRandomMovie()

    override suspend fun getPagedList(): Response<SearchMovieResponse> {
        service.getPagedList()
    }
}