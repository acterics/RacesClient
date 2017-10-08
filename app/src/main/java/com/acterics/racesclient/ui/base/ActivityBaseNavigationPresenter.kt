package com.acterics.racesclient.ui.base

import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

/**
 * Created by root on 03.10.17.
 */
abstract class ActivityBaseNavigationPresenter<T: BaseMvpNavigationView>: BaseNavigationPresenter<T>() {
    @Inject
    lateinit var navigationHolder: NavigatorHolder


    fun onPause() {
        viewState.unregisterNavigator(navigationHolder)
    }

    fun onResume() {
        viewState.registerNavigator(navigationHolder)
    }
}