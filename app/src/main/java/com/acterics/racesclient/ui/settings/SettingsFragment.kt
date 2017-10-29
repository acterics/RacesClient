package com.acterics.racesclient.ui.settings

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.acterics.racesclient.presentation.main.MainDrawerFragment
import kotlinx.android.synthetic.main.fragment_settings.*

/**
 * Created by root on 09.10.17.
 */
class SettingsFragment: MainDrawerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun getToolbar(): Toolbar {
        return settingsToolbar
    }

}