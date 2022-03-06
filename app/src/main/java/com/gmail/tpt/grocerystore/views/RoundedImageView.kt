package com.gmail.tpt.grocerystore.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.core.graphics.drawable.toBitmap
import com.gmail.tpt.grocerystore.R

class RoundedImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    companion object {
        const val IMAGE_SCALE_FACTOR = 0.8f
    }

    var scaleFactor: Float = 1f
        set(value) {
            field = value
            invalidate()
        }
    var showRoundedBackground = false
        set(value) {
            field = value
            postInvalidate()
        }

    private lateinit var roundedBg: RoundedBitmapDrawable

    init {
        scaleType = ScaleType.FIT_CENTER

        attrs?.apply {
            val typedArray = context.obtainStyledAttributes(
                this,
                R.styleable.RoundedImageView, 0, 0
            )

            showRoundedBackground =
                typedArray.getBoolean(R.styleable.RoundedImageView_showRoundBackground, false)

            typedArray.recycle()
        }
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
        if (showRoundedBackground) {
            canvas?.let {
                roundedBg.draw(it)
            }
            canvas?.save()
            canvas?.scale(scaleFactor, scaleFactor, width / 2f, height / 2f)
        }
        super.onDraw(canvas)

        if (showRoundedBackground) {
            canvas?.restore()
        }
    }


}