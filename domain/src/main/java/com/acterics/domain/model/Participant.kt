package com.acterics.domain.model


/**
 * Created by root on 30.10.17.
 */
data class Participant(var id: Long = 0L,
                       var horse: Horse? = null,
                       var rating: Float = 0.0f,
                       var bets: List<Bet>? = null)