package com.acterics.racesclient.presentation.signup.presenter

import android.content.Context
import com.acterics.racesclient.BaseApplication
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.presentation.signup.view.SignUpView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by root on 02.10.17.
 */
@InjectViewState
class SignUpPresenter(private val router: Router): MvpPresenter<SignUpView>() {


    fun onSignUpButtonClick() {
//        apiService.signUp(SignUpRequest())
//        context.login(debugTools.getUser())
//        router.newRootScreen(Screens.MAIN_SCREEN)
    }
}