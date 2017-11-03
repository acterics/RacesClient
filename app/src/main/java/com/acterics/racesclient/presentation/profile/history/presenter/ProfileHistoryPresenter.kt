package com.acterics.racesclient.presentation.profile.history.presenter

import com.acterics.racesclient.domain.interactor.GetBetHistoryUseCase
import com.acterics.racesclient.domain.interactor.GetRacesUseCase
import com.acterics.racesclient.domain.model.dto.HistoryBet
import com.acterics.racesclient.presentation.profile.history.HistoryBetItem
import com.acterics.racesclient.presentation.profile.history.view.ProfileHistoryView
import com.acterics.racesclient.presentation.schedule.ScheduleItem
import com.acterics.racesclient.presentation.schedule.view.ScheduleView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 03.11.17.
 */
//TODO code duplication with SchedulePresenter
@InjectViewState
class ProfileHistoryPresenter(private val router: Router,
                              private var getBetHistoryUseCase: GetBetHistoryUseCase):
        MvpPresenter<ProfileHistoryView>() {

    private var page = -1
    private val pageSize = 10
    private var loading = false


    override fun attachView(view: ProfileHistoryView) {
        super.attachView(view)
        if (page == -1) {
            viewState.resetPage(0)
        } else {
            viewState.resetPage(page)
        }

    }


    fun onLoadMore(currentPage: Int) {
        if (currentPage > page || page == -1 && !loading) {
            loading = true
            viewState.startPageLoading(currentPage == 0)
            getBetHistoryUseCase.execute(
                    params = GetBetHistoryUseCase.Params(currentPage * pageSize, pageSize),
                    onSuccess = { history -> onHistoryPageLoaded(history, currentPage) },
                    onError = { throwable -> onHistoryPageLoadError(throwable) }
            )
        }
    }

    private fun onHistoryPageLoaded(history: List<HistoryBet>, currentPage: Int) {
        page = currentPage
        viewState.stopPageLoading()
        viewState.showHistory(history.map { historyBet -> HistoryBetItem(historyBet) })
        loading = false
    }

    private fun onHistoryPageLoadError(throwable: Throwable) {
        throwable.printStackTrace()
        viewState.stopPageLoading()
        viewState.showPagingError(throwable.message, false)
        loading = false
    }


}