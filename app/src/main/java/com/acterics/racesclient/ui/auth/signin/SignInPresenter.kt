package com.acterics.racesclient.ui.auth.signin

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter

/**
 * Created by root on 29.09.17.
 */
class SignInPresenter: BaseNavigationPresenter<SignInView>() {
    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

}