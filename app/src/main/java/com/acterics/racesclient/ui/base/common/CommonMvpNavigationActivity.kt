package com.acterics.racesclient.ui.base.common

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.base.BaseMvpNavigationActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

/**
 * Created by root on 29.09.17.
 */
abstract class CommonMvpNavigationActivity: BaseMvpNavigationActivity() {
    override fun getNavigator(): Navigator {
        return object: SupportAppNavigator(this, R.id.holder_content) {
            override fun createActivityIntent(screenKey: String?, data: Any?): Intent {
                return getNavigationIntent(screenKey, data)
            }

            override fun createFragment(screenKey: String?, data: Any?): Fragment {
                return getFragment(screenKey, data)
            }

            override fun createStartActivityOptions(command: Command?, activityIntent: Intent?): Bundle? {
                return getStartActivityOptions(command, activityIntent)
            }

        }
    }

    open fun getStartActivityOptions(command: Command?, intent: Intent?): Bundle? {
        return null
    }
    abstract fun getNavigationIntent(screenKey: String?, data: Any?): Intent
    abstract fun getFragment(screenKey: String?, data: Any?): Fragment
}