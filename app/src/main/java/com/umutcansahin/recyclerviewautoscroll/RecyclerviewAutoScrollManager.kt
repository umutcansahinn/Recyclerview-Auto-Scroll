package com.umutcansahin.recyclerviewautoscroll

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class RecyclerviewAutoScrollManager(
    context: Context?,
    orientation: Int,
    reverseLayout: Boolean
) : LinearLayoutManager(context, orientation, reverseLayout) {
    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {

        val linearSmoothScroller:LinearSmoothScroller = object : LinearSmoothScroller(
            recyclerView.context
        ) {
            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                return 250f / displayMetrics.densityDpi
                //faster = 100f>500f>1000f....
            }
        }
        linearSmoothScroller.targetPosition = position
        startSmoothScroll(linearSmoothScroller)
    }
}