package com.acterics.racesclient.ui.auth

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.base.BaseMvpNavigationActivity
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.ui.base.common.CommonMvpNavigationActivity
import com.acterics.racesclient.utils.getStatusBarSize
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import ru.terrakok.cicerone.commands.SystemMessage
import timber.log.Timber

/**
 * Created by root on 28.09.17.
 */
class AuthenticateActivity: CommonMvpNavigationActivity(), AuthenticateView {


    private val imLogo by lazy { findViewById<ImageView>(R.id.im_logo) }

    override fun getFragment(screenKey: String?, data: Any?): Fragment {
        TODO("not implemented")
    }

    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent {
        TODO("not implemented")
    }

    @InjectPresenter
    lateinit var presenter: AuthenticatePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticate)
        imLogo.setPadding(0, 0, 0, getStatusBarSize())
    }

    override fun onBackPressed() {
        finish()
    }

}