package com.acterics.racesclient.presentation.racedetails.presenter

import android.view.View
import com.acterics.domain.interactor.RaceInteractor
import com.acterics.domain.model.Race
import com.acterics.racesclient.common.constants.Screens
import com.acterics.racesclient.common.ui.translation.AddBetTranslation
import com.acterics.racesclient.presentation.racedetails.view.RaceDetailView
import com.acterics.racesclient.presentation.racedetails.view.item.AddBetItem
import com.acterics.racesclient.presentation.racedetails.view.item.ParticipantItem
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

/**
 * Created by root on 15.10.17.
 */


@InjectViewState
class RaceDetailPresenter(private val router: Router,
                          private val raceInteractor: RaceInteractor):
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
            raceInteractor.getRaceDetails(id)
                    .subscribeBy(
                            onSuccess = { race ->
                                onDetailsLoaded(race)
                            },
                            onError = { error ->
                                onDetailsLoadError(error)
                            }
                    )
        }

    }


    private fun onDetailsLoaded(details: Race) {
        viewState.stopParticipantsLoading()
        viewState.showParticipants( details.participants.map {
            ParticipantItem(it).apply {
                subItems.add(getAddBetSubItem(this))
            }
        })
        loaded = true
    }

    private fun onDetailsLoadError(throwable: Throwable) {
        throwable.printStackTrace()
        viewState.stopParticipantsLoading()
        viewState.showError(throwable.message)
    }


    private fun onAddBet(addBetTranslation: AddBetTranslation, view: View?) {
        with(sharedElements) {
            clear()
            put(addBetTranslation.addBetHolder, view)
        }
        viewState.startToolbarNavigation()
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
        AddBetItem().apply {
            withParent(participantItem)
            addBetClickListener = {translation, view -> onAddBet(translation, view) }
        }
}