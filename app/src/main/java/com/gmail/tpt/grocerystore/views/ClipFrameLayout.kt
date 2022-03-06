package com.gmail.tpt.grocerystore.views

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import com.gmail.tpt.grocerystore.dp
import com.gmail.tpt.grocerystore.utils.Logger

class ClipFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var path: Path
    private var bgDrawable: Drawable? = null
    private var backgroundResource: Int? = null
    private var backgroundColor: Int? = null

    private val maskedPaint: Paint

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
        maskedPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            .apply {
                color = Color.WHITE
                xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
            }
    }


    override fun onDraw(canvas: Canvas?) {
        Logger.d("on draw =>")
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        path = Path()
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
    }

    override fun dispatchDraw(canvas: Canvas) {

        backgroundColor?.toDrawable()
            ?.apply {
                setBounds(0, 0, width, height)
                draw(canvas)
            }


        backgroundResource
            ?.apply {
                val drawable = ContextCompat.getDrawable(context, this)
                drawable?.setBounds(0, 0, width, height)
            }

        bgDrawable?.apply {
            setBounds(0, 0, width, height)
            draw(canvas)
        }


        super.dispatchDraw(canvas)
        canvas.drawPath(path, maskedPaint)
    }

    override fun setBackgroundColor(color: Int) {
        backgroundResource = null
        bgDrawable = null
        backgroundColor = color
    }

    override fun setBackgroundResource(resid: Int) {
        backgroundColor = null
        bgDrawable = null
        backgroundResource = resid
    }

    override fun setBackground(background: Drawable?) {
        backgroundColor = null
        backgroundResource = null
        bgDrawable = background
    }
}