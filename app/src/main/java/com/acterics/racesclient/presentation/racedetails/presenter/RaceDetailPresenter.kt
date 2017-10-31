package com.acterics.racesclient.presentation.racedetails.presenter

import com.acterics.racesclient.domain.interactor.ConfirmBetUseCase
import com.acterics.racesclient.domain.interactor.GetRaceDetailsUseCase
import com.acterics.racesclient.domain.model.Race
import com.acterics.racesclient.presentation.racedetails.BetItem
import com.acterics.racesclient.presentation.racedetails.ParticipantItem
import com.acterics.racesclient.presentation.racedetails.ParticipantSubItem
import com.acterics.racesclient.presentation.racedetails.view.RaceDetailView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 15.10.17.
 */


@InjectViewState
class RaceDetailPresenter(private val router: Router,
                          private val getRaceDetailsUseCase: GetRaceDetailsUseCase,
                          private val confirmBetUseCase: ConfirmBetUseCase): MvpPresenter<RaceDetailView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.onViewAttached()
    }

    fun onBack() {
        router.exit()
    }



    fun loadDetails(id: Long) {
        viewState.startParticipantsLoading()
        getRaceDetailsUseCase.execute(
                params = GetRaceDetailsUseCase.Params(id),
                onSuccess = { onDetailsLoaded(it) },
                onError = { onDetailsLoadError(it) }
        )

    }


    private fun onDetailsLoaded(details: Race) {
        viewState.stopParticipantsLoading()
        viewState.showParticipants( details.participants!!
                .map { ParticipantItem(it).apply {
                    subItems.add(ParticipantSubItem().also {
                        it.withParent(this)
                        it.onConfirmBetListener = {bet, rating, participationId -> onConfirmBet(bet, rating, participationId, this) }
                    })
                }
                } )
    }

    private fun onDetailsLoadError(throwable: Throwable) {
        throwable.printStackTrace()
        viewState.stopParticipantsLoading()
        viewState.showError(throwable.message)
    }


    private fun onConfirmBet(bet: Float, rating: Float, participantId: Long, participantItem: ParticipantItem) {
        confirmBetUseCase.execute(
                params = ConfirmBetUseCase.Params(bet, rating, participantId),
                onSuccess = {
                    participantItem.apply {
                        betOn()
                        subItems.add(0, BetItem(it))
                        viewState.addNewBet(identifier, subItems.size - 1)
                    }
                },
                onError = {viewState.showError(it.message)}
        )
    }

}