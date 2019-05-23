package com.ynov.kotlin.rickmorty.presentation.list.listener

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

class EndlessRecyclerViewScrollListener(private val mLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var visibleThreshold = 5
    private var currentPage = 1
    private var previousTotalItemCount = 0
    private var loading = true
    private val startingPageIndex = 1

    var onLoadMore: ((page: Int, totalItemsCount: Int, view: RecyclerView) -> Unit)? = null

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        val lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
        val totalItemCount = mLayoutManager.itemCount

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold >= totalItemCount) {
            currentPage++
            onLoadMore!!.invoke(currentPage, totalItemCount, view)
            loading = true
        }
    }
}