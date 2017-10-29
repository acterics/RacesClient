package com.acterics.racesclient.presentation.profile.general

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by root on 09.10.17.
 */
class ProfileGeneralFragment: MvpAppCompatFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_general, container, false)
    }
}