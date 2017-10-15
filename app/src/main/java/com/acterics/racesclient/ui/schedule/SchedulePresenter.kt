package com.acterics.racesclient.ui.schedule

import android.os.Handler
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.DebugTools
import com.arellomobile.mvp.InjectViewState
import javax.inject.Inject

/**
 * Created by root on 15.10.17.
 */
@InjectViewState
class SchedulePresenter: BaseNavigationPresenter<ScheduleView>() {


    @Inject lateinit var debugTools: DebugTools

    private val handler = Handler()
    private var page = -1

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }


    override fun attachView(view: ScheduleView) {
        super.attachView(view)
        if (page > 0) {
            view.resetScrollListener(page)
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.resetScrollListener(0)
    }

    override fun detachView(view: ScheduleView?) {
        super.detachView(view)
        handler.removeCallbacksAndMessages(null)

    }


    fun onLoadMore(currentPage: Int) {
        if (currentPage > page) {
            page = currentPage
            viewState.startScheduleLoading()
            handler.postDelayed({

                with(viewState) {
                    stopScheduleLoading()
                    showRaces(debugTools.getRacesPage(currentPage).map { ScheduleItem(it) })
                }
            }, debugTools.getNetworkDelay())
        }
    }

}