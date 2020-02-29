package com.gmail.tpt.grocerystore

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.FrameLayout

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
        path.moveTo(120f, 70f)
        path.cubicTo(5f, 75f, 5f, 15f, 0f, 0f)
        path.lineTo(width.toFloat(), 0f)
        path.cubicTo(width - 5f, 15f, width - 5f, 75f, width - 120f, 70f)


        path.close()

        clipPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.CLEAR))
        canvas.drawPath(path, clipPaint)
    }

    override fun getOutlineProvider(): ViewOutlineProvider {
        return object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {

                val path = Path()
                path.moveTo(100f, 50f)
                path.lineTo(0f, 0f)
                path.lineTo(width.toFloat(), 0f)
                path.lineTo(width - 100f, 50f)
                path.close()

                outline.setConvexPath(path)
            }
        }
    }
}