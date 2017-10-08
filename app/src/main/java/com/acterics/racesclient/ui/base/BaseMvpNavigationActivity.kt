package com.acterics.racesclient.ui.base

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.*
import timber.log.Timber

/**
 * Created by root on 28.09.17.
 */
abstract class BaseMvpNavigationActivity: MvpAppCompatActivity(),
        BaseMvpNavigationView, NavigatorProvider {

    override fun registerNavigator(navigationHolder: NavigatorHolder) {
        navigationHolder.setNavigator(getNavigator())
    }

    override fun unregisterNavigator(navigationHolder: NavigatorHolder) {
        navigationHolder.removeNavigator()
    }

    override fun invalidCommand(command: Command) {
        Timber.w("Invalid command ${command.javaClass.name}")
    }

    override fun onResume() {
        super.onResume()
        getBasePresenter().onResume()
    }

    override fun onPause() {
        super.onPause()
        getBasePresenter().onPause()
    }

    abstract fun getBasePresenter(): ActivityBaseNavigationPresenter<*>



}