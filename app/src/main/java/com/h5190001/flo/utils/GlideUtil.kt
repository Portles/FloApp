package com.h5190001.flo.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.h5190001.flo.R

object GlideUtil {
    fun downloadPicAndShow(context: Context, url: String, img: ImageView) {
        Glide.with(context)
            .load(url)
            .error(R.drawable.logo)
            .centerCrop()
            .into(img)
    }
    fun ImageView.getImageFromUrl(url: String){
        Glide.with(this.context).load(url).into(this)
    }
}