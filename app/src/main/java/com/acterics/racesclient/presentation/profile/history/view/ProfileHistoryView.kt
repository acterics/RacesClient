package com.acterics.racesclient.presentation.profile.history.view

import com.acterics.racesclient.common.ui.PagingMvpView
import com.acterics.racesclient.presentation.profile.history.HistoryBetItem

/**
 * Created by root on 03.11.17.
 */
interface ProfileHistoryView: PagingMvpView {
    fun showHistory(history: List<HistoryBetItem>)
    fun sortByName()
    fun sortByBet()
    fun sortByDate()
}