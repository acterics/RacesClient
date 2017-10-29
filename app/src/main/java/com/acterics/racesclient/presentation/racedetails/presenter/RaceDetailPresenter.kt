package com.acterics.racesclient.presentation.racedetails.presenter

import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.domain.interactor.GetRaceDetails
import com.acterics.racesclient.presentation.racedetails.ParticipantItem
import com.acterics.racesclient.presentation.racedetails.view.RaceDetailView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router

/**
 * Created by root on 15.10.17.
 */


@InjectViewState
class RaceDetailPresenter(private val router: Router,
                          private val getRaceDetails: GetRaceDetails): MvpPresenter<RaceDetailView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.onViewAttached()
    }

    fun onBack() {
        router.exit()
    }

    override fun onDestroy() {
        super.onDestroy()
        getRaceDetails.dispose()
    }


    fun loadDetails(id: Long) {
        viewState.startParticipantsLoading()
        getRaceDetails
                .execute(
                        params = GetRaceDetails.Params(id),
                        onSuccess = { onDetailsLoaded(it) },
                        onError = { onDetailsLoadError(it) }
                )

    }


    private fun onDetailsLoaded(details: Race) {
        viewState.stopParticipantsLoading()
        viewState.showParticipants( details.participants!!.map { ParticipantItem(it) } )
    }

    private fun onDetailsLoadError(throwable: Throwable) {
        throwable.printStackTrace()
        viewState.stopParticipantsLoading()
        viewState.showError(throwable.message)
    }

}