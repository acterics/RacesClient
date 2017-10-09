package com.acterics.racesclient.ui.profile.edit

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.ActivityBaseNavigationPresenter
import com.arellomobile.mvp.InjectViewState

/**
 * Created by root on 09.10.17.
 */
@InjectViewState
class EditProfilePresenter: ActivityBaseNavigationPresenter<EditProfileView>() {

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

}