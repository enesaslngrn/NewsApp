package com.enesas.newsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.enesas.newsapp.R
import com.enesas.newsapp.database.ArticleDatabase
import com.enesas.newsapp.databinding.ActivityNewsBinding
import com.enesas.newsapp.repository.NewsRepository
import com.enesas.newsapp.viewmodel.NewsViewModel
import com.enesas.newsapp.viewmodel.NewsViewModelProviderFactory

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)


        binding.bottomNavigationView.setOnItemSelectedListener { menuitem ->
            when (menuitem.itemId){
                R.id.breakingNewsFragment -> {
                    Navigation.findNavController(this,R.id.fragmentContainerView).navigate(R.id.breakingNewsFragment)
                    true
                }
                R.id.savedNewsFragment -> {
                    Navigation.findNavController(this,R.id.fragmentContainerView).navigate(R.id.savedNewsFragment)
                    true
                }
                R.id.searchNewsFragment -> {
                    Navigation.findNavController(this,R.id.fragmentContainerView).navigate(R.id.searchNewsFragment)
                    true
                }

                else -> false
            }
        }
    }
}