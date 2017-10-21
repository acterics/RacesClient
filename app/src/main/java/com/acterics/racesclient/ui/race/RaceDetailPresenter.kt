package com.acterics.racesclient.ui.race

import com.acterics.racesclient.RacesApplication
import com.acterics.racesclient.ui.item.ParticipantItem
import com.acterics.racesclient.data.rest.ApiService
import com.acterics.racesclient.ui.base.BaseNavigationPresenter
import com.acterics.racesclient.utils.checkStatus
import com.arellomobile.mvp.InjectViewState
import javax.inject.Inject

/**
 * Created by root on 15.10.17.
 */


@InjectViewState
class RaceDetailPresenter(): BaseNavigationPresenter<RaceDetailView>() {

    @Inject lateinit var apiService: ApiService

    override fun injectComponents() {
        RacesApplication.applicationComponent.inject(this)
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
        apiService.getRace(id)
                .checkStatus()
                .subscribeBy(
                        onNext = { onDetailsLoaded(it) },
                        onError = { onDetailsLoadError(it) }
                )
    }


    private fun onDetailsLoaded(details: RaceDetailResponse) {
        viewState.stopParticipantsLoading()
        viewState.showParticipants(details.participants.map { ParticipantItem(it) })
    }

    private fun onDetailsLoadError(throwable: Throwable) {
        viewState.stopParticipantsLoading()
        viewState.showError(throwable.message)
    }

}