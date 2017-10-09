package com.acterics.racesclient.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.auth.AuthenticateActivity
import com.acterics.racesclient.ui.base.ActivityBaseNavigationPresenter
import com.acterics.racesclient.ui.base.common.CommonMvpNavigationActivity
import com.acterics.racesclient.ui.main.profile.ProfileFragment
import com.acterics.racesclient.utils.Screens
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by root on 08.10.17.
 */
class MainActivity: CommonMvpNavigationActivity(), MainActivityView {

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vNavigationDrawer.setNavigationItemSelectedListener(presenter)
    }


    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent? {
        return when(screenKey) {
            Screens.AUTHENTICATE_SCREEN -> Intent(this, AuthenticateActivity::class.java)
            else -> null
        }
    }

    override fun getFragment(screenKey: String?, data: Any?): Fragment? {
        return when(screenKey) {
            Screens.PROFILE_SCREEN -> ProfileFragment()
            else -> null
        }
    }

    override fun getBasePresenter(): ActivityBaseNavigationPresenter<*> {
        return presenter
    }
}