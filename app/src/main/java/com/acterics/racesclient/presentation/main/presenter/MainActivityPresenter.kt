package com.acterics.racesclient.presentation.main.presenter

import android.content.Context
import android.support.v4.app.Fragment
import android.view.MenuItem
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Screens
import com.acterics.racesclient.common.extentions.clearUser
import com.acterics.racesclient.common.ui.CustomToolbarHolder
import com.acterics.racesclient.presentation.main.view.MainActivityView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 09.10.17.
 */
@InjectViewState
class MainActivityPresenter(private val router: Router,
                            private val context: Context): MvpPresenter<MainActivityView>() {

    val onNavigationItemSelectedListener = { menuItem: MenuItem ->  onNavigationItemSelected(menuItem) }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.PROFILE)
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.drawer_profile -> router.replaceScreen(Screens.PROFILE)
            R.id.drawer_schedule -> router.replaceScreen(Screens.RACES)
            R.id.drawer_settings -> router.replaceScreen(Screens.SETTINGS)
            R.id.drawer_exit -> onLogout()
            else -> return false
        }
        return true
    }

    fun onFragmentNavigation(fragment: Fragment?) {
        when(fragment) {
            is CustomToolbarHolder -> { viewState.hideToolbar() }
            else -> viewState.showToolbar()
        }
        viewState.closeDrawer()
    }

    private fun onLogout() {
        context.clearUser()
        router.newRootScreen(Screens.AUTHENTICATE)
    }

}