package com.acterics.racesclient.ui.base.common

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import ru.terrakok.cicerone.commands.Command

/**
 * Created by root on 29.09.17.
 */
interface CommonNavigatorScreen {
    fun getStartActivityOptions(command: Command?, intent: Intent?): Bundle?
    fun getNavigationIntent(screenKey: String?, data: Any?): Intent?
    fun getFragment(screenKey: String?, data: Any?): Fragment?
    fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?)
}