package com.acterics.racesclient.presentation.editprofile.view

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AlertDialog
import com.acterics.racesclient.R
import com.acterics.racesclient.common.extentions.getUser
import com.acterics.racesclient.common.ui.activity.CommonMvpNavigationActivity
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.presentation.editprofile.presenter.EditProfilePresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_edit_profile.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import android.provider.MediaStore
import com.acterics.racesclient.common.constants.FileConsts.PIC_URI
import com.acterics.racesclient.common.extentions.createDirIfNotExists
import com.acterics.racesclient.common.extentions.loadImage


/**
 * Created by root on 09.10.17.
 */
class EditProfileActivity: CommonMvpNavigationActivity(), EditProfileView {

    companion object {
        const val SOURCE_CAMERA = 0
        const val SOURCE_GALLERY = 1
    }

    @Inject lateinit var navigationHolder: NavigatorHolder
    @Inject lateinit var router: Router

    @InjectPresenter lateinit var presenter: EditProfilePresenter

    private val user by lazy { getUser() }
    private var newAvatarUri: Uri? = null

    @ProvidePresenter
    fun provideEditProfilePresenter() =
            EditProfilePresenter(router, user)

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

        with (btAccept) {
            (drawable as AnimatedVectorDrawable).start()
            setOnClickListener { presenter.onAcceptClick() }
        }
        imAvatar.loadImage(user.avatar, true)

        etFirstName.setText(user.firstName)
        etLastName.setText(user.lastName)

        imAvatar.setOnClickListener {
            AlertDialog.Builder(this, R.style.DialogStyle)
                    .setItems(arrayOf(getString(R.string.source_camera), getString(R.string.source_gallery)), { _, i -> onChangeAvatar(i) })
                    .setTitle(getString(R.string.change_avatar))
                    .create()
                    .show()
        }

    }




    override fun onBackPressed() {
        startAcceptButtonAnimation()
        super.onBackPressed()
    }

    override fun performBackPress() {
        onBackPressed()
    }

    override fun rejectComponent() {
        //Nothing to reject
    }

    override fun getInjectedNavigationHolder(): NavigatorHolder = navigationHolder

    override fun startAcceptButtonAnimation() {
        val avd = ResourcesCompat.getDrawable(resources, R.drawable.avd_accept_to_edit, null) as AnimatedVectorDrawable
        btAccept.setImageDrawable(avd)
        avd.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            SOURCE_GALLERY -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.data?.let {
                        newAvatarUri = it
                        imAvatar.loadImage(it, true)
                    }
                }
            }
        }
    }

    private fun onChangeAvatar(source: Int) {
        when(source) {
            SOURCE_CAMERA -> {
                if (createDirIfNotExists()) {
                    val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            .putExtra(MediaStore.EXTRA_OUTPUT, PIC_URI)
                    startActivityForResult(takePicture, SOURCE_CAMERA)
                }
            }
            SOURCE_GALLERY -> {
                val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(pickPhoto, SOURCE_GALLERY)
            }
        }
    }
}