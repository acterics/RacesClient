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

    private val navigator = Navigator { command -> when (command) {
        is Forward -> forward(command)
        is Replace -> replace(command)
        is Back -> back(command)
        is SystemMessage -> systemMessage(command)
        else -> invalidCommand(command)
    } }

    abstract fun getBasePresenter(): BaseNavigationPresenter<*>

    override fun registerNavigator(navigationHolder: NavigatorHolder) {
        navigationHolder.setNavigator(navigator)
    }

    override fun unregisterNavigator(navigationHolder: NavigatorHolder) {
        navigationHolder.removeNavigator()
    }

    override fun invalidCommand(command: Command) {
        Timber.w("Invalid command ${command.javaClass.name}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
        getBasePresenter().onResume()

    }

    override fun onPause() {
        super.onPause()
        getBasePresenter().onPause()
    }

}