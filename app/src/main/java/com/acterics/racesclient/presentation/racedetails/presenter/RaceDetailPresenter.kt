package com.acterics.racesclient.presentation.racedetails.presenter

import android.view.View
import com.acterics.racesclient.common.extentions.Screens
import com.acterics.racesclient.common.ui.ExpandedCardProgressItem
import com.acterics.racesclient.common.ui.translation.AddBetTranslation
import com.acterics.racesclient.domain.interactor.ConfirmBetUseCase
import com.acterics.racesclient.domain.interactor.GetRaceDetailsUseCase
import com.acterics.racesclient.domain.model.Race
import com.acterics.racesclient.presentation.racedetails.view.item.BetItem
import com.acterics.racesclient.presentation.racedetails.view.item.ParticipantItem
import com.acterics.racesclient.presentation.racedetails.ParticipantSubItem
import com.acterics.racesclient.presentation.racedetails.view.RaceDetailView
import com.acterics.racesclient.presentation.racedetails.view.item.AddBetItem
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

    val sharedElements = HashMap<String, View?>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.onViewAttached()
    }

    fun onBack() {
        router.exit()
    }

    override fun detachView(view: RaceDetailView?) {
        super.detachView(view)
        sharedElements.clear()
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
        viewState.showParticipants( details.participants
                .map { ParticipantItem(it)
                        .apply { subItems.add(getAddBetSubItem()) }
                } )
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
        router.navigateTo(Screens.ADD_BET_SCREEN, addBetTranslation)
    }


//    private fun onConfirmBet(bet: Float, rating: Float, participantId: Long, participantItem: ParticipantItem) {
//        participantItem.subItems.apply {
//            removeAt(lastIndex)
//            add(ExpandedCardProgressItem())
//            viewState.notifyNewBet(participantItem.identifier, size)
//        }
//        confirmBetUseCase.execute(
//                params = ConfirmBetUseCase.Params(bet, rating, participantId),
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



    private fun getAddBetSubItem(): AddBetItem =
        AddBetItem().also {
            it.addBetClickListener = {translation, view -> onAddBet(translation, view) }
        }
}