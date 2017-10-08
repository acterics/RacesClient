package com.acterics.racesclient.ui.base

import com.arellomobile.mvp.MvpAppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
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