package com.acterics.racesclient.presentation.profile.history.presenter

import com.acterics.racesclient.common.ui.DefaultItem
import com.acterics.racesclient.domain.interactor.GetBetHistoryUseCase
import com.acterics.racesclient.domain.model.dto.HistoryBet
import com.acterics.racesclient.presentation.profile.history.HistoryBetItem
import com.acterics.racesclient.presentation.profile.history.view.ProfileHistoryView
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

    fun onSortByDate() { viewState.sortBy(Comparator { object1, object2 ->
        historyItemComparator(object1, object2,
                { historyBet1, historyBet2 -> historyBet1.date.compareTo(historyBet2.date)})
    })}
    fun onSortByName() { viewState.sortBy(Comparator { object1, object2 ->
        historyItemComparator(object1, object2,
                { historyBet1, historyBet2 -> historyBet1.horseName.compareTo(historyBet2.horseName) })
    })}
    fun onSortByBet() { viewState.sortBy(Comparator { object1, object2 ->
        historyItemComparator(object1, object2,
                { historyBet1, historyBet2 -> historyBet1.bet.bet.compareTo(historyBet2.bet.bet) })
    })}
    fun onSortByResult() { viewState.sortBy(Comparator { object1, object2 ->
        historyItemComparator(object1, object2,
                { historyBet1, historyBet2 -> historyBet1.result.compareTo(historyBet2.result) })
    })}

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


    private fun historyItemComparator(object1: DefaultItem,
                                      object2: DefaultItem,
                                      comparator: (historyBet1: HistoryBet, historyBet2: HistoryBet) -> Int): Int {
        return if (object1 is HistoryBetItem && object2 is HistoryBetItem) {
            comparator.invoke(object1.historyBet, object2.historyBet)
        } else {
            -1
        }
    }

}