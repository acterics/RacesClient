package com.acterics.racesclient.ui.race

import com.acterics.racesclient.data.entity.Race
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
}