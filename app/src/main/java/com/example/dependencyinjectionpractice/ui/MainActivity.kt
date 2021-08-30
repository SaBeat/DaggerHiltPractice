package com.example.dependencyinjectionpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dependencyinjectionpractice.R
import com.example.dependencyinjectionpractice.adapter.NewsAdapter
import com.example.dependencyinjectionpractice.databinding.ActivityMainBinding
import com.example.dependencyinjectionpractice.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var newsAdapter: NewsAdapter
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        initViewModel()
    }

    private fun initRecycler() {
        newsAdapter = NewsAdapter()
        binding.rvMain.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.listObserver().observe(this, {
            if (it != null) {
                newsAdapter.submitList(it.articles)
            }
        })
        viewModel.getViewModel()
    }
}