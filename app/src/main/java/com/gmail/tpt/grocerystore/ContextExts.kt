package com.gmail.tpt.grocerystore

import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes

/**
 *
 * Created by thet on 4/3/2020.
 *
 */
fun Context.dp(dp: Float): Float {
    return resources.displayMetrics.density * dp
}

fun Context.px(px: Float): Float {
    return px / resources.displayMetrics.density
}

fun Context.getDrawableDimension(@DrawableRes drawable: Int): Pair<Int, Int> {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true

    BitmapFactory.decodeResource(this.resources, drawable, options)
    return Pair(options.outWidth, options.outHeight)
}