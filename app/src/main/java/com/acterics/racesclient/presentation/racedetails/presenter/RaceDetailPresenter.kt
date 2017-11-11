package com.acterics.racesclient.presentation.racedetails.presenter

import android.view.View
import com.acterics.racesclient.common.constants.Screens
import com.acterics.racesclient.common.ui.translation.AddBetTranslation
import com.acterics.racesclient.domain.interactor.GetRaceDetailsUseCase
import com.acterics.racesclient.domain.model.Race
import com.acterics.racesclient.presentation.racedetails.view.RaceDetailView
import com.acterics.racesclient.presentation.racedetails.view.item.AddBetItem
import com.acterics.racesclient.presentation.racedetails.view.item.ParticipantItem
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber

/**
 * Created by root on 15.10.17.
 */


@InjectViewState
class RaceDetailPresenter(private val router: Router,
                          private val getRaceDetailsUseCase: GetRaceDetailsUseCase):
        MvpPresenter<RaceDetailView>() {

    val sharedElements = HashMap<String, View?>()
    var loaded = false



    override fun attachView(view: RaceDetailView?) {
        super.attachView(view)
        viewState.onPresenterAttached()
    }

    fun onBack() {
        router.exit()
    }

    override fun detachView(view: RaceDetailView?) {
        super.detachView(view)
        sharedElements.clear()
    }



    fun loadDetails(id: Long) {
        if (!loaded) {
            viewState.startParticipantsLoading()
            getRaceDetailsUseCase.execute(
                    params = GetRaceDetailsUseCase.Params(id),
                    onSuccess = { onDetailsLoaded(it) },
                    onError = { onDetailsLoadError(it) }
            )
        }

    }


    private fun onDetailsLoaded(details: Race) {
        viewState.stopParticipantsLoading()
        viewState.showParticipants( details.participants
                .map { ParticipantItem(it)
                        .apply { subItems.add(getAddBetSubItem(this)) }
                } )
        loaded = true
    }

    private fun onDetailsLoadError(throwable: Throwable) {
        throwable.printStackTrace()
        viewState.stopParticipantsLoading()
        viewState.showError(throwable.message)
    }


    private fun onAddBet(addBetTranslation: AddBetTranslation, view: View?) {
        sharedElements.apply {
            clear()
            put(addBetTranslation.addBetHolder, view)
        }
        viewState.startNavigationAnimation()
        router.navigateTo(Screens.ADD_BET, addBetTranslation)
    }


//    private fun onConfirmBet(bet: Float, rating: Float, participantId: Long, participantItem: ParticipantItem) {
//        participantItem.subItems.apply {
//            removeAt(lastIndex)
//            add(ExpandedCardProgressItem())
//            viewState.notifyNewBet(participantItem.identifier, size)
//        }
//        addBetUseCase.execute(
//                params = AddBetUseCase.Params(bet, rating, participantId),
//                onSuccess = {
//                    participantItem.subItems.apply {
//                        participantItem.betOn()
//                        removeAt(lastIndex)
//                        add(getAddBetSubItem(participantItem))
//                        add(0, BetItem(it))
//                        viewState.notifyNewBet(participantItem.identifier, size - 1)
//                    }
//                },
//                onError = {viewState.showError(it.message)}
//        )
//    }



    private fun getAddBetSubItem(participantItem: ParticipantItem): AddBetItem =
        AddBetItem().also {
            it.withParent(participantItem)
            it.addBetClickListener = {translation, view -> onAddBet(translation, view) }
        }
}