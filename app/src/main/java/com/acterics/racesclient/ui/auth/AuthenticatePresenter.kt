package com.acterics.racesclient.ui.auth

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.arellomobile.mvp.InjectViewState

/**
 * Created by root on 29.09.17.
 */
@InjectViewState
class AuthenticatePresenter: BaseNavigationPresenter<AuthenticateView>() {

    override fun attachView(view: AuthenticateView) {
        super.attachView(view)
        router.navigateTo(Screens.SIGN_IN_SCREEN)
    }

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

}