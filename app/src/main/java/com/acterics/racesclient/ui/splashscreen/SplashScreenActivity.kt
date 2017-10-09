package com.acterics.racesclient.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.auth.AuthenticateActivity
import com.acterics.racesclient.ui.base.ActivityBaseNavigationPresenter
import com.acterics.racesclient.ui.base.common.CommonMvpNavigationActivity
import com.acterics.racesclient.ui.main.MainActivity
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.getStatusBarSize
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_splash.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Replace
import java.lang.UnsupportedOperationException

/**
 * Created by root on 27.09.17.
 */
class SplashScreenActivity: CommonMvpNavigationActivity(), SplashScreenView {

    @InjectPresenter
    lateinit var presenter: SplashScreenPresenter


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
            Screens.MAIN_SCREEN -> Intent(this, MainActivity::class.java)
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

    override fun getBasePresenter(): ActivityBaseNavigationPresenter<*> {
        return presenter
    }

}