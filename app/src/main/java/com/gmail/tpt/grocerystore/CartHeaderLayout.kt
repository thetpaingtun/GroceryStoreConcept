package com.gmail.tpt.grocerystore

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlin.math.floor

/**
 *
 * Created by thet on 4/3/2020.
 *
 */
class CartHeaderLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val margin = 16f
    val itemWidth = 48f

    init {
        orientation = HORIZONTAL
    }

    fun getHoldableImageCount(): Int {
        val total = margin + itemWidth
        val holdableItem = (width / context.dp(total))
        return floor(holdableItem).toInt()
    }

    fun lastChild(): View? {
        if (childCount == 0) return null
        return getChildAt(childCount - 1)
    }

    fun addViewWithMarginLeft(view: View) {
        val lp = LayoutParams(layoutParams)
        //if there is an item on the left
        if (childCount > 0) {
            lp.marginStart = context.dp(16f).toInt()
        }
        lp.width = context.dp(48f).toInt()
        lp.height = context.dp(48f).toInt()

        addView(view, lp)
    }

    fun isLayoutFull(): Boolean {
        return childCount >= getHoldableImageCount()
    }

}