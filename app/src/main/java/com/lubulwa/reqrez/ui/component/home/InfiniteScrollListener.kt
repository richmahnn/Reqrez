package com.lubulwa.reqrez.ui.component.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class InfiniteScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
            val visibleItemCount = linearLayoutManager.childCount
            val totalItemCount = linearLayoutManager.itemCount
            val pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()

            if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                loadMore()
            }
        }
    }

    protected abstract fun loadMore()

}