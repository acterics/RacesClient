package com.acterics.racesclient.ui.auth.signin

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.base.common.CommonMvpNavigationFragment

/**
 * Created by root on 29.09.17.
 */
class SignInFragment: CommonMvpNavigationFragment(), SignInView {

    var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.fragment_sign_in, container, false)

        return rootView
    }

    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent {
        throw UnsupportedOperationException()
    }

    override fun getFragment(screenKey: String?, data: Any?): Fragment {
        throw UnsupportedOperationException()
    }



}