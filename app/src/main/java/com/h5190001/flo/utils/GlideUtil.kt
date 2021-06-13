package com.h5190001.flo.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideUtil {
    fun ImageView.getImageFromUrl(url: String){
        Glide.with(this.context).load(url).into(this)
    }
}