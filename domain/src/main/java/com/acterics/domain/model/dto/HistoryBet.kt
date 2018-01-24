package com.acterics.domain.model.dto

import com.acterics.domain.model.Bet
import java.util.*

/**
 * Created by root on 03.11.17.
 */
data class HistoryBet(var bet: Bet = Bet(),
                      var date: Date = Date(),
                      var success: Boolean? = null,
                      var result: Float? = null,
                      var horseName: String = "")