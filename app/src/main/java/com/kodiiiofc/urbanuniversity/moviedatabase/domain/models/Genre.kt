package com.kodiiiofc.urbanuniversity.moviedatabase.domain.models

data class Genre(
    val name: String
) {
    override fun toString(): String {
        return name
    }
}