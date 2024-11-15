package com.kodiiiofc.urbanuniversity.moviedatabase.presentation.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kodiiiofc.urbanuniversity.moviedatabase.databinding.LoadStateViewBinding

class MovieLoadStateAdapter : LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(val binding: LoadStateViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(
        holder: MovieLoadStateAdapter.LoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.binding.progress.isVisible = loadState is LoadState.Loading
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MovieLoadStateAdapter.LoadStateViewHolder {
        return LoadStateViewHolder(
            LoadStateViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}