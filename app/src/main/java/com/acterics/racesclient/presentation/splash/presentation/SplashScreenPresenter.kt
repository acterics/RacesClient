package com.acterics.racesclient.presentation.splash.presentation

import android.content.Context
import android.os.Handler
import com.acterics.racesclient.common.constants.Screens
import com.acterics.racesclient.common.extentions.isAuthenticate
import com.acterics.racesclient.presentation.splash.view.SplashScreenView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 28.09.17.
 */

@InjectViewState
class SplashScreenPresenter(private val router: Router,
                            private val context: Context): MvpPresenter<SplashScreenView>() {

    companion object {
        const val DELAY_TIME = 600L
    }


    private val routingHandler = Handler()


    override fun attachView(view: SplashScreenView) {
        super.attachView(view)

        routingHandler.postDelayed({
            if (context.isAuthenticate()) {
                router.replaceScreen(Screens.MAIN)
            } else {
                router.replaceScreen(Screens.AUTHENTICATE)
            }
        }, DELAY_TIME)


    }

    override fun detachView(view: SplashScreenView) {
        super.detachView(view)
        routingHandler.removeCallbacksAndMessages(null)
    }

}