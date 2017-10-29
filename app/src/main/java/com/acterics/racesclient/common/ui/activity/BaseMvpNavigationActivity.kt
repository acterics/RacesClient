package com.acterics.racesclient.common.ui.activity

import android.os.Bundle
import com.acterics.racesclient.common.ui.navigation.BaseMvpNavigationView
import com.acterics.racesclient.common.ui.navigation.NavigatorProvider
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import timber.log.Timber

/**
 * Created by root on 28.09.17.
 */
abstract class BaseMvpNavigationActivity: MvpAppCompatActivity(),
        BaseMvpNavigationView, NavigatorProvider {

    override fun invalidCommand(command: Command) {
        Timber.w("Invalid command ${command.javaClass.name}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectComponent()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        if (isFinishing) {
            rejectComponent()
        }
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        getInjectedNavigationHolder().setNavigator(getNavigator())

    }

    override fun onPause() {
        super.onPause()
        getInjectedNavigationHolder().removeNavigator()
    }

    abstract fun injectComponent()
    abstract fun rejectComponent()
    abstract fun getInjectedNavigationHolder(): NavigatorHolder



}