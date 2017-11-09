package com.acterics.racesclient.presentation.settings

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.acterics.racesclient.common.ui.ActionBarToggleBinder
import com.acterics.racesclient.common.ui.fragment.BaseScopedFragment
import com.acterics.racesclient.di.ComponentsManager
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

/**
 * Created by root on 09.10.17.
 */
class SettingsFragment: BaseScopedFragment() {

    @Inject
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var toggleBinder: ActionBarToggleBinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = getString(R.string.settings)
        toggleBinder.apply {
            this.toolbar = toolbar
        }.bind()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        toggleBinder.unbind()
    }

    override fun injectComponent() {
        ComponentsManager.mainComponent!!.inject(this)
    }
}