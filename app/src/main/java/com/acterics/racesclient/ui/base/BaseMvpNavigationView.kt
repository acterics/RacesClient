package com.acterics.racesclient.ui.base

import com.arellomobile.mvp.MvpView
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command

/**
 * Created by root on 28.09.17.
 */
interface BaseMvpNavigationView: MvpView {

    fun invalidCommand(command: Command)
    fun registerNavigator(navigationHolder: NavigatorHolder)
    fun unregisterNavigator(navigationHolder: NavigatorHolder)
}