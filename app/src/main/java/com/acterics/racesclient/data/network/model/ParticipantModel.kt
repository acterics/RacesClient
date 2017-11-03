package com.acterics.racesclient.data.network.model

/**
 * Created by root on 21.10.17.
 */
data class ParticipantModel(val id: Long,
                            val raceId: Long,
                            val horse: HorseModel,
                            val rating: Float,
                            val bets: MutableList<BetModel>)

