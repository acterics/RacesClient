package com.acterics.racesclient.ui.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.acterics.racesclient.R
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_sign_up.*

/**
 * Created by root on 02.10.17.
 */
class SignUpFragment: MvpAppCompatFragment(), SignUpView {

    @InjectPresenter
    lateinit var presenter: SignUpPresenter

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
}