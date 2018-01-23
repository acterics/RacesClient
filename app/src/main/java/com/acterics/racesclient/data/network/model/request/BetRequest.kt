package com.acterics.racesclient.data.network.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by root on 31.10.17.
 */
data class BetRequest(
        @SerializedName("participant_id")
        val participantId: Long = 0L,

        val bet: Float = 0.0f,
        val rating: Float = 0.0f)