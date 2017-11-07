package com.acterics.racesclient.presentation.racedetails.view

import com.acterics.racesclient.presentation.racedetails.view.item.ParticipantItem
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*

/**
 * Created by root on 15.10.17.
 */
interface RaceDetailView: MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun onPresenterAttached()
    @StateStrategyType(SkipStrategy::class)
    fun showParticipants(participants: List<ParticipantItem>)
    fun startParticipantsLoading()
    fun stopParticipantsLoading()
    fun showError(message: String?)
    fun notifyNewBet(identifier: Long, previousSize: Int)
}