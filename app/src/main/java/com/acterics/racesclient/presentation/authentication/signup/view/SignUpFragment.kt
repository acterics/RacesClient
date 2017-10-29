package com.acterics.racesclient.presentation.authentication.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.acterics.racesclient.R
import com.acterics.racesclient.common.ui.fragment.BaseScopedFragment
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.presentation.authentication.signup.presenter.SignUpPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_sign_up.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by root on 02.10.17.
 */
class SignUpFragment: BaseScopedFragment(), SignUpView {

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: SignUpPresenter

    @ProvidePresenter
    fun provideSignUpPresenter(): SignUpPresenter = SignUpPresenter(router)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_sign_up, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btSignUp.setOnClickListener { presenter.onSignUpButtonClick() }
    }

    override fun showError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun injectComponent() {
        ComponentsManager.authenticationComponent!!.inject(this)
    }
}