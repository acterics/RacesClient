package com.acterics.racesclient.ui.main

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.design.widget.NavigationView
import android.support.v4.content.res.ResourcesCompat
import android.view.MenuItem
import com.acterics.racesclient.R
import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.ActivityBaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.logout
import com.arellomobile.mvp.InjectViewState
import javax.inject.Inject

/**
 * Created by root on 09.10.17.
 */
@InjectViewState
class MainActivityPresenter: ActivityBaseNavigationPresenter<MainActivityView>(),
        NavigationView.OnNavigationItemSelectedListener
{

    @Inject
    lateinit var context: Context

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.PROFILE_SCREEN)
    }

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.drawer_profile -> router.replaceScreen(Screens.PROFILE_SCREEN)
            R.id.drawer_schedule -> router.replaceScreen(Screens.RACES_SCREEN)
            R.id.drawer_settings -> router.replaceScreen(Screens.SETTINGS_SCREEN)
            R.id.drawer_exit -> onLogout()
            else -> return false
        }
        return true
    }

    fun onDrawerFragmentChanged(drawerFragment: MainDrawerFragment) {
        viewState.closeDrawer()
    }

    fun getNavigationIcon(lightTheme: Boolean): Drawable? {
        return if (lightTheme) {
            ResourcesCompat.getDrawable(context.resources, R.drawable.ic_menu_black, null)
        } else {
            ResourcesCompat.getDrawable(context.resources, R.drawable.ic_menu_white, null)
        }
    }


    private fun onLogout() {
        context.logout()
        router.newRootScreen(Screens.AUTHENTICATE_SCREEN)
    }

}