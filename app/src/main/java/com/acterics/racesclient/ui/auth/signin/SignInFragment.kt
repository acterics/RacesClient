package com.acterics.racesclient.ui.auth.signin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_sign_in.*

/**
 * Created by root on 29.09.17.
 */
class SignInFragment: MvpAppCompatFragment(), SignInView {

    @InjectPresenter
    lateinit var presenter: SignInPresenter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etSignInEmail.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onEmailInputChanged(s)
            }
        })

        etSignInPassword.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.onPasswordInputChanged(s)
            }

        })

        btToSignUp.setOnClickListener{ presenter.onSignUpButtonClick() }
        btSignIn.setOnClickListener { presenter.onSignInButtonClick() }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false) ?: throw IllegalStateException()

    }

    override fun showEmailInputError(errorRes: Int) {
        holderEmail?.error = getString(errorRes)
    }
    override fun hideEmailInputError() {
        holderEmail?.isErrorEnabled = false
    }

    override fun showPasswordInputError(errorRes: Int) {
        holderPassword?.error = getString(errorRes)
    }




}