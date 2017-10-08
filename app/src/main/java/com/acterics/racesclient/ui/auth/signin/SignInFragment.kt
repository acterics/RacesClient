package com.acterics.racesclient.ui.auth.signin

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.auth.signup.SignUpFragment
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import kotlinx.android.synthetic.main.fragment_sign_in.*
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import timber.log.Timber

/**
 * Created by root on 29.09.17.
 */
class SignInFragment: MvpAppCompatFragment(), SignInView {
    override fun onViewAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @InjectPresenter
    lateinit var presenter: SignInPresenter


    override fun onStart() {
        super.onStart()

        presenter.ping()

    }


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

        btToSignUp.setOnClickListener {
            //            fragmentManager.beginTransaction()
//                    .replace(R.id.holder_content, SignUpFragment())
//                    .addToBackStack(null)
//                    .commit()
            presenter.onSignUpButtonClick()
        }
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