package com.acterics.racesclient.ui.profile.edit

import android.content.Intent
import android.support.v4.app.Fragment
import com.acterics.racesclient.ui.base.ActivityBaseNavigationPresenter
import com.acterics.racesclient.common.ui.activity.CommonMvpNavigationActivity
import com.arellomobile.mvp.presenter.InjectPresenter

/**
 * Created by root on 09.10.17.
 */
class EditProfileActivity: CommonMvpNavigationActivity(), EditProfileView {

    @InjectPresenter
    lateinit var presenter: EditProfilePresenter

    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent? {
        return null
    }

    override fun getFragment(screenKey: String?, data: Any?): Fragment? {
        return null
    }

    override fun getBasePresenter(): ActivityBaseNavigationPresenter<*> {
        return presenter
    }
}