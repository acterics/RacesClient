package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.Organization
import com.acterics.racesclient.data.database.entity.OrganizationEntity
import com.acterics.racesclient.data.network.model.OrganizationModel
import javax.inject.Inject

/**
 * Created by root on 30.10.17.
 */
class OrganizationMapper
@Inject constructor() {
    fun toDomain(from: OrganizationEntity): Organization = Organization(from.id, from.name)
    fun toDomain(from: OrganizationModel): Organization = Organization(from.id, from.name)
    fun toEntity(from: OrganizationModel): OrganizationEntity = OrganizationEntity(from.id, from.name)
}