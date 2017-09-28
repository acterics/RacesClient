package com.acterics.racesclient.ui.base

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.*
import timber.log.Timber

/**
 * Created by root on 28.09.17.
 */
abstract class BaseMvpNavigationActivity: MvpAppCompatActivity() {

    private val navigator = Navigator { command -> when (command) {
        is Forward -> forward(command)
        is Replace -> replace(command)
        is Back -> back(command)
        is SystemMessage -> systemMessage(command)
        else -> invalidCommand(command)
    } }

    abstract fun getRouter(): Router
    abstract fun getNavigationHolder(): NavigatorHolder
    abstract fun injectComponents()

    abstract fun forward(command: Forward)
    abstract fun replace(command: Replace)
    abstract fun back(command: Back)
    abstract fun systemMessage(command: SystemMessage)

    protected fun invalidCommand(command: Command) {
        Timber.w("Invalid command ${command.javaClass.name}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectComponents()
        super.onCreate(savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
        getNavigationHolder().setNavigator(navigator)
    }

    override fun onPause() {
        getNavigationHolder().removeNavigator()
        super.onPause()
    }

}