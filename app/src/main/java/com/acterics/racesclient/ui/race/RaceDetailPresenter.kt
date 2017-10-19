package com.acterics.racesclient.ui.race

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.checkStatus
import com.arellomobile.mvp.InjectViewState
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by root on 15.10.17.
 */


@InjectViewState
class RaceDetailPresenter: BaseNavigationPresenter<RaceDetailView>() {

    @Inject lateinit var apiService: ApiService

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

    fun onBack() {
        router.exit()
    }

    fun loadDetails(id: Long) {
        apiService.getRace(id)
                .checkStatus()
                .subscribeBy()
    }

}