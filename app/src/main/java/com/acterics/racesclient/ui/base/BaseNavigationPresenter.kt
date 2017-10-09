package com.acterics.racesclient.ui.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by root on 28.09.17.
 */
abstract class BaseNavigationPresenter<T: MvpView>: MvpPresenter<T>() {

    @Inject
    lateinit var router: Router

    override fun attachView(view: T) {
        injectComponents()
        super.attachView(view)
    }

    protected abstract fun injectComponents()


}