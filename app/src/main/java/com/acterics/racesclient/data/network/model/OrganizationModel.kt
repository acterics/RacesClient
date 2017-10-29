package com.acterics.racesclient.data.network.model

import com.acterics.racesclient.data.database.entity.Organization

/**
 * Created by root on 24.10.17.
 */
data class OrganizationModel(val id: Long,
                             val name: String): EntityWrapper<Organization> {
    override fun map(): Organization = Organization(id, name)

}