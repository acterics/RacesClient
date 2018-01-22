package com.acterics.racesclient.presentation.profile.history.presenter

import com.acterics.domain.interactor.ProfileInteractor
import com.acterics.domain.model.dto.HistoryBet
import com.acterics.domain.model.dto.Page
import com.acterics.racesclient.common.ui.DefaultItem
import com.acterics.racesclient.presentation.profile.history.HistoryBetItem
import com.acterics.racesclient.presentation.profile.history.view.ProfileHistoryView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

/**
 * Created by root on 03.11.17.
 */
//TODO code duplication with SchedulePresenter
@InjectViewState
class ProfileHistoryPresenter(private val router: Router,
                              private val profileInteractor: ProfileInteractor):
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
            profileInteractor.getBetHistory(Page(currentPage * pageSize, pageSize))
                    .subscribeBy(
                            onSuccess = { history ->
                                onHistoryPageLoaded(history, currentPage)
                            },
                            onError = { error ->
                                onHistoryPageLoadError(error)
                            }
                    )
        }
    }

    fun onSortByDate() { viewState.sortBy(Comparator { object1, object2 ->
        historyItemComparator(object1, object2,
                { historyBet1, historyBet2 -> historyBet2.date.compareTo(historyBet1.date)})
    })}
    fun onSortByName() { viewState.sortBy(Comparator { object1, object2 ->
        historyItemComparator(object1, object2,
                { historyBet1, historyBet2 -> historyBet1.horseName.compareTo(historyBet2.horseName) })
    })}
    fun onSortByBet() { viewState.sortBy(Comparator { object1, object2 ->
        historyItemComparator(object1, object2,
                { historyBet1, historyBet2 -> historyBet2.bet.bet.compareTo(historyBet1.bet.bet) })
    })}
    fun onSortByResult() { viewState.sortBy(Comparator { object1, object2 ->
        historyItemComparator(object1, object2,
                { historyBet1, historyBet2 -> (historyBet2.result?: Float.NEGATIVE_INFINITY).compareTo(historyBet1.result ?: Float.NEGATIVE_INFINITY) })
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