package com.ynov.kotlin.rickmorty.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String, length: Int = Snackbar.LENGTH_LONG){
    Snackbar.make(this, message, length).show()
}