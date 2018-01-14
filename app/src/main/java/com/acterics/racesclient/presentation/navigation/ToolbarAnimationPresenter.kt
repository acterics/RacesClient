package com.acterics.racesclient.presentation.navigation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

/**
 * Created by root on 11.11.17.
 */
@InjectViewState
class ToolbarAnimationPresenter : MvpPresenter<ToolbarAnimationView>() {

    override fun attachView(view: ToolbarAnimationView?) {
        super.attachView(view)
        viewState.postBindNavigationIcon()
    }

    override fun detachView(view: ToolbarAnimationView?) {
        viewState.startBackToolbarAnimation()
        super.detachView(view)
    }
}