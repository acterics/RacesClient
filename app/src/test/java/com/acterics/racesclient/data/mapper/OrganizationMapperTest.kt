package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.Horse
import com.acterics.domain.model.Organization
import com.acterics.racesclient.ApplicationTestCase
import com.acterics.racesclient.data.database.entity.OrganizationEntity
import com.acterics.racesclient.data.network.model.OrganizationModel
import com.acterics.racesclient.di.TestComponentsManager
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/**
 * Created by oleg on 18.01.18.
 */
class OrganizationMapperTest: ApplicationTestCase() {

    private val fakeOrganizationId = 123L
    private val fakeOrganizationName = "Organization"

    private val organizationMapper: OrganizationMapper = OrganizationMapper()



    @Test
    fun entityToDomain() {
        val entity = createFakeOrganizationEntity()
        val organization = organizationMapper.toDomain(entity)

        organization shouldBeInstanceOf Organization::class
        organization.name shouldEqual fakeOrganizationName
        organization.id shouldEqual fakeOrganizationId
    }

    @Test
    fun modelToDomain() {
        val model = createFakeOrganizationModel()
        val organization = organizationMapper.toDomain(model)

        organization shouldBeInstanceOf Organization::class
        organization.name shouldEqual fakeOrganizationName
        organization.id shouldEqual fakeOrganizationId
    }

    @Test
    fun modelToEntity() {
        val model = createFakeOrganizationModel()
        val entity = organizationMapper.toEntity(model)

        entity shouldBeInstanceOf OrganizationEntity::class
        entity.name shouldEqual fakeOrganizationName
        entity.id shouldEqual fakeOrganizationId
    }

    private fun createFakeOrganizationEntity() = OrganizationEntity(fakeOrganizationId, fakeOrganizationName)
    private fun createFakeOrganizationModel() = OrganizationModel(fakeOrganizationId, fakeOrganizationName)

}