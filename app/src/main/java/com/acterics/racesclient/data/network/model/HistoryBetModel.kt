package com.acterics.racesclient.data.network.model

/**
 * Created by root on 03.11.17.
 */
data class HistoryBetModel(var id: Long,
                           var bet: Float,
                           var rating: Float,
                           var participantId: Long,
                           var raceDate: Long,
                           var win: Boolean)