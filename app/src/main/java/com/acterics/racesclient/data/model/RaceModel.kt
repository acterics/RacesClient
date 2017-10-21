package com.acterics.racesclient.data.model

import com.acterics.racesclient.data.entity.Organization
import org.joda.time.DateTime

/**
 * Created by root on 21.10.17.
 */
data class RaceModel(val id: Long,
                     val title: String,
                     val dateTime: DateTime,
                     val organizer: Organization,
                     val participants: List<ParticipantModel>)