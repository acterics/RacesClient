package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.Organization
import com.acterics.racesclient.ApplicationTestCase
import com.acterics.racesclient.data.database.entity.OrganizationEntity
import com.acterics.racesclient.data.network.model.OrganizationModel
import com.acterics.racesclient.di.TestComponentsManager
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

    @Inject
    lateinit var organizationMapper: OrganizationMapper

    @Before
    fun setup() {
        TestComponentsManager.initAppComponent(ApplicationTestCase.application())
        TestComponentsManager.testAppComponent.inject(this)
    }

    @Test
    fun entityToDomain() {
        val entity = createFakeOrganizationEntity()
        val organization = organizationMapper.toDomain(entity)

        assertThat(organization, CoreMatchers.`is`(CoreMatchers.instanceOf(Organization::class.java)))
        assertThat(organization.id, CoreMatchers.`is`(fakeOrganizationId))
        assertThat(organization.name, CoreMatchers.`is`(fakeOrganizationName))
    }

    @Test
    fun modelToDomain() {
        val model = createFakeOrganizationModel()
        val organization = organizationMapper.toDomain(model)

        assertThat(organization, CoreMatchers.`is`(CoreMatchers.instanceOf(Organization::class.java)))
        assertThat(organization.id, CoreMatchers.`is`(fakeOrganizationId))
        assertThat(organization.name, CoreMatchers.`is`(fakeOrganizationName))
    }

    @Test
    fun modelToEntity() {
        val model = createFakeOrganizationModel()
        val entity = organizationMapper.toEntity(model)

        assertThat(entity, CoreMatchers.`is`(CoreMatchers.instanceOf(OrganizationEntity::class.java)))
        assertThat(entity.id, CoreMatchers.`is`(fakeOrganizationId))
        assertThat(entity.name, CoreMatchers.`is`(fakeOrganizationName))
    }

    private fun createFakeOrganizationEntity() = OrganizationEntity(fakeOrganizationId, fakeOrganizationName)
    private fun createFakeOrganizationModel() = OrganizationModel(fakeOrganizationId, fakeOrganizationName)

}