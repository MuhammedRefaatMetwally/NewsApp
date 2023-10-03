package com.example.newsapp.ui.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.data.api.model.newsResponse.News
import com.example.newsapp.databinding.ItemNewsBinding

class NewsAdapter(var newsList : List<News?>? = null) : Adapter<NewsAdapter.ViewHolder>(){
     var onNewsClickListener : OnNewsClickListener? = null
    inner class  ViewHolder(val binding : ItemNewsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(news: News?){
           binding.news = news
            binding.executePendingBindings() // 3shan myt25rsh fe binding the data whhen scrolling aw invalidateAll()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ViewHolder(binding)
    }

    override fun getItemCount(): Int = newsList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList!![position]
        holder.bind(news)
        if(onNewsClickListener != null){
          holder.binding.root.setOnClickListener {
              onNewsClickListener!!.onNewsClick(news)
          }
        }
    }

    fun bindView(articles: List<News?>?) {
         newsList = articles
        notifyDataSetChanged()
    }

    fun interface OnNewsClickListener{
        fun onNewsClick(news: News?)
    }
}