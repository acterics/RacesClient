package com.acterics.racesclient.presentation.authentication.signin.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.acterics.racesclient.R
import com.acterics.racesclient.common.dsl.addTextChangeListener
import com.acterics.racesclient.common.ui.fragment.BaseScopedFragment
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.domain.interactor.SignInUseCase
import com.acterics.racesclient.presentation.authentication.signin.presenter.SignInPresenter
import com.acterics.racesclient.utils.validators.EmailValidator
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_sign_in.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by root on 29.09.17.
 */
class SignInFragment: BaseScopedFragment(), SignInView {


    @InjectPresenter lateinit var presenter: SignInPresenter
    @Inject lateinit var emailValidator: EmailValidator
    @Inject lateinit var router: Router
    @Inject lateinit var appContext: Context
    @Inject lateinit var signInUseCase: SignInUseCase

    @ProvidePresenter
    fun provideSignInPresenter() =
            SignInPresenter(emailValidator, signInUseCase, router)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        etSignInEmail.addTextChangeListener {
            onTextChanged { text, _, _, _ -> presenter.onEmailInputChanged(text) }
        }

        etSignInPassword.addTextChangeListener {
            onTextChanged { text, _, _, _ -> presenter.onPasswordInputChanged(text) }
        }

        btToSignUp.setOnClickListener{ presenter.onSignUpButtonClick() }
        btSignIn.setOnClickListener {
            presenter.onSignInButtonClick(
                    etSignInEmail.text.toString(),
                    etSignInPassword.text.toString()
            )
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_sign_in, container, false) ?: throw IllegalStateException()

    override fun showEmailInputError(errorRes: Int) {
        holderEmail?.error = getString(errorRes)
    }

    override fun hideEmailInputError() {
        holderEmail?.isErrorEnabled = false
    }

    override fun showPasswordInputError(errorRes: Int) {
        holderPassword?.error = getString(errorRes)
    }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun injectComponent() {
        ComponentsManager.authenticationComponent!!.inject(this)
    }
}