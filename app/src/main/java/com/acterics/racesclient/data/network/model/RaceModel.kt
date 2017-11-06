package com.acterics.racesclient.data.network.model

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

/**
 * Created by root on 21.10.17.
 */
data class RaceModel(val id: Long,
                     val title: String,

                     @SerializedName("date_time")
                     val dateTime: Long,
                     val organizer: OrganizationModel,
                     val participants: List<ParticipantModel>)