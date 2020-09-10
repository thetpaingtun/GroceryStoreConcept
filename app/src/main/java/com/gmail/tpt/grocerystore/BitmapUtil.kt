package com.gmail.tpt.grocerystore

import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes

fun Context.getDrawableDimension(@DrawableRes drawable: Int): Pair<Int, Int> {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true

    BitmapFactory.decodeResource(this.resources, drawable, options)
    return Pair(options.outWidth, options.outHeight)
}