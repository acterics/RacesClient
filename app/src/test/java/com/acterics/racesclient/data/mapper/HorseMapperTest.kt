package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.Bet
import com.acterics.domain.model.Horse
import com.acterics.racesclient.ApplicationTestCase
import com.acterics.racesclient.data.database.entity.BetEntity
import com.acterics.racesclient.data.database.entity.HorseEntity
import com.acterics.racesclient.data.network.model.HorseModel
import com.acterics.racesclient.di.TestComponentsManager
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/**
 * Created by oleg on 18.01.18.
 */
class HorseMapperTest: ApplicationTestCase() {

    private val fakeHorseId = 123L
    private val fakeHorseName = "Flash"

    @Inject
    lateinit var horseMapper: HorseMapper

    @Before
    fun setup() {
        TestComponentsManager.initAppComponent(application())
        TestComponentsManager.testAppComponent.inject(this)
    }


    @Test
    fun testEntityToDomainMapping() {
        val entity = createFakeHorseEntity()
        val horse = horseMapper.toDomain(entity)

        MatcherAssert.assertThat(horse, CoreMatchers.`is`(CoreMatchers.instanceOf(Horse::class.java)))
        MatcherAssert.assertThat(horse.id, CoreMatchers.`is`(fakeHorseId))
        MatcherAssert.assertThat(horse.name, CoreMatchers.`is`(fakeHorseName))
    }


    @Test
    fun testModelToDomainMapping() {
        val model = createFakeHorseModel()
        val horse = horseMapper.toDomain(model)

        MatcherAssert.assertThat(horse, CoreMatchers.`is`(CoreMatchers.instanceOf(Horse::class.java)))
        MatcherAssert.assertThat(horse.id, CoreMatchers.`is`(fakeHorseId))
        MatcherAssert.assertThat(horse.name, CoreMatchers.`is`(fakeHorseName))

    }

    @Test
    fun testModelToEntityMapping() {
        val model = createFakeHorseModel()
        val entity = horseMapper.toEntity(model)

        MatcherAssert.assertThat(entity, CoreMatchers.`is`(CoreMatchers.instanceOf(HorseEntity::class.java)))
        MatcherAssert.assertThat(entity.id, CoreMatchers.`is`(fakeHorseId))
        MatcherAssert.assertThat(entity.name, CoreMatchers.`is`(fakeHorseName))
    }


    private fun createFakeHorseEntity() = HorseEntity(fakeHorseId, fakeHorseName)
    private fun createFakeHorseModel() = HorseModel(fakeHorseId, fakeHorseName)
}