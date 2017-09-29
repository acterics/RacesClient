package com.acterics.racesclient.ui.base.common

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.base.NavigatorProvider
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

/**
 * Created by root on 29.09.17.
 */
class CommonNavigatorProvider(private val navigationScreen: CommonNavigatorScreen,
                              private val fragmentActivity: FragmentActivity) : NavigatorProvider {
    override fun getNavigator(): Navigator {
        return object: SupportAppNavigator(fragmentActivity, R.id.holder_content) {
            override fun createActivityIntent(screenKey: String?, data: Any?): Intent? {
                return navigationScreen.getNavigationIntent(screenKey, data)
            }

            override fun createFragment(screenKey: String?, data: Any?): Fragment? {
                return navigationScreen.getFragment(screenKey, data)
            }

            override fun createStartActivityOptions(command: Command?, activityIntent: Intent?): Bundle? {
                return navigationScreen.getStartActivityOptions(command, activityIntent)
            }

            override fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
                return navigationScreen.setupFragmentTransactionAnimation(command, currentFragment, nextFragment, fragmentTransaction)
            }
        }
    }
}