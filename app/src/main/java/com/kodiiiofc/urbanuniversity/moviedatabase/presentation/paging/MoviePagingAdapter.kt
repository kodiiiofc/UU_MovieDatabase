package com.kodiiiofc.urbanuniversity.moviedatabase.presentation.paging

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kodiiiofc.urbanuniversity.moviedatabase.databinding.MovieListItemBinding
import com.kodiiiofc.urbanuniversity.moviedatabase.domain.models.Movie

class MoviePagingAdapter(private val context: Context) : PagingDataAdapter<Movie, MoviePagingAdapter.ViewHolder>(DIFF_CALLBACK) {

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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.binding.apply {
            Glide.with(context).load(movie?.poster?.url).into(moviePoster)
            movieGenre.text = movie?.genres?.joinToString(separator = ", ")
            movieTitle.text = movie?.name
            movieYear.text = movie?.year.toString()
            movieRating.text = "${movieRating.text} ${movie?.rating?.imdb}"
            ratingBar.rating = (movie?.rating?.imdb?.toFloat() ?: 0f) / 2




        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }
}