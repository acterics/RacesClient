package com.acterics.racesclient.ui.auth.signup

import android.content.Context
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.DebugTools
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.login
import com.arellomobile.mvp.InjectViewState
import javax.inject.Inject

/**
 * Created by root on 02.10.17.
 */
@InjectViewState
class SignUpPresenter: BaseNavigationPresenter<SignUpView>() {

    @Inject lateinit var context: Context
    @Inject lateinit var debugTools: DebugTools

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }


    fun onSignUpButtonClick() {
        context.login(debugTools.getUser())
        router.newRootScreen(Screens.MAIN_SCREEN)
    }
}