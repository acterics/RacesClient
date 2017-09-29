package com.acterics.racesclient.ui.base.common

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.acterics.racesclient.ui.base.BaseMvpNavigationFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command

/**
 * Created by root on 29.09.17.
 */
abstract class CommonMvpNavigationFragment: BaseMvpNavigationFragment(), CommonNavigatorScreen {

    private val navigatorProvider by lazy { CommonNavigatorProvider(this, activity) }

    override fun getNavigator(): Navigator {
        return navigatorProvider.getNavigator()
    }

    override fun getStartActivityOptions(command: Command?, intent: Intent?): Bundle? {
        return null
    }

    override fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
        //Default implementation
    }

}