package com.w4eret1ckrtb1tch.recipesalderasoft.ui.adapter

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(private val sidePagingDp: Int, private val topPagingDp: Int) :
    RecyclerView.ItemDecoration() {

    private val Int.convertPx: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = topPagingDp.convertPx
        outRect.right = sidePagingDp.convertPx
        outRect.left = sidePagingDp.convertPx
    }
}