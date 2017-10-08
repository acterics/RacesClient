package com.acterics.racesclient.ui.auth.signin

import com.acterics.racesclient.R
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.validators.EmailValidator
import com.arellomobile.mvp.InjectViewState
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by root on 29.09.17.
 */
@InjectViewState
class SignInPresenter: BaseNavigationPresenter<SignInView>() {

    @Inject
    lateinit var emailValidator: EmailValidator

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

    override fun attachView(view: SignInView) {
        super.attachView(view)
        Timber.e("attachView: ")
    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Timber.e("onFirstViewAttach: ")
    }



    fun onEmailInputChanged(email: CharSequence?) {
        if (email?.isEmpty() != true && !emailValidator.validate(email)) {
            viewState.showEmailInputError(R.string.error_invalid_email)
        } else {
            viewState.hideEmailInputError()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("onDestroy: ")
    }

    fun onPasswordInputChanged(password: CharSequence?) {

    }

    fun onSignUpButtonClick() {
        Timber.i("onSignUpButtonClick: ")
        router.navigateTo(Screens.SIGN_UP_SCREEN)
    }


    fun ping() {
        Timber.e("ping:")
    }



}