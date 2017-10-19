package com.acterics.racesclient.data.model.response

import com.acterics.racesclient.data.entity.Horse
import com.acterics.racesclient.data.entity.Participant
import com.acterics.racesclient.data.entity.Race

/**
 * Created by root on 18.10.17.
 */
data class RaceDetailResponse(val race: Race,
                              val participants: List<Participant>)