package com.acterics.racesclient.ui.auth

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.ImageView
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.auth.signin.SignInFragment
import com.acterics.racesclient.ui.auth.signup.SignUpFragment
import com.acterics.racesclient.ui.base.ActivityBaseNavigationPresenter
import com.acterics.racesclient.ui.base.common.CommonMvpNavigationActivity
import com.acterics.racesclient.utils.Screens
import com.acterics.racesclient.utils.getStatusBarSize
import com.acterics.racesclient.utils.keyboard.KeyboardMvpView
import com.acterics.racesclient.utils.keyboard.KeyboardPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_authenticate.*
import ru.terrakok.cicerone.commands.*
import timber.log.Timber

/**
 * Created by root on 28.09.17.
 */
class AuthenticateActivity: CommonMvpNavigationActivity(), AuthenticateView, KeyboardMvpView {


    companion object {
        val KEYBOARD_SLIDE_ANIMATION_DURATION = 150L
    }


    private var isAnimate = false

    @InjectPresenter
    lateinit var presenter: AuthenticatePresenter

    @InjectPresenter
    lateinit var keyboardPresenter: KeyboardPresenter


    private val signInFragment = SignInFragment()
    private val signUpFragment = SignUpFragment()

    override fun getFragment(screenKey: String?, data: Any?): Fragment {
        Timber.i("getFragment: $screenKey")
        return when(screenKey) {
            Screens.SIGN_IN_SCREEN -> SignInFragment()
            Screens.SIGN_UP_SCREEN -> SignUpFragment()
            else -> throw UnsupportedOperationException()
        }

    }

    override fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
        when (command) {
            is Replace -> {
                when(command.screenKey) {
                    Screens.SIGN_IN_SCREEN -> fragmentTransaction?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    Screens.SIGN_UP_SCREEN -> fragmentTransaction?.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_left_out)
                }
            }
            is Forward -> fragmentTransaction?.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out, R.anim.slide_right_in, R.anim.slide_right_out)
//            is Back -> fragmentTransaction?.setCustomAnimations(R.anim.slide_left_out, R.anim.slide_left_in)
        }
    }

    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent? {
        return null
    }

    fun onSignIn(view: View) {
        presenter.onSignIn()
        Timber.i("onSignIn: ")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_authenticate)
        keyboardPresenter.root = holderRoot
        imLogo?.setPadding(0, 0, 0, getStatusBarSize())
    }

    override fun onKeyboardVisibleChanged(visible: Boolean) {
        if (holderRoot!= null &&  !isAnimate) {
            if (visible)  { showKeyboardAnimatorSet() }
            else  { hideKeyboardAnimatorSet() }
                    .apply {
                        addListener(object: AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) { isAnimate = false }
                            override fun onAnimationStart(animation: Animator?) { isAnimate = true }
                        })
                        start()
                    }
        }
    }


    private fun showKeyboardAnimatorSet(): AnimatorSet {
        return AnimatorSet().apply {
            duration = KEYBOARD_SLIDE_ANIMATION_DURATION
            playTogether(
                    ObjectAnimator.ofFloat(imLogo, View.ALPHA, 1f, 0f),
                    ObjectAnimator.ofFloat(imLogo, View.TRANSLATION_Y, 0f, -imLogo.height.toFloat()),
                    ObjectAnimator.ofFloat(holderContent, View.TRANSLATION_Y, 0f, -imLogo.height.toFloat()))

        }
    }

    private fun hideKeyboardAnimatorSet(): AnimatorSet {
        return AnimatorSet().apply {
            duration = KEYBOARD_SLIDE_ANIMATION_DURATION
            playTogether(
                    ObjectAnimator.ofFloat(imLogo, View.ALPHA, 0f, 1f),
                    ObjectAnimator.ofFloat(imLogo, View.TRANSLATION_Y, -imLogo.height.toFloat(), 0f),
                    ObjectAnimator.ofFloat(holderContent, View.TRANSLATION_Y, -imLogo.height.toFloat(), 0f)
            )
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        presenter.onBackPressed()
//        finish()
    }


    override fun getBasePresenter(): ActivityBaseNavigationPresenter<*> {
        return presenter
    }


}