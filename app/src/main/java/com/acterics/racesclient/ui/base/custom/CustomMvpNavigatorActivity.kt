package com.acterics.racesclient.ui.base.custom

import com.acterics.racesclient.ui.base.BaseMvpNavigationActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import ru.terrakok.cicerone.commands.SystemMessage

/**
 * Created by root on 29.09.17.
 */
abstract class CustomMvpNavigatorActivity: BaseMvpNavigationActivity(), CustomMvpNavigationView {

    override fun getNavigator(): Navigator {
        return Navigator { command -> when (command) {
            is Forward -> forward(command)
            is Replace -> replace(command)
            is Back -> back(command)
            is SystemMessage -> systemMessage(command)
            else -> invalidCommand(command)
        } }
    }

}