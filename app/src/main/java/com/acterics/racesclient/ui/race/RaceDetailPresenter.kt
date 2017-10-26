package com.acterics.racesclient.ui.race

import com.acterics.racesclient.BaseApplication
import com.acterics.racesclient.data.entity.Race
import com.acterics.racesclient.data.model.RaceModel
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.data.usecase.GetRaceDetails
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.ui.item.ParticipantItem
import com.arellomobile.mvp.InjectViewState
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by root on 15.10.17.
 */


@InjectViewState
class RaceDetailPresenter(): BaseNavigationPresenter<RaceDetailView>() {

    @Inject lateinit var getRaceDetails: GetRaceDetails

    override fun injectComponents() {
        BaseApplication.applicationComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.onViewAttached()
    }

    fun onBack() {
        router.exit()
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
        viewState.showRace(details)
        viewState.showParticipants( details.participants!!.map { ParticipantItem(it) } )
    }

    private fun onDetailsLoadError(throwable: Throwable) {
        viewState.stopParticipantsLoading()
        viewState.showError(throwable.message)
    }

}