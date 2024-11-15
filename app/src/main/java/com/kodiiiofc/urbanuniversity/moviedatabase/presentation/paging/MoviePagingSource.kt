package com.kodiiiofc.urbanuniversity.moviedatabase.presentation.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.MovieRepository
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.Movie
import javax.inject.Inject

class MoviePagingSource
@Inject constructor(private val repository: MovieRepository) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        } as Int?
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 0
        Log.d("aAA", "MoviePagingSource.load")
        return try {

            Log.d("aAA", "MoviePagingSource.load -> block try")

            var moviePagedList = listOf<Movie>()
            val response = repository.getPagedList(
                page = page + 1,
                limit = 5,
                listParams = "top250"
            )
            if (response.isSuccessful && response.body() != null) {
                moviePagedList = response.body()!!.docs
            }

            Log.d("aAA", "load: $moviePagedList")

            LoadResult.Page(
                data = moviePagedList,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (moviePagedList.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }

    }
}