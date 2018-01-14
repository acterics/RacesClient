package com.acterics.racesclient.common.ui

import android.content.Context
import android.widget.Toast
import com.acterics.racesclient.common.ui.other.MatchParentProgressItem
import com.mikepenz.fastadapter_extensions.items.ProgressItem
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener

/**
 * Created by root on 03.11.17.
 */
class PagingMvpViewDelegate {

    var progressAdapter: DefaultItemAdapter? = null
    var scrollListener: EndlessRecyclerOnScrollListener? = null

    fun startPageLoading(isFirstPage: Boolean) {
        progressAdapter?.let {
            it.clear()
            it.add ( when (isFirstPage) {
                true -> MatchParentProgressItem()
                false -> ProgressItem()
            }
                    .withEnabled(true)
            )
        }
    }

    fun stopPageLoading() {
        progressAdapter?.clear()
    }

    fun resetPage(page: Int) {
        scrollListener?.resetPageCount(page)
    }

    fun showPageError(context: Context?, message: String?, isFirstPage: Boolean) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}