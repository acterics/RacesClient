package com.acterics.racesclient.ui.schedule

import android.os.Handler
import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.checkStatus
import com.arellomobile.mvp.InjectViewState
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


    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }


    override fun attachView(view: ScheduleView) {
        super.attachView(view)
        if (page == -1) {
            view.resetPage(0)
        } else {
            view.resetPage(page)
        }

    }

    override fun detachView(view: ScheduleView?) {
        super.detachView(view)
        sharedElements.clear()
    }


    fun onLoadMore(currentPage: Int) {
        Timber.e("onLoadMore: ")
        if (currentPage > page || page == -1) {
            viewState.startScheduleLoading(currentPage == 0)
            apiService.getSchedule(currentPage)
                    .checkStatus()
                    .map { it.races }
                    .subscribeBy(
                            onNext = { races ->
                                page = currentPage
                                viewState.stopScheduleLoading()
                                viewState.showRaces(races.map { race -> ScheduleItem(race) })
                            },
                            onError = { throwable ->
                                viewState.stopScheduleLoading()
                                viewState.showError(throwable.message)
                            }
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

}