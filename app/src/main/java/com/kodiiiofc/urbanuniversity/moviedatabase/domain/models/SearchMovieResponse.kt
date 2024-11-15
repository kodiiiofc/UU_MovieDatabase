package com.kodiiiofc.urbanuniversity.moviedatabase.domain.models

data class SearchMovieResponse(
    val docs: List<Movie>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)