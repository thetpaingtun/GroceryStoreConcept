package com.gmail.tpt.grocerystore

import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.ShapePath

class UCorner : CornerTreatment() {

    override fun getCornerPath(
        shapePath: ShapePath,
        angle: Float,
        interpolation: Float,
        radius: Float
    ) {

        shapePath.reset(0.0f, radius * interpolation)
        shapePath.lineTo(radius * interpolation, radius * interpolation)
        shapePath.lineTo(radius * interpolation, 0f)
//        shapePath.reset(0f, radius * interpolation, 180f, 180 - angle)

    }
}