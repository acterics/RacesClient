package com.acterics.racesclient.presentation.profile.history.view

import com.acterics.racesclient.common.ui.DefaultItem
import com.acterics.racesclient.common.ui.PagingMvpView
import com.acterics.racesclient.presentation.profile.history.HistoryBetItem
import com.mikepenz.fastadapter.IItem

/**
 * Created by root on 03.11.17.
 */
interface ProfileHistoryView: PagingMvpView {
    fun showHistory(history: List<HistoryBetItem>)
    fun sortBy(comparator: Comparator<IItem<*, *>>)
}