package com.acterics.racesclient.data.model

import com.acterics.racesclient.data.entity.Organization

/**
 * Created by root on 24.10.17.
 */
data class OrganizationModel(val id: Long,
                             val name: String): EntityWrapper<Organization> {
    override fun map(): Organization = Organization(id, name)

}