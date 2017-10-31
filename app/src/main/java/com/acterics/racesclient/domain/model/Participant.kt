package com.acterics.racesclient.domain.model

/**
 * Created by root on 30.10.17.
 */
data class Participant(var id: Long,
                       var horse: Horse?,
                       var rating: Float,
                       var bets: List<Bet>)