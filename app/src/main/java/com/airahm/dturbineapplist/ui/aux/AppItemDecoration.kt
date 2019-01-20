package com.airahm.dturbineapplist.ui.aux

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.airahm.dturbineapplist.app.AppConstants

class AppItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val k = parent.getChildAdapterPosition(view)
        when {
            k <= 1 -> outRect.top = AppConstants.INITIAL_OFFSET;
        }
    }
}