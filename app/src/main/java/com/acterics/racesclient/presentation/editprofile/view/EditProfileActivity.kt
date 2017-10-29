package com.acterics.racesclient.presentation.editprofile.view

import android.content.Intent
import android.support.v4.app.Fragment
import com.acterics.racesclient.common.ui.activity.CommonMvpNavigationActivity
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.presentation.editprofile.presenter.EditProfilePresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

/**
 * Created by root on 09.10.17.
 */
class EditProfileActivity: CommonMvpNavigationActivity(), EditProfileView {

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @InjectPresenter
    lateinit var presenter: EditProfilePresenter

    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent? {
        return null
    }

    override fun getFragment(screenKey: String?, data: Any?): Fragment? {
        return null
    }

    override fun injectComponent() {
        ComponentsManager.appComponent.inject(this)
    }

    override fun rejectComponent() {
        //Nothing to reject
    }

    override fun getInjectedNavigationHolder(): NavigatorHolder = navigationHolder
}