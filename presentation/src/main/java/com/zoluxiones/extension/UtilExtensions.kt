package com.zoluxiones.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.zoluxiones.R

fun ImageView.loadImageFromUrl(
    url: String,
    lifeCycleOwner: Fragment,
    @DrawableRes placeholder: Int = R.mipmap.ic_launcher
) {
    Glide
        .with(lifeCycleOwner)
        .load(url)
        .centerCrop()
        .placeholder(placeholder)
        .into(this)
}