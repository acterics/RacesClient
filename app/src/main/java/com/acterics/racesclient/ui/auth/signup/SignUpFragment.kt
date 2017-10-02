package com.acterics.racesclient.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.base.common.CommonMvpNavigationFragment

/**
 * Created by root on 02.10.17.
 */
class SignUpFragment: CommonMvpNavigationFragment() {

    private var holderRoot: View? = null

    private val btSignUp by lazy { holderRoot?.findViewById<Button>(R.id.bt_sign_up) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        holderRoot = inflater?.inflate(R.layout.fragment_sign_up, container, false)


        return holderRoot
    }

    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFragment(screenKey: String?, data: Any?): Fragment? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}