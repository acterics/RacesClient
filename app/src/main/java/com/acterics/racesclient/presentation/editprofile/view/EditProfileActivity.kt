package com.acterics.racesclient.presentation.editprofile.view

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import com.acterics.racesclient.R
import com.acterics.racesclient.common.extentions.getUser
import com.acterics.racesclient.common.ui.activity.CommonMvpNavigationActivity
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.presentation.editprofile.presenter.EditProfilePresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by root on 09.10.17.
 */
class EditProfileActivity: CommonMvpNavigationActivity(), EditProfileView {

    @Inject lateinit var navigationHolder: NavigatorHolder
    @Inject lateinit var router: Router

    @InjectPresenter lateinit var presenter: EditProfilePresenter

    private val user by lazy { getUser() }

    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent? {
        return null
    }

    override fun getFragment(screenKey: String?, data: Any?): Fragment? {
        return null
    }

    override fun injectComponent() {
        ComponentsManager.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_edit_profile)
        super.onCreate(savedInstanceState)
        getUser()
        (btAccept.drawable as AnimatedVectorDrawable).start()
        Glide.with(this)
                .load(user.avatar)
                .error(R.color.dark_grey)
                .into(imAvatar)
        etFirstName.setText(user.firstName)
        etLastName.setText(user.lastName)
    }



    override fun onBackPressed() {
        val avd = ResourcesCompat.getDrawable(resources, R.drawable.avd_accept_to_edit, null) as AnimatedVectorDrawable
        btAccept.setImageDrawable(avd)
        avd.start()
        super.onBackPressed()
    }

    override fun rejectComponent() {
        //Nothing to reject
    }

    override fun getInjectedNavigationHolder(): NavigatorHolder = navigationHolder
}