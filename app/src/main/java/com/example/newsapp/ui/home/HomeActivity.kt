package com.example.newsapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.ui.home.news.NewsFragment
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // feh shrt by2ol lw fragment wa5da AndroidEntryPoint lazem el Activity ta5od hekam AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NewsFragment())
            .commit()
    }
}