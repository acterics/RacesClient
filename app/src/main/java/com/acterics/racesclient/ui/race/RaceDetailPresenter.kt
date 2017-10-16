package com.acterics.racesclient.ui.race

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.arellomobile.mvp.InjectViewState

/**
 * Created by root on 15.10.17.
 */


@InjectViewState
class RaceDetailPresenter: BaseNavigationPresenter<RaceDetailView>() {


    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

    fun onBack() {
        router.exit()
    }

}