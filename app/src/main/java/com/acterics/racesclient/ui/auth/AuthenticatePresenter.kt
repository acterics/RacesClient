package com.acterics.racesclient.ui.auth

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.arellomobile.mvp.InjectViewState

/**
 * Created by root on 29.09.17.
 */
@InjectViewState
class AuthenticatePresenter: BaseNavigationPresenter<AuthenticateView>() {



    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

}