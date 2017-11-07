package com.acterics.racesclient.presentation.splash.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Screens
import com.acterics.racesclient.common.extentions.getStatusBarSize
import com.acterics.racesclient.common.ui.activity.CommonMvpNavigationActivity
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.presentation.authentication.AuthenticateActivity
import com.acterics.racesclient.presentation.main.view.MainActivity
import com.acterics.racesclient.presentation.splash.presentation.SplashScreenPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_splash.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Replace
import java.lang.UnsupportedOperationException
import javax.inject.Inject

/**
 * Created by root on 27.09.17.
 */
class SplashScreenActivity: CommonMvpNavigationActivity(), SplashScreenView {


    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var appContext: Context

    @InjectPresenter
    lateinit var presenter: SplashScreenPresenter

    @ProvidePresenter
    fun provideSplashScreenPresenter(): SplashScreenPresenter = SplashScreenPresenter(router, appContext)

    override fun getNavigator(): Navigator {
        return Navigator { command -> when (command) {
            is Replace -> {
                val options: Bundle? = when(command.screenKey) {
                    Screens.AUTHENTICATE -> {
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this, imLogo, getString(R.string.translation_name_auth_logo)).toBundle()
                    }
                    else -> null
                }
                startActivity(getNavigationIntent(command.screenKey, null), options)
                imLogo.postDelayed( { finish() }, 500)
            }
            else -> throw UnsupportedOperationException()
        } }
    }

    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent {
        return when (screenKey) {
            Screens.AUTHENTICATE -> Intent(this, AuthenticateActivity::class.java)
            Screens.MAIN -> Intent(this, MainActivity::class.java)
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


    override fun injectComponent() {
        ComponentsManager.appComponent.inject(this)
    }

    override fun rejectComponent() {
        //Nothing to do
    }

    override fun getInjectedNavigationHolder(): NavigatorHolder = navigationHolder
}