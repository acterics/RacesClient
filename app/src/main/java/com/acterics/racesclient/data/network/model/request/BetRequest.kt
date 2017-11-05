package com.acterics.racesclient.data.network.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by root on 31.10.17.
 */
data class BetRequest(
        @SerializedName("participant_id")
        val participantId: Long,
        val bet: Float,
        val rating: Float)