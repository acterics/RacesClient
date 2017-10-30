package com.acterics.racesclient.data.network.model

import com.acterics.racesclient.data.database.entity.ParticipantEntity
import org.joda.money.Money

/**
 * Created by root on 21.10.17.
 */
data class ParticipantModel(val id: Long,
                            val raceId: Long,
                            val horse: HorseModel,
                            val rating: Float,
                            val currentBet: Money?)

