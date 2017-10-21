package com.acterics.racesclient.ui.schedule

import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.ui.item.ScheduleItem
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.checkStatus
import com.arellomobile.mvp.InjectViewState
import io.reactivex.disposables.Disposable
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
    private var disposable: Disposable? = null

    val sharedElements = HashMap<String, View?>()

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

    override fun attachView(view: ScheduleView) {
        super.attachView(view)
        if (page == -1) {
            viewState.resetPage(0)
        } else {
            viewState.resetPage(page)
        }

    }


    override fun detachView(view: ScheduleView?) {
        super.detachView(view)
        sharedElements.clear()
    }



    fun onLoadMore(currentPage: Int) {
        if (currentPage > page || page == -1) {
            viewState.startScheduleLoading(currentPage == 0)
            if (disposable?.isDisposed != false) {
                disposable = apiService.getSchedule(currentPage)
                        .checkStatus()
                        .map { it.races }
                        .subscribeBy(
                                onNext = { races -> onSchedulePageLoaded(races, currentPage) },
                                onError = { throwable -> onSchedulePageLoadError(throwable) }
                        )
            }
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
        Timber.e("onSchedulePageLoaded")
        viewState.stopScheduleLoading()
        viewState.showRaces(races.map { race -> ScheduleItem(race) })
        disposable?.dispose()
    }

    private fun onSchedulePageLoadError(throwable: Throwable) {
        viewState.stopScheduleLoading()
        viewState.showError(throwable.message)
        disposable?.dispose()
    }





}