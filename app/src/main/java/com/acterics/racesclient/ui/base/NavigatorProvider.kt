package com.acterics.racesclient.ui.base

import ru.terrakok.cicerone.Navigator

/**
 * Created by root on 29.09.17.
 */
interface NavigatorProvider {
    fun getNavigator(): Navigator
}