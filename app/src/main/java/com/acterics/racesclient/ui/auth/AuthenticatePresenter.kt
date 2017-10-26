package com.acterics.racesclient.ui.auth

import com.acterics.racesclient.BaseApplication
import com.acterics.racesclient.ui.base.ActivityBaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.arellomobile.mvp.InjectViewState

/**
 * Created by root on 29.09.17.
 */
@InjectViewState
class AuthenticatePresenter: ActivityBaseNavigationPresenter<AuthenticateView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.SIGN_IN_SCREEN)
    }

    override fun injectComponents() {
        BaseApplication.applicationComponent.inject(this)
    }


    fun onBackPressed() {
        router.exit()
    }


}