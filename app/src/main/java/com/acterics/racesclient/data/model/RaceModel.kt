package com.acterics.racesclient.data.model

import com.acterics.racesclient.data.entity.Race
import org.joda.time.DateTime

/**
 * Created by root on 21.10.17.
 */
data class RaceModel(val id: Long,
                     val title: String,
                     val dateTime: DateTime,
                     val organizer: OrganizationModel,
                     val participants: List<ParticipantModel>): EntityWrapper<Race> {
    override fun map(): Race = Race(id, title, dateTime.millis, organizer.id,
            organizer = organizer.map(),
            dateTime = dateTime,
            participants = participants.map { it.map() })

}