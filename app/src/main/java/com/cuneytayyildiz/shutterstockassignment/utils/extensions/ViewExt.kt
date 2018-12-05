package com.cuneytayyildiz.shutterstockassignment.utils.extensions

import android.view.View
import com.cuneytayyildiz.shutterstockassignment.R
import com.google.android.material.snackbar.Snackbar

fun View.snack(text: String, retry: (Snackbar) -> Unit) {
    val snackbar = Snackbar.make(this, text, Snackbar.LENGTH_INDEFINITE)
    snackbar.setAction(R.string.button_retry) { retry(snackbar) }
    snackbar.show()
}