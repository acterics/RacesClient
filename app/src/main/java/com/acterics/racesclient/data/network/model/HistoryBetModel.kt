package com.acterics.racesclient.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by root on 03.11.17.
 */
data class HistoryBetModel(
        var id: Long,
        var bet: Float,
        var rating: Float,

        @SerializedName("participant_id")
        var participantId: Long,
        @SerializedName("horse_name")
        var horseName: String,
        @SerializedName("race_date")
        var raceDate: Long,

        var success: Boolean,
        var result: Float)