package com.acterics.racesclient.ui.base

import com.arellomobile.mvp.MvpAppCompatFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import timber.log.Timber

/**
 * Created by root on 29.09.17.
 */
abstract class BaseMvpNavigationFragment: MvpAppCompatFragment(),
        BaseMvpNavigationView, NavigatorProvider {


    override fun invalidCommand(command: Command) {
        Timber.w("Invalid command ${command.javaClass.name}")
    }

    override fun registerNavigator(navigationHolder: NavigatorHolder) {
        navigationHolder.setNavigator(getNavigator())
    }

    override fun unregisterNavigator(navigationHolder: NavigatorHolder) {
        navigationHolder.removeNavigator()
    }

}