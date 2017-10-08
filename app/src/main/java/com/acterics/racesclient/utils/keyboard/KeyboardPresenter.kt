package com.acterics.racesclient.utils.keyboard

import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import timber.log.Timber

/**
 * Created by root on 03.10.17.
 */

@InjectViewState

class KeyboardPresenter: MvpPresenter<KeyboardMvpView>(), ViewTreeObserver.OnGlobalLayoutListener {

    private var keyboardVisible = false
    var root: View? = null


    override fun attachView(view: KeyboardMvpView?) {
        super.attachView(view)
        registerKeyboardListener()
    }

    override fun detachView(view: KeyboardMvpView?) {
        unregisterKeyboardListener()
        super.detachView(view)



    }

    override fun onGlobalLayout() {
        val b = isKeyboardShown(root?.rootView)
        if (b != keyboardVisible) {
            keyboardVisible = b

            if (!keyboardVisible) {
                viewState.onKeyboardVisibleChanged(false)
            } else {
                viewState.onKeyboardVisibleChanged(true)
            }
        }
    }


    private fun registerKeyboardListener() {
        root?.viewTreeObserver?.addOnGlobalLayoutListener(this) ?: Timber.w(IllegalStateException())
    }

    private fun unregisterKeyboardListener() {
        root?.viewTreeObserver?.removeOnGlobalLayoutListener(this) ?: Timber.w(IllegalStateException())
    }

    private fun isKeyboardShown(rootView: View?): Boolean {
        val r = Rect()
        rootView?.getWindowVisibleDisplayFrame(r)
        val dm = rootView?.resources?.displayMetrics ?: return false
        val heightDiff = (rootView.bottom) - r.bottom
        return heightDiff > dm.density * 64
    }
}