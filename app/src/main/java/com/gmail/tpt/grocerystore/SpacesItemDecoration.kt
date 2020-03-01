package com.gmail.tpt.grocerystore

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class SpacesItemDecoration(val context: Context, val initSpace: Int? = null) : ItemDecoration() {

    private val space: Int

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {


        parent.clipChildren = false
        parent.clipToPadding = false
        parent.setPadding(space, space, space, space)

        val pos = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount
        var bottomSpace = space
        if (itemCount > 0 && itemCount == pos + 1) {
            bottomSpace = convertDpToPixel(160f).toInt()
        }

        outRect.set(space, space, space, bottomSpace)
    }

    init {

        space =
            initSpace ?: context.getResources().getDimensionPixelSize(R.dimen.recycler_spacing) / 2;

    }

    fun convertDpToPixel(dp: Float): Float {
        val density = context.resources.displayMetrics.density
        return density * dp
    }

}