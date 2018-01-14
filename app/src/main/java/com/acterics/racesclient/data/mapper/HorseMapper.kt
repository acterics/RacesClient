package com.acterics.racesclient.data.mapper

import com.acterics.racesclient.data.database.entity.HorseEntity
import com.acterics.racesclient.data.network.model.HorseModel
import com.acterics.domain.model.Horse
import javax.inject.Inject

/**
 * Created by root on 30.10.17.
 */
class HorseMapper
@Inject constructor() {
    fun toDomain(from: HorseEntity): Horse = Horse(from.id, from.name)
    fun toDomain(from: HorseModel): Horse = Horse(from.id, from.name)
    fun toEntity(from: HorseModel): HorseEntity = HorseEntity(from.id, from.name)

}