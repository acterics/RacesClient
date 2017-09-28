package com.acterics.racesclient.ui.splashscreen

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 28.09.17.
 */

@InjectViewState
class SplashScreenPresenter: BaseNavigationPresenter<SplashScreenView>() {
    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }


}