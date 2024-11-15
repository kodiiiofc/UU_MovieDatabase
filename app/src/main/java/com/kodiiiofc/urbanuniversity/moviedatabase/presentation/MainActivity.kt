package com.kodiiiofc.urbanuniversity.moviedatabase.presentation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0,0,0,"Выйти")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            0 -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


}