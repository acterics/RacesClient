package com.acterics.racesclient.presentation.navigation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

/**
 * Created by root on 12.11.17.
 */
@InjectViewState
open class BaseToolbarAnimationPresenter: MvpPresenter<BaseToolbarAnimationView>() {

    private var attached = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.bindNavigationIcon()
        attached = true
    }

    override fun attachView(view: BaseToolbarAnimationView?) {
        if (attached) { viewState.postBindNavigationIcon() }
        super.attachView(view)

    }
}

