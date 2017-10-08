package com.acterics.racesclient.ui.auth.signup

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.arellomobile.mvp.InjectViewState

/**
 * Created by root on 02.10.17.
 */
@InjectViewState
class SignUpPresenter: BaseNavigationPresenter<SignUpView>() {
    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }


    fun onSignUpButtonClick() {
        //TODO add authorization logic
        router.newRootScreen(Screens.MAIN_SCREEN)
    }
}