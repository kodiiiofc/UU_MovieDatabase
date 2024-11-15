package com.kodiiiofc.urbanuniversity.moviedatabase.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kodiiiofc.urbanuniversity.moviedatabase.databinding.MovieListItemBinding
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.Movie

class MoviePagingAdapter : PagingDataAdapter<Movie, MoviePagingAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.binding.apply {
            movieTitle.text = movie?.name
            movieYear.text = movie?.year.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }
}