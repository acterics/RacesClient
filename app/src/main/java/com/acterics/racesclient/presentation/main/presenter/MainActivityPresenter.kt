package com.acterics.racesclient.presentation.main.presenter

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import android.view.MenuItem
import com.acterics.racesclient.R
import com.acterics.racesclient.common.extentions.Screens
import com.acterics.racesclient.common.extentions.logout
import com.acterics.racesclient.common.ui.fragment.MainDrawerFragment
import com.acterics.racesclient.domain.interactor.ConfirmBetUseCase
import com.acterics.racesclient.domain.interactor.GetRaceDetailsUseCase
import com.acterics.racesclient.domain.interactor.GetRacesUseCase
import com.acterics.racesclient.presentation.main.view.MainActivityView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 09.10.17.
 */
@InjectViewState
class MainActivityPresenter(private val router: Router,
                            private val context: Context,
                            private val getRacesUseCase: GetRacesUseCase,
                            private val getRaceDetailsUseCase: GetRaceDetailsUseCase,
                            private val confirmBetUseCase: ConfirmBetUseCase): MvpPresenter<MainActivityView>()
{

    val onNavigationItemSelectedListener = { menuItem: MenuItem ->  onNavigationItemSelected(menuItem) }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.PROFILE_SCREEN)
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
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

    override fun onDestroy() {
        super.onDestroy()
        getRaceDetailsUseCase.dispose()
        getRacesUseCase.dispose()
        confirmBetUseCase.dispose()
    }

    private fun onLogout() {
        context.logout()
        router.newRootScreen(Screens.AUTHENTICATE_SCREEN)
    }

}