package com.acterics.racesclient.presentation.schedule.presenter

import android.view.View
import com.acterics.domain.model.Race
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Screens
import com.acterics.racesclient.domain.interactor.GetRacesUseCase
import com.acterics.racesclient.presentation.schedule.ScheduleItem
import com.acterics.racesclient.presentation.schedule.view.ScheduleView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 15.10.17.
 */
@InjectViewState
class SchedulePresenter(private val router: Router,
                        private val getRacesUseCase: GetRacesUseCase):
        MvpPresenter<ScheduleView>() {

    private var page = -1
    private val pageSize = 10
    private var loading = false
    val sharedElements = HashMap<String, View?>()

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


    //TODO fix page duplicating issue
    fun onLoadMore(currentPage: Int) {
        if (currentPage > page || page == -1 && !loading) {
            loading = true
            viewState.startPageLoading(currentPage == 0)
            getRacesUseCase.execute(
                    params = GetRacesUseCase.Params(currentPage * pageSize, pageSize),
                    onSuccess = { races -> onSchedulePageLoaded(races, currentPage) },
                    onError = { throwable -> onSchedulePageLoadError(throwable) }
            )
        }
    }


    fun onScheduleItemClick(view: View?, item: ScheduleItem?) : Boolean {
        with(sharedElements) {
            clear()
            item?.scheduleRaceTranslation?.let {
                put(it.holderTranslationName, view)
                put(it.titleTranslationName, view?.findViewById(R.id.tvRaceTitle))
                put(it.organizerTranslationName, view?.findViewById(R.id.tvRaceOrganizer))
            }
        }
        viewState.startToolbarAnimation()
        router.navigateTo(Screens.RACE_DETAIL, item?.scheduleRaceTranslation)
        return true
    }

    private fun onSchedulePageLoaded(races: List<Race>, currentPage: Int) {
        page = currentPage
        viewState.stopPageLoading()
        viewState.showRaces(races.map {
            race -> ScheduleItem(race)
        })
        loading = false
    }

    private fun onSchedulePageLoadError(throwable: Throwable) {
        throwable.printStackTrace()
        viewState.stopPageLoading()
        viewState.showPagingError(throwable.message, false)
        loading = false
    }





}