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
import com.acterics.racesclient.ui.base.common.CommonMvpNavigationFragment
import com.acterics.racesclient.utils.Screens
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

/**
 * Created by root on 29.09.17.
 */
class SignInFragment: CommonMvpNavigationFragment(), SignInView {


    private var rootView: View? = null
    private val holderEmail by lazy { rootView?.findViewById<TextInputLayout>(R.id.holder_email) }
    private val holderPassword by lazy { rootView?.findViewById<TextInputLayout>(R.id.holder_password) }
    private val etEmail by lazy { rootView?.findViewById<TextInputEditText>(R.id.et_email) }
    private val etPassword by lazy { rootView?.findViewById<TextInputEditText>(R.id.et_password) }
    private val btSignUp by lazy { rootView?.findViewById<Button>(R.id.bt_sign_up) }

    @InjectPresenter
    lateinit var presenter: SignInPresenter


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.fragment_sign_in, container, false)

        etEmail?.addTextChangedListener( object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.onEmailInputChanged(s)
            }
        })
        etPassword?.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.onPasswordInputChanged(s)
            }

        })

        btSignUp?.setOnClickListener { presenter.onSignUpButtonClick() }




        return rootView
    }

    override fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
        if (command is Forward && command.screenKey == Screens.SIGN_UP_SCREEN) {
            fragmentTransaction?.setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
        }

    }

    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent {
        throw UnsupportedOperationException()
    }

    override fun getFragment(screenKey: String?, data: Any?): Fragment {
        return when (screenKey) {
            Screens.SIGN_UP_SCREEN -> SignUpFragment()
            else -> throw IllegalStateException()
        }
    }


    override fun showEmailInputError(errorRes: Int) {
        holderEmail?.error = getString(errorRes)
    }

    override fun showPasswordInputError(errorRes: Int) {
        holderPassword?.error = getString(errorRes)
    }



}