package com.acterics.racesclient.data.model

import com.acterics.racesclient.data.entity.Horse
import org.joda.money.Money

/**
 * Created by root on 21.10.17.
 */
data class ParticipantModel(val id: Long,
                            val horse: HorseModel,
                            val rating: Float,
                            val currentBet: Money)