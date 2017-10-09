package com.acterics.racesclient.ui.main

import android.support.v7.widget.Toolbar
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by root on 09.10.17.
 */
abstract class MainDrawerFragment: MvpAppCompatFragment() {


    abstract fun getToolbar(): Toolbar
}