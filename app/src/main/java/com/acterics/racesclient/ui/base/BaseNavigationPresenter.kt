package com.acterics.racesclient.ui.base

import com.acterics.racesclient.RacesApplication
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import okhttp3.Route
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by root on 28.09.17.
 */
abstract class BaseNavigationPresenter<T: BaseMvpNavigationView>: MvpPresenter<T>() {

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    override fun attachView(view: T) {
        super.attachView(view)
        injectComponents()
        view.registerNavigator(navigationHolder)
    }

    override fun detachView(view: T) {
        super.detachView(view)
        view.unregisterNavigator(navigationHolder)
    }

    protected abstract fun injectComponents()

}