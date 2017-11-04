package com.acterics.racesclient.domain.model.dto

import com.acterics.racesclient.domain.model.Bet
import org.joda.time.DateTime

/**
 * Created by root on 03.11.17.
 */
data class HistoryBet(var bet: Bet,
                      var date: DateTime,
                      var success: Boolean,
                      var result: Float,
                      var horseName: String)