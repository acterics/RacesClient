package com.acterics.racesclient.presentation.addbet.presenter

import com.acterics.racesclient.domain.interactor.AddBetUseCase
import com.acterics.racesclient.presentation.addbet.view.AddBetView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 09.11.17.
 */
@InjectViewState
class AddBetPresenter(private val router: Router,
                      private val addBetUseCase: AddBetUseCase):
        MvpPresenter<AddBetView>() {


    fun onBack() {
        router.exit()
    }
}