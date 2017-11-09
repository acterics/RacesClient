package com.acterics.racesclient.presentation.main.view

import com.acterics.racesclient.common.ui.navigation.BaseMvpNavigationView

/**
 * Created by root on 09.10.17.
 */
interface MainActivityView: BaseMvpNavigationView {
    fun closeDrawer()
    fun openDrawer()
    fun hideToolbar()
    fun showToolbar()
}