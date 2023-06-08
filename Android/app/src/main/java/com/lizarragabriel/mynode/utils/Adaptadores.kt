package com.lizarragabriel.mynode.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lizarragabriel.mynode.R

object Adaptadores {
    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(image: ImageView, url: String?) {
        if(url != null) {
            Glide.with(image.context).load(url).error(R.drawable.notfound).into(image)
        }
    }
}