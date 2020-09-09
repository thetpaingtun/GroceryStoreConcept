package com.gmail.tpt.grocerystore

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

/**
 *
 * Created by thet on 4/3/2020.
 *
 */
class CartHeaderLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    init {
        orientation = HORIZONTAL
    }

    fun getHoldableImageCount(): Int {
        val margin = 16f
        val itemWidth = 48f
        val total = margin + itemWidth
        val holdableItem = (width / context.dp(total))
        return holdableItem.toInt()
    }

    fun lastChild(): View? {
        if (childCount == 0) return null
        return getChildAt(childCount - 1)
    }

    fun addViewWithMarginLeft(view: View) {
        val lp = LinearLayout.LayoutParams(layoutParams)
        //if there is an item on the left
        if (childCount > 0) {
            lp.marginStart = context.dp(16f).toInt()
        }
        lp.width = context.dp(48f).toInt()
        lp.height = context.dp(48f).toInt()

        addView(view, lp)
    }
}