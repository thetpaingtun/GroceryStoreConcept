package com.gmail.tpt.grocerystore

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.core.graphics.drawable.toBitmap

class RoundedImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    private lateinit var roundedBg: RoundedBitmapDrawable

    init {
        scaleType = ScaleType.FIT_CENTER
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val bitmap = ColorDrawable(Color.WHITE).toBitmap(width = w, height = h)
        roundedBg = RoundedBitmapDrawableFactory.create(resources, bitmap).apply {
            setBounds(0, 0, w, h)
            isCircular = true
        }
    }


    override fun onDraw(canvas: Canvas?) {
        canvas?.let { roundedBg.draw(it) }
        super.onDraw(canvas)
    }
}