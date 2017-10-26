package com.acterics.racesclient.ui.auth.signin

import android.content.Context
import com.acterics.racesclient.R
import com.acterics.racesclient.BaseApplication
import com.acterics.racesclient.data.entity.User
import com.acterics.racesclient.data.model.request.SignInRequest
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.data.usecase.Authenticate
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.login
import com.acterics.racesclient.utils.validators.EmailValidator
import com.arellomobile.mvp.InjectViewState
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by root on 29.09.17.
 */
@InjectViewState
class SignInPresenter: BaseNavigationPresenter<SignInView>() {

    @Inject lateinit var emailValidator: EmailValidator
    @Inject lateinit var context: Context
    @Inject lateinit var authenticate: Authenticate

    override fun injectComponents() {
        BaseApplication.applicationComponent.inject(this)
    }

    fun onEmailInputChanged(email: CharSequence?) {
        if (email?.isEmpty() != true && !emailValidator.validate(email)) {
            viewState.showEmailInputError(R.string.error_invalid_email)
        } else {
            viewState.hideEmailInputError()
        }
    }


    fun onPasswordInputChanged(password: CharSequence?) {

    }

    fun onSignUpButtonClick() {
        router.navigateTo(Screens.SIGN_UP_SCREEN)
    }

    fun onSignInButtonClick(email: String, password: String) {
        //TODO add authorization logic
        authenticate
                .execute(params = SignInRequest(email, password),
                        onSuccess = { onSuccessLogin(it)},
                        onError = { viewState.showError(it.message) }
                )
    }


    private fun onSuccessLogin(user: User) {
        context.login(user)
        router.newRootScreen(Screens.MAIN_SCREEN)
    }





}