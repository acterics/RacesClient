package com.acterics.racesclient.presentation.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.Gravity
import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Screens
import com.acterics.racesclient.common.extentions.gone
import com.acterics.racesclient.common.extentions.visible
import com.acterics.racesclient.common.ui.SharedElementHolder
import com.acterics.racesclient.common.ui.SharedElementsHolder
import com.acterics.racesclient.common.ui.activity.CommonMvpNavigationActivity
import com.acterics.racesclient.common.ui.translation.AddBetTranslation
import com.acterics.racesclient.common.ui.translation.ScheduleRaceTranslation
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.presentation.addbet.view.AddBetFragment
import com.acterics.racesclient.presentation.authentication.AuthenticateActivity
import com.acterics.racesclient.presentation.editprofile.view.EditProfileActivity
import com.acterics.racesclient.presentation.main.presenter.MainActivityPresenter
import com.acterics.racesclient.presentation.profile.view.ProfileFragment
import com.acterics.racesclient.presentation.racedetails.view.RaceDetailFragment
import com.acterics.racesclient.presentation.schedule.view.ScheduleFragment
import com.acterics.racesclient.presentation.settings.SettingsFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by root on 08.10.17.
 */

class MainActivity: CommonMvpNavigationActivity(), MainActivityView {

    @Inject lateinit var router: Router
    @Inject lateinit var appContext: Context
    @Inject lateinit var navigationHolder: NavigatorHolder
    @InjectPresenter lateinit var presenter: MainActivityPresenter

    private var sharedView = WeakReference<View>(null)

    @ProvidePresenter
    fun provideMainPresenter(): MainActivityPresenter =
            MainActivityPresenter(router, appContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        vNavigationDrawer.setNavigationItemSelectedListener(presenter.onNavigationItemSelectedListener)

    }




    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent? =
        when(screenKey) {
            Screens.AUTHENTICATE -> Intent(this, AuthenticateActivity::class.java)
            Screens.EDIT_PROFILE -> {
                sharedView = WeakReference(data as View)
                Intent(this, EditProfileActivity::class.java)
            }
            else -> null
        }

    override fun getFragment(screenKey: String?, data: Any?): Fragment? =
        when(screenKey) {
            Screens.PROFILE -> ProfileFragment()
            Screens.RACES -> ScheduleFragment()
            Screens.SETTINGS -> SettingsFragment()
            Screens.RACE_DETAIL -> RaceDetailFragment().apply { scheduleRaceTranslation = data as ScheduleRaceTranslation }
            Screens.ADD_BET -> AddBetFragment().apply { addBetTranslation = data as AddBetTranslation }
            else -> null
        }?.also { presenter.onFragmentNavigation(it) }


    override fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
        when(command) {
            is Forward -> {
                when (currentFragment) {
                    is SharedElementHolder -> fragmentTransaction?.addSharedElement(currentFragment.getSharedView(), currentFragment.getSharedTranslationName())
                    is SharedElementsHolder -> currentFragment.getSharedElements().entries.forEach {
                        fragmentTransaction?.addSharedElement(it.value, it.key)
                    }
                }
            }
        }
    }

    override fun getStartActivityOptions(command: Command?, intent: Intent?): Bundle? {
       return if (command is Forward && command.screenKey == Screens.EDIT_PROFILE) {
           sharedView.get()?.let { view ->
               ActivityOptionsCompat
                       .makeSceneTransitionAnimation(this, view, getString(R.string.translation_name_edit_fab))
                       .toBundle()
           }


       } else {
           null
       }
    }


    override fun closeDrawer() {
        holderDrawer.closeDrawer(vNavigationDrawer)
    }

    override fun openDrawer() {
        holderDrawer.openDrawer(Gravity.START)
    }

    override fun onBackPressed() {
        if (holderDrawer.isDrawerOpen(vNavigationDrawer)) {
            closeDrawer()
        } else {
            super.onBackPressed()
        }
    }

    override fun injectComponent() {
        ComponentsManager.initMainComponent(this)
        ComponentsManager.mainComponent!!.inject(this)
    }

    override fun rejectComponent() {
        ComponentsManager.clearMainComponent()
    }

    override fun getInjectedNavigationHolder(): NavigatorHolder = navigationHolder

    override fun hideToolbar() { toolbar.gone() }

    override fun showToolbar() {
        with (toolbar) {
            visible()
            setNavigationOnClickListener({ openDrawer() })
        }
    }
}