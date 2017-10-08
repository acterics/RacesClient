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
abstract class BaseNavigationPresenter<T: MvpView>: MvpPresenter<T>() {

    @Inject
    lateinit var router: Router

    override fun attachView(view: T) {
        super.attachView(view)
        injectComponents()

    }

    protected abstract fun injectComponents()


}