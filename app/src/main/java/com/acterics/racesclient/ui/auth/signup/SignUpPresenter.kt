package com.acterics.racesclient.ui.auth.signup

import android.content.Context
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.data.entity.User
import com.acterics.racesclient.data.model.request.SignUpRequest
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
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
    @Inject lateinit var apiService: ApiService

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }


    fun onSignUpButtonClick() {
//        apiService.signUp(SignUpRequest())
//        context.login(debugTools.getUser())
//        router.newRootScreen(Screens.MAIN_SCREEN)
    }
}