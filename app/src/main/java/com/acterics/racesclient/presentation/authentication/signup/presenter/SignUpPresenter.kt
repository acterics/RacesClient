package com.acterics.racesclient.presentation.authentication.signup.presenter

import com.acterics.domain.interactor.AuthenticateInteractor
import com.acterics.racesclient.presentation.authentication.signup.view.SignUpView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 02.10.17.
 */
@InjectViewState
class SignUpPresenter(private val router: Router,
                      private val authenticateInteractor: AuthenticateInteractor):
        MvpPresenter<SignUpView>() {


    fun onSignUpButtonClick() {
//        apiService.signUp(SignUpRequest())
//        context.login(debugTools.getUser())
//        router.newRootScreen(Screens.MAIN_SCREEN)
    }
}