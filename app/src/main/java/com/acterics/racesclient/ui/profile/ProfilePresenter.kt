package com.acterics.racesclient.ui.profile

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.Screens
import com.arellomobile.mvp.InjectViewState

/**
 * Created by root on 09.10.17.
 */
@InjectViewState
class ProfilePresenter: BaseNavigationPresenter<ProfileView>() {


    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
    }

    fun onEditProfileClicked() {
        router.navigateTo(Screens.EDIT_PROFILE_SCREEN)
    }
}