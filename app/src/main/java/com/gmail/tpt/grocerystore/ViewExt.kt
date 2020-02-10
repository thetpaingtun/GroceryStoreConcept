package com.gmail.tpt.grocerystore

import android.graphics.Point
import android.view.View

/**
 *
 * Created by thet on 10/2/2020.
 *
 */
fun View.getLocationOnScreen(): Point {
    val location = IntArray(2)
    this.getLocationOnScreen(location)
    return Point(location[0], location[1])
}