package com.acterics.racesclient.presentation.editprofile.presenter

import com.acterics.racesclient.presentation.editprofile.view.EditProfileView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import com.acterics.racesclient.data.database.entity.User

/**
 * Created by root on 09.10.17.
 */
@InjectViewState
class EditProfilePresenter(private val router: Router,
                           private val user: User) : MvpPresenter<EditProfileView>() {

    fun onAcceptClick() {
        viewState.performBackPress()
    }

}