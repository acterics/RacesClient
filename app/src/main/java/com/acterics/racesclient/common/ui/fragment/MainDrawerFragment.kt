package com.acterics.racesclient.common.ui.fragment

import android.os.Bundle
import android.view.View
import com.acterics.racesclient.common.ui.OnDrawerFragmentViewCreatedListener
import com.acterics.racesclient.common.ui.ToolbarHolder

/**
 * Created by root on 09.10.17.
 */
abstract class MainDrawerFragment: BaseScopedFragment(), ToolbarHolder {


    protected open fun isLightTheme(): Boolean {
        return true
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as OnDrawerFragmentViewCreatedListener)
                .onDrawerFragmentViewCreated(this, isLightTheme())
    }
}