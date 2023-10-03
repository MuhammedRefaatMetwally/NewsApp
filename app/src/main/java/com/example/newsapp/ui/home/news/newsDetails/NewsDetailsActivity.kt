package com.example.newsapp.ui.home.news.newsDetails

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.newsapp.R
import com.example.data.api.model.newsResponse.News
import com.example.newsapp.databinding.ActivityNewsDetailsBinding
import java.text.DateFormat
import java.time.format.DateTimeFormatter

class NewsDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsDetailsBinding
    var news : News = News()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        news = intent.getParcelableExtra("news")!!
        binding.news = news
        binding.date.text = news.publishedAt?.format("yyyy-MM-dd\\'T\\'HH:mm:ss.SSS\\'Z\\")

    }
}