package com.acterics.racesclient.presentation.profile.history.presenter

import com.acterics.racesclient.domain.interactor.GetBetHistoryUseCase
import com.acterics.racesclient.presentation.profile.history.view.ProfileHistoryView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 03.11.17.
 */
@InjectViewState
class ProfileHistoryPresenter(private val router: Router,
                              private var getBetHistoryUseCase: GetBetHistoryUseCase):
        MvpPresenter<ProfileHistoryView>() {





    fun onLoadMore(page: Int) {

    }


}