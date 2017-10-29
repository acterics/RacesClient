package com.acterics.racesclient.common.ui.navigation

import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import ru.terrakok.cicerone.commands.SystemMessage

/**
 * Created by root on 29.09.17.
 */
interface CustomMvpNavigationView: BaseMvpNavigationView {
    fun forward(command: Forward)
    fun replace(command: Replace)
    fun back(command: Back)
    fun systemMessage(command: SystemMessage)
}