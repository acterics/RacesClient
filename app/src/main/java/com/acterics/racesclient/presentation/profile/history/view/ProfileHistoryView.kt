package com.acterics.racesclient.presentation.profile.history.view

import com.acterics.racesclient.common.ui.PagingMvpView
import com.acterics.racesclient.domain.model.dto.HistoryBet
import com.acterics.racesclient.presentation.profile.history.HistoryBetItem
import com.arellomobile.mvp.MvpView

/**
 * Created by root on 03.11.17.
 */
interface ProfileHistoryView: PagingMvpView {
    fun showHistory(history: List<HistoryBetItem>)
}