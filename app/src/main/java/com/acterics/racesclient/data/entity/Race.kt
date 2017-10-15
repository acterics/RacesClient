package com.acterics.racesclient.data.entity

import org.joda.time.DateTime
/**
 * Created by root on 15.10.17.
 */
data class Race(val title: String,
                val organizer: Organization,
                val date: DateTime)