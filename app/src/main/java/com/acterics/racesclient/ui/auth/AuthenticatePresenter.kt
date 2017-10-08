package com.acterics.racesclient.ui.auth

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.ActivityBaseNavigationPresenter
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.arellomobile.mvp.InjectViewState

/**
 * Created by root on 29.09.17.
 */
@InjectViewState
class AuthenticatePresenter: ActivityBaseNavigationPresenter<AuthenticateView>() {



    override fun attachView(view: AuthenticateView) {
        super.attachView(view)
        router.replaceScreen(Screens.SIGN_IN_SCREEN)
    }

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }


    fun onBackPressed() {
        router.exit()
    }


    fun onSignIn() {
        router.navigateTo(Screens.SIGN_UP_SCREEN)
    }

}