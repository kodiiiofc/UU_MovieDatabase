package com.kodiiiofc.urbanuniversity.moviedatabase.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.MovieRepository
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.Movie
import javax.inject.Inject

class MoviePagingSource
@Inject constructor(private val repository: MovieRepository)
    : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        } as Int?
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 0
        return try {
            val moviePagedList = repository.getPagedList().body()?.docs

            LoadResult.Page(
                data = moviePagedList ?: listOf(),
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (moviePagedList.isNullOrEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}