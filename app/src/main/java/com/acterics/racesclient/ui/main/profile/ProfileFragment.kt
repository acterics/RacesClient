package com.acterics.racesclient.ui.main.profile

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.main.MainDrawerFragment
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by root on 09.10.17.
 */
class ProfileFragment: MainDrawerFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun getToolbar(): Toolbar {
        return profileToolbar
    }

}