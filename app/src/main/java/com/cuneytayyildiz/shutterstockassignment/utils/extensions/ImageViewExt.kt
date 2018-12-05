package com.cuneytayyildiz.shutterstockassignment.utils.extensions

import android.widget.ImageView
import com.cuneytayyildiz.shutterstockassignment.R
import com.squareup.picasso.Picasso

fun ImageView.load(url: String) {
    if (url.isNotEmpty()) Picasso.get().load(url).placeholder(R.drawable.bg_placeholder).into(this)
}