package com.acterics.racesclient.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.auth.AuthenticateActivity
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.ui.base.common.CommonMvpNavigationActivity
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.getStatusBarSize
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.*
import timber.log.Timber
import java.lang.UnsupportedOperationException

/**
 * Created by root on 27.09.17.
 */
class SplashScreenActivity: CommonMvpNavigationActivity(), SplashScreenView {

    @InjectPresenter
    lateinit var presenter: SplashScreenPresenter

    private val imLogo by lazy { findViewById<ImageView>(R.id.im_logo) }

    override fun getNavigator(): Navigator {
        return Navigator { command -> when (command) {
            is Replace -> {
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this, imLogo, "logo").toBundle()
                startActivity(getNavigationIntent(command.screenKey, null), options)
                imLogo.postDelayed( { finish() }, 500)
            }
            else -> throw UnsupportedOperationException()
        } }
    }

    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent {
        return when (screenKey) {
            Screens.AUTHENTICATE_SCREEN -> Intent(this, AuthenticateActivity::class.java)
            Screens.MAIN_SCREEN -> TODO("not implemented")
            else -> throw UnsupportedOperationException()
        }
    }

    override fun getFragment(screenKey: String?, data: Any?): Fragment {
        throw UnsupportedOperationException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setBackgroundDrawableResource(R.color.white)
        imLogo.setPadding(0, 0, 0, getStatusBarSize())
    }

}