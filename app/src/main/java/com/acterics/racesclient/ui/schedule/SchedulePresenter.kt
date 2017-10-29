package com.acterics.racesclient.ui.schedule

import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.BaseApplication
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.domain.interactor.GetRaces
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.common.extentions.Screens
import com.arellomobile.mvp.InjectViewState
import javax.inject.Inject

/**
 * Created by root on 15.10.17.
 */
@InjectViewState
class SchedulePresenter: BaseNavigationPresenter<ScheduleView>() {

    @Inject lateinit var getRaces: GetRaces

    private var page = -1
    private val pageSize = 10
    private var loading = false
    val sharedElements = HashMap<String, View?>()

    override fun injectComponents() {
        BaseApplication.applicationComponent.inject(this)
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

    override fun onDestroy() {
        super.onDestroy()
        getRaces.dispose()
    }
    //TODO fix page duplicating issue
    fun onLoadMore(currentPage: Int) {
        if (currentPage > page || page == -1 && !loading) {
            loading = true
            viewState.startScheduleLoading(currentPage == 0)
            getRaces.execute(
                    params = GetRaces.Params(currentPage * pageSize, pageSize),
                    onSuccess = { races -> onSchedulePageLoaded(races, currentPage) },
                    onError = { throwable -> onSchedulePageLoadError(throwable) }
            )
        }
    }


    fun onScheduleItemClick(view: View?, item: ScheduleItem?) : Boolean {
        sharedElements.clear()
        item?.let {
            sharedElements.put(item.scheduleRaceTranslation.holderTranslationName, view)
            sharedElements.put(item.scheduleRaceTranslation.titleTranslationName, view?.findViewById(R.id.tvRaceTitle))
            sharedElements.put(item.scheduleRaceTranslation.organizerTranslationName, view?.findViewById(R.id.tvRaceOrganizer))
        }
        router.navigateTo(Screens.RACE_DETAIL_SCREEN, item?.scheduleRaceTranslation)
        return true
    }

    private fun onSchedulePageLoaded(races: List<Race>, currentPage: Int) {
        page = currentPage
        viewState.stopScheduleLoading()
        viewState.showRaces(races
                .map { race -> ScheduleItem(race) })
        loading = false
    }

    private fun onSchedulePageLoadError(throwable: Throwable) {
        throwable.printStackTrace()
        viewState.stopScheduleLoading()
        viewState.showError(throwable.message)
        loading = false
    }





}