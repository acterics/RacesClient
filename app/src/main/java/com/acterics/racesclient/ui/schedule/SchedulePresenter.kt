package com.acterics.racesclient.ui.schedule

import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.checkStatus
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.MvpViewState
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by root on 15.10.17.
 */
@InjectViewState
class SchedulePresenter: BaseNavigationPresenter<ScheduleView>() {

    @Inject lateinit var apiService: ApiService

    private var page = -1
    val sharedElements = HashMap<String, View?>()
    var shouldRestore = true

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

    //FIXME resolve bug with view state restoring after fragment destroy not detach
    override fun attachView(view: ScheduleView) {
//        var tempViewState = viewState
//        if (!shouldRestore) {
//            setViewState(null)
//
//        }
        super.attachView(view)
//        if (!shouldRestore) {
//            setViewState(tempViewState as MvpViewState<@JvmWildcard ScheduleView>)
//        }
        if (page == -1) {
            viewState.resetPage(0)
        } else {
            viewState.resetPage(page)
        }

        shouldRestore = false

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Timber.e("onFirstViewAttach: ")
    }


    override fun destroyView(view: ScheduleView?) {
        super.destroyView(view)
        Timber.e("destroyView: ")
    }

    override fun detachView(view: ScheduleView?) {
        super.detachView(view)
        Timber.e("detachView: ")
        sharedElements.clear()
    }


    fun onLoadMore(currentPage: Int) {
        Timber.e("onLoadMore: current $currentPage, page $page")
        if (currentPage > page || page == -1) {
            Timber.e("onLoadMore: loading")
            viewState.startScheduleLoading(currentPage == 0)
            apiService.getSchedule(currentPage)
                    .checkStatus()
                    .map { it.races }
                    .subscribeBy(
                            onNext = { races -> onSchedulePageLoaded(races, currentPage) },
                            onError = { throwable -> onSchedulePageLoadError(throwable) }
                    )
        }

    }


    fun onScheduleItemClick(view: View?, item: ScheduleItem?) : Boolean {
        sharedElements.clear()
        item?.let {
            sharedElements.put(item.holderTranslationName, view)
            sharedElements.put(item.titleTranslationName, view?.findViewById(R.id.tvRaceTitle))
            sharedElements.put(item.organizerTranslationName, view?.findViewById(R.id.tvRaceOrganizer))
        }
        router.navigateTo(Screens.RACE_DETAIL_SCREEN, item)
        return true
    }

    private fun onSchedulePageLoaded(races: List<Race>, currentPage: Int) {
        page = currentPage
        Timber.e("onSchedulePageLoaded: ${isInRestoreState(viewState)}")
        viewState.stopScheduleLoading()
        viewState.showRaces(races.map { race -> ScheduleItem(race) })
    }

    private fun onSchedulePageLoadError(throwable: Throwable) {
        viewState.stopScheduleLoading()
        viewState.showError(throwable.message)
    }





}