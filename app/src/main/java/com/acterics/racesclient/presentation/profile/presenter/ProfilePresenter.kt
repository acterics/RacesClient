package com.acterics.racesclient.presentation.profile.presenter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.res.ResourcesCompat
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Screens
import com.acterics.racesclient.common.extentions.getUser
import com.acterics.racesclient.common.extentions.isLight
import com.acterics.racesclient.common.extentions.transform
import com.acterics.racesclient.presentation.profile.ProfileViewModel
import com.acterics.racesclient.presentation.profile.view.ProfileView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 09.10.17.
 */
@InjectViewState
class ProfilePresenter(private val router: Router,
                       private val context: Context): MvpPresenter<ProfileView>() {



    override fun attachView(view: ProfileView) {
        super.attachView(view)
        view.showUser(context.getUser())
    }


    fun defineTheme(avatarBitmap: Bitmap, rectAvatar: Rect, rectUsername: Rect, rectNavIcon: Rect ) {
        val yOffset = rectAvatar.top
        val xOffset = rectAvatar.left

        val navIsLight = avatarBitmap
                .transform { Bitmap.createBitmap(it, rectNavIcon.left - xOffset,    rectNavIcon.top - yOffset,
                        rectNavIcon.width(), rectNavIcon.height()) }
                .transform { Bitmap.createScaledBitmap(it, 1, 1, false).getPixel(0, 0) }
                .isLight()

        val usernameIsLight = avatarBitmap
                .transform { Bitmap.createBitmap(it, rectUsername.left - xOffset, rectUsername.top - yOffset,
                        rectUsername.width(), rectUsername.height()) }
                .transform { Bitmap.createScaledBitmap(it, 1, 1, false).getPixel(0, 0) }
                .isLight()


        val drawable: Drawable?
        val drawableRes = if (navIsLight) {
            R.drawable.ic_menu_black
        } else {
            R.drawable.ic_menu_white
        }
        val textColor = if (usernameIsLight) {
            Color.BLACK
        } else {
            Color.WHITE
        }

        drawable = ResourcesCompat.getDrawable(context.resources, drawableRes, null)
        viewState.applyTheme(ProfileViewModel(drawable, textColor))
    }



    fun onEditProfileClicked() {
        router.navigateTo(Screens.EDIT_PROFILE)
    }



}