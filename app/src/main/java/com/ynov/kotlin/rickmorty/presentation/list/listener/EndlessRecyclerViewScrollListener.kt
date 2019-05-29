package com.ynov.kotlin.rickmorty.presentation.list.listener

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

// je rajoute 1 point à la note juste pour la beauté d'avoir fait cette classe
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
            // TODO dangereux d'utiliser !! ici, il faut éviter ça au maximum, un bon code kotlin ne contient aucun !!
            onLoadMore!!.invoke(currentPage, totalItemCount, view)
            loading = true
        }
    }
}