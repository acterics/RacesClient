package com.acterics.racesclient.ui.schedule

import android.os.Handler
import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.DebugTools
import com.acterics.racesclient.utils.Screens
import com.arellomobile.mvp.InjectViewState
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.IItem
import kotlinx.android.synthetic.main.item_race.view.*
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by root on 15.10.17.
 */
@InjectViewState
class SchedulePresenter: BaseNavigationPresenter<ScheduleView>() {


    @Inject lateinit var debugTools: DebugTools

    private val handler = Handler()
    private var page = 0
    val sharedElements = HashMap<String, View?>()

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }


    override fun attachView(view: ScheduleView) {
        super.attachView(view)
        if (page >= 0) {
            view.resetScrollListener(page)
        }
    }

    override fun detachView(view: ScheduleView?) {
        super.detachView(view)
        sharedElements.clear()
        handler.removeCallbacksAndMessages(null)
    }



    fun onLoadMore(currentPage: Int) {
        val isFirstPage = page == 0
        Timber.e("onLoadMore: page: $page currentPage: $currentPage")
        if (currentPage > page) {
            viewState.startScheduleLoading(isFirstPage)
            handler.postDelayed({
                with(viewState) {
                    page = currentPage
                    stopScheduleLoading()
                    showRaces(debugTools.getRacesPage(currentPage).map { ScheduleItem(it) })
                    Timber.e("onLoadedx: page: $page currentPage: $currentPage")
                }
            }, debugTools.getNetworkDelay())
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