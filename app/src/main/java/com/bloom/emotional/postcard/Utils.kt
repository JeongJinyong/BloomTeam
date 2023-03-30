package com.bloom.emotional.postcard

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*

fun defaultImageRequestOptions() = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
fun ImageView.imageLoad(
    url: String,
    requestOptions: RequestOptions = defaultImageRequestOptions()
) {
    Glide.with(context).load(url).apply(requestOptions).into(this)
}

fun getCurrentTime(): String {
    val date = Date()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    return dateFormat.format(date)
}