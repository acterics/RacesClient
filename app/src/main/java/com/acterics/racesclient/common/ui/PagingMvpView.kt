package com.acterics.racesclient.common.ui

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by root on 03.11.17.
 */
interface PagingMvpView: MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun startPageLoading(isFirstPage: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun stopPageLoading()

    @StateStrategyType(SkipStrategy::class)
    fun showPagingError(message: String?, isFirstPage: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun resetPage(page: Int)
}