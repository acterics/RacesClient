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
abstract class BaseMvpNavigationActivity: MvpAppCompatActivity(), BaseMvpNavigationView {


    abstract fun getNavigator(): Navigator

    override fun registerNavigator(navigationHolder: NavigatorHolder) {
        navigationHolder.setNavigator(getNavigator())
    }

    override fun unregisterNavigator(navigationHolder: NavigatorHolder) {
        navigationHolder.removeNavigator()
    }

    override fun invalidCommand(command: Command) {
        Timber.w("Invalid command ${command.javaClass.name}")
    }



}