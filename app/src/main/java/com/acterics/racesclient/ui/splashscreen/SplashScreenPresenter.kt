package com.acterics.racesclient.ui.splashscreen

import android.content.Context
import android.os.Handler
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.isAuthenticate
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by root on 28.09.17.
 */

@InjectViewState
class SplashScreenPresenter: BaseNavigationPresenter<SplashScreenView>() {

    companion object {
        const val DELAY_TIME = 1000L
    }

    @Inject
    lateinit var context: Context

    private val routingHandler = Handler()

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }


    override fun attachView(view: SplashScreenView) {
        super.attachView(view)

        routingHandler.postDelayed({
            if (context.isAuthenticate()) {
                router.replaceScreen(Screens.MAIN_SCREEN)
            } else {
                router.replaceScreen(Screens.AUTHENTICATE_SCREEN)
            }
        }, DELAY_TIME)


    }

    override fun detachView(view: SplashScreenView) {
        super.detachView(view)
        routingHandler.removeCallbacksAndMessages(null)
    }

}