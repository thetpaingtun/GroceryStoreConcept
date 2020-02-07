package com.gmail.tpt.grocerystore

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpacesItemDecoration(context: Context) : ItemDecoration() {

    private val space: Int

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        /*    if (parent.paddingLeft != halfSpace) {
                parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace)
                parent.clipToPadding = false
            }*/

        parent.clipChildren = false
        parent.clipToPadding = false
        parent.setPadding(space, space, space, space)

        outRect.set(space, space, space, space)
    }

    init {
        space = context.getResources().getDimensionPixelSize(R.dimen.recycler_spacing) / 2;

    }

}