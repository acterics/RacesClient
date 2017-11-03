package com.acterics.racesclient.common.ui

import android.content.Context
import android.widget.Toast
import com.mikepenz.fastadapter_extensions.items.ProgressItem
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener

/**
 * Created by root on 03.11.17.
 */
class PagingMvpViewDelegate {

    fun <T : DefaultItemAdapter> startPageLoading(progressAdapter: T, isFirstPage: Boolean) {
        progressAdapter.clear()
        val progressItem = if (isFirstPage) {
            MatchParentProgressItem()
        } else {
            ProgressItem()
        }
                .withEnabled(true)
        progressAdapter.add(progressItem)
    }

    fun <T : DefaultItemAdapter> stopPageLoading(progressAdapter: T) {
        progressAdapter.clear()
    }

    fun <T : EndlessRecyclerOnScrollListener> resetPage(scrollListener: T, page: Int) {
        scrollListener.resetPageCount(page)
    }

    fun showPageError(context: Context, message: String?, isFirstPage: Boolean) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}