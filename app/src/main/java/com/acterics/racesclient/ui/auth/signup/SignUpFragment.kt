package com.acterics.racesclient.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import timber.log.Timber

/**
 * Created by root on 02.10.17.
 */
class SignUpFragment: MvpAppCompatFragment(), SignUpView {

    private var holderRoot: View? = null


//    @InjectPresenter
//    lateinit var presenter: SignUpPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.i("onCreateView: ")
        holderRoot = inflater?.inflate(R.layout.fragment_sign_up, container, false)


        return holderRoot
    }




}