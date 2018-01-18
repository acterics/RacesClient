package com.acterics.racesclient.common.dsl

import android.support.v7.widget.RecyclerView
import com.acterics.racesclient.common.ui.DefaultItemAdapter
import com.acterics.racesclient.common.ui.PagingMvpViewDelegate
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener

/**
 * Created by oleg on 14.01.18.
 */

fun RecyclerView.addEndlessOnScrollListener(init: EndlessOnScrollListenerHelper.() -> Unit) {
    val listener = EndlessOnScrollListenerHelper(this)
    listener.init()
    addOnScrollListener(listener.buildListener())
}


class EndlessOnScrollListenerHelper(val recyclerView: RecyclerView) {

    var footerAdapter: DefaultItemAdapter? = null
    var visibleThreshold: Int = -1
    var pagingDelegate: PagingMvpViewDelegate? = null

    private var loadMore: ((Int) -> Unit)? = null

    fun onLoadMore(onLoadMore: ((Int) -> Unit)?) {
        loadMore = onLoadMore
    }

    internal fun buildListener(): EndlessRecyclerOnScrollListener {
        val scrollListener = object: EndlessRecyclerOnScrollListener(recyclerView.layoutManager, visibleThreshold, footerAdapter) {
            override fun onLoadMore(currentPage: Int) {
                loadMore?.invoke(currentPage)
            }
        }


        pagingDelegate?.let {
            it.progressAdapter = footerAdapter
            it.scrollListener = scrollListener
        }

        return scrollListener

    }




}