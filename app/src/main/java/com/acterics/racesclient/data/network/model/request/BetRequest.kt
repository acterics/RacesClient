package com.acterics.racesclient.data.network.model.request

/**
 * Created by root on 31.10.17.
 */
data class BetRequest(val participantId: Long,
                      val bet: Float,
                      val rating: Float)