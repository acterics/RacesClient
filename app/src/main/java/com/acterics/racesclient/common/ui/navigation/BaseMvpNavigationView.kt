package com.acterics.racesclient.common.ui.navigation

import com.arellomobile.mvp.MvpView
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command

/**
 * Created by root on 28.09.17.
 */
interface BaseMvpNavigationView: MvpView {

    fun invalidCommand(command: Command)
}