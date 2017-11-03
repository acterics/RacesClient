package com.acterics.racesclient.data.network.model

import org.joda.time.DateTime

/**
 * Created by root on 21.10.17.
 */
data class RaceModel(val id: Long,
                     val title: String,
                     val dateTime: DateTime,
                     val organizer: OrganizationModel,
                     val participants: List<ParticipantModel>)