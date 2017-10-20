package com.acterics.racesclient.ui.main

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by root on 09.10.17.
 */
abstract class MainDrawerFragment: MvpAppCompatFragment(), ToolbarHolder {


    protected open fun isLightTheme(): Boolean {
        return true
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as OnDrawerFragmentViewCreatedListener)
                .onDrawerFragmentViewCreated(this, isLightTheme())
    }
}