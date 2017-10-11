package com.acterics.racesclient.ui.main

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.ActionBarDrawerToggle
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.auth.AuthenticateActivity
import com.acterics.racesclient.ui.base.ActivityBaseNavigationPresenter
import com.acterics.racesclient.ui.base.common.CommonMvpNavigationActivity
import com.acterics.racesclient.ui.profile.ProfileFragment
import com.acterics.racesclient.ui.profile.edit.EditProfileActivity
import com.acterics.racesclient.ui.schedule.ScheduleFragment
import com.acterics.racesclient.ui.settings.SettingsFragment
import com.acterics.racesclient.utils.Screens
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by root on 08.10.17.
 */
class MainActivity: CommonMvpNavigationActivity(), MainActivityView {


    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vNavigationDrawer.setNavigationItemSelectedListener(presenter)

    }


    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent? {
        return when(screenKey) {
            Screens.AUTHENTICATE_SCREEN -> Intent(this, AuthenticateActivity::class.java)
            Screens.EDIT_PROFILE_SCREEN -> Intent(this, EditProfileActivity::class.java)
            else -> null
        }
    }

    override fun getFragment(screenKey: String?, data: Any?): Fragment? {
        val drawerFragment: MainDrawerFragment = when(screenKey) {
            Screens.PROFILE_SCREEN -> ProfileFragment()
            Screens.RACES_SCREEN -> ScheduleFragment()
            Screens.SETTINGS_SCREEN -> SettingsFragment()
            else -> return null
        }
        presenter.onDrawerFragmentChanged(drawerFragment)
        return drawerFragment


    }

    override fun onDrawerFragmentViewCreated(toolbarHolder: ToolbarHolder, lightTheme: Boolean) {
        setSupportActionBar(toolbarHolder.getToolbar())
        val toggle = ActionBarDrawerToggle(this, holderDrawer,
                toolbarHolder.getToolbar(), R.string.app_name, R.string.app_name)
        holderDrawer.addDrawerListener(toggle)
        toggle.syncState()
        toolbarHolder.getToolbar().navigationIcon = presenter.getNavigationIcon(lightTheme)

    }

    override fun getBasePresenter(): ActivityBaseNavigationPresenter<*> {
        return presenter
    }

    override fun closeDrawer() {
        holderDrawer.closeDrawer(vNavigationDrawer)
    }

    override fun onBackPressed() {
        if (holderDrawer.isDrawerOpen(vNavigationDrawer)) {
            closeDrawer()
        } else {
            super.onBackPressed()
        }
    }

}