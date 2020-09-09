package com.gmail.tpt.grocerystore

import android.content.Context

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