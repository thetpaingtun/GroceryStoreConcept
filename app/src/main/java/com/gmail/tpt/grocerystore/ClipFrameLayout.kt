package com.gmail.tpt.grocerystore

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.updatePadding

class ClipFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var path: Path
    private var bgDrawable: Drawable? = null
    private var backgroundResource: Int? = null
    private var backgroundColor: Int? = null


    init {

//        updatePadding(top = context.dp(48f).toInt())
    }


/*    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {

        for (child in children) {
            child.layout(left, top, right, bottom)
        }
    }*/

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
        Logger.d("dispatch draw =>")

        val state = canvas.save()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            canvas.clipOutPath(path)
        } else {
            canvas.clipPath(path, Region.Op.INTERSECT)
        }

        backgroundColor?.toDrawable()
            ?.apply {
                setBounds(0, 0, width, height)
                draw(canvas)
            }


        backgroundResource
            ?.apply {
                val drawable = ContextCompat.getDrawable(context, this)
                drawable?.setBounds(0, 0, width, height)
                drawable?.draw(canvas)
            }

        bgDrawable?.apply {
            setBounds(0, 0, width, height)
            draw(canvas)
        }


        super.dispatchDraw(canvas)
        canvas.restoreToCount(state)

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