package com.gmail.tpt.grocerystore

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import androidx.core.view.children

/**
 *
 * Created by thet on 27/2/2020.
 *
 *
 */

/*@NonNull Context context, @Nullable AttributeSet attrs,
            @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes*/
class CartFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    private var clipPaint: Paint

    init {

        clipPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            .apply {
                setColor(Color.RED)
                style = Paint.Style.FILL
                setStrokeWidth(1f)
            }

        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    override fun setBackground(background: Drawable?) {
//        super.setBackground(background)
    }

/*    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {

        for (child in children) {
            child.layout(left, top, right, bottom)
        }
    }*/


    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        val rectView = Path()
        rectView.addRect(
            0f,
            0f,
            1f * width,
            1f * height,
            Path.Direction.CW
        )


        val path = Path()
        path.moveTo(context.dp(40f), context.dp(23.334f))
        path.cubicTo(
            context.dp(1.6666f),
            context.dp(25f),
            context.dp(1.6666f),
            context.dp(5f),
            0f,
            0f
        )
        path.lineTo(width.toFloat(), 0f)
        path.cubicTo(
            width - context.dp(1.6666f),
            context.dp(5f),
            width - context.dp(5f),
            context.dp(25f),
            width - context.dp(40f),
            context.dp(23.334f)
        )


        path.close()

        clipPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.CLEAR))
        canvas.drawPath(path, clipPaint)
    }

}