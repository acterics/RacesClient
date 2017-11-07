package com.acterics.racesclient.presentation.authentication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.acterics.racesclient.R
import com.acterics.racesclient.common.constants.Screens
import com.acterics.racesclient.common.extentions.getStatusBarSize
import com.acterics.racesclient.common.ui.activity.CommonMvpNavigationActivity
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.presentation.authentication.signin.view.SignInFragment
import com.acterics.racesclient.presentation.authentication.signup.view.SignUpFragment
import com.acterics.racesclient.presentation.main.view.MainActivity
import com.acterics.racesclient.utils.keyboard.KeyboardMvpView
import com.acterics.racesclient.utils.keyboard.KeyboardPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_authenticate.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

/**
 * Created by root on 28.09.17.
 */
class AuthenticateActivity: CommonMvpNavigationActivity(), KeyboardMvpView {

    companion object {
        val KEYBOARD_SLIDE_ANIMATION_DURATION = 150L
    }

    private var isAnimate = false

    @InjectPresenter
    lateinit var keyboardPresenter: KeyboardPresenter

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    override fun getFragment(screenKey: String?, data: Any?): Fragment {
        return when(screenKey) {
            Screens.SIGN_IN -> SignInFragment()
            Screens.SIGN_UP -> SignUpFragment()
            else -> throw UnsupportedOperationException()
        }

    }

    override fun setupFragmentTransactionAnimation(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
        when (command) {
            is Replace -> {
                when(command.screenKey) {
                    Screens.SIGN_IN -> fragmentTransaction?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                }
            }
            is Forward -> fragmentTransaction?.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out, R.anim.slide_right_in, R.anim.slide_right_out)

        }
    }

    override fun getNavigationIntent(screenKey: String?, data: Any?): Intent? {
        return when(screenKey) {
            Screens.MAIN -> Intent(this, MainActivity::class.java)
            else -> null
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticate)
        keyboardPresenter.registrateView(holderRoot)
        router.replaceScreen(Screens.SIGN_IN)
        imLogo.setPadding(0, 0, 0, getStatusBarSize())
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

    override fun onBackPressed() {
        router.exit()
    }


    override fun injectComponent() {
        ComponentsManager.authenticationComponent!!.inject(this)
    }

    override fun rejectComponent() {
        ComponentsManager.clearAuthenticationComponent()
    }

    override fun getInjectedNavigationHolder(): NavigatorHolder {
        return navigationHolder
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




}