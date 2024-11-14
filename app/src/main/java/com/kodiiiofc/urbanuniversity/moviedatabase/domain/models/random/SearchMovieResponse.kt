package com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.random

data class SearchMovieResponse(
    val docs: List<MovieResponse>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)