package com.acterics.racesclient.domain.model

import org.joda.time.DateTime

/**
 * Created by root on 30.10.17.
 */
data class Race(var id: Long,
                var title: String,
                var dateTime: DateTime,
                var organizer: Organization?,
                var participants: List<Participant>)
