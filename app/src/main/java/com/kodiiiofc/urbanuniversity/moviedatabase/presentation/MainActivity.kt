package com.kodiiiofc.urbanuniversity.moviedatabase.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.kodiiiofc.urbanuniversity.moviedatabase.R
import com.kodiiiofc.urbanuniversity.moviedatabase.databinding.ActivityMainBinding
import com.kodiiiofc.urbanuniversity.moviedatabase.presentation.paging.MovieLoadStateAdapter
import com.kodiiiofc.urbanuniversity.moviedatabase.presentation.paging.MoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = MoviePagingAdapter(this)
        binding.recyclerView.adapter = adapter.withLoadStateFooter(MovieLoadStateAdapter())

        lifecycleScope.launch {
            Log.d("TAG", "onCreate: lifecycleScope.launch")
            viewModel.data.collectLatest {
                Log.d("TAG", "onCreate: viewModel.data.collectLatest")
                adapter.submitData(it).let {
                    Log.d("TAG", "onCreate: adapter.submitData")
                }
            }
        }

    }
}