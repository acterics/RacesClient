package com.acterics.racesclient.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by root on 03.11.17.
 */
data class HistoryBetModel(
        var id: Long = 0L,
        var bet: Float = 0f,
        var rating: Float = 0f,

        @SerializedName("participant_id")
        var participantId: Long = 0L,

        @SerializedName("horse_name")
        var horseName: String = "",

        @SerializedName("race_date")
        var raceDate: Long = 0L,

        var success: Boolean? = null,
        var result: Float? = null
)