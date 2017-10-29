package com.acterics.racesclient.presentation.main

import android.os.Bundle
import android.view.View
import com.acterics.racesclient.common.ui.OnDrawerFragmentViewCreatedListener
import com.acterics.racesclient.common.ui.ToolbarHolder
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