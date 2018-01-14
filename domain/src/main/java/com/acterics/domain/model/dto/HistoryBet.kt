package com.acterics.domain.model.dto

import com.acterics.domain.model.Bet
import org.joda.time.DateTime

/**
 * Created by root on 03.11.17.
 */
data class HistoryBet(var bet: Bet,
                      var date: DateTime,
                      var success: Boolean?,
                      var result: Float?,
                      var horseName: String)