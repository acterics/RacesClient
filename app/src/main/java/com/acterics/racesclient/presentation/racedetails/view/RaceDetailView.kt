package com.acterics.racesclient.presentation.racedetails.view

import com.acterics.racesclient.presentation.racedetails.ParticipantItem
import com.arellomobile.mvp.MvpView

/**
 * Created by root on 15.10.17.
 */
interface RaceDetailView: MvpView {

    fun onViewAttached()
    fun showParticipants(participants: List<ParticipantItem>)
    fun startParticipantsLoading()
    fun stopParticipantsLoading()
    fun showError(message: String?)
    fun notifyNewBet(identifier: Long, previousSize: Int)
    fun notifyBetLoading(identifier: Long)
}