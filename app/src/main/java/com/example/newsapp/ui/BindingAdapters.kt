package com.example.newsapp.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.newsapp.R

@BindingAdapter(value = ["app:url" , "app:placeholder"])
fun bindImageWithUrl(imageView: ImageView , url: String? , placeholder:Drawable){
    Glide.with(imageView)
        .load(url)
        .placeholder(placeholder)
        .into(imageView)
}


@BindingAdapter(value = ["app:imageUrl" , "app:roundedPlaceholder"])
fun bindImageUrl(imageView: ImageView , url: String? , placeholder:Drawable){
    Glide.with(imageView)
        .load(url)
        .placeholder(placeholder)
        .into(imageView)
}

@BindingAdapter("launchUrl")
fun launchUrl(view : View, url : String?){
    view.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.context.startActivity(intent)
    }
}