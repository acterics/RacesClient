package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.Bet
import com.acterics.domain.model.Horse
import com.acterics.racesclient.ApplicationTestCase
import com.acterics.racesclient.data.database.entity.HorseEntity
import com.acterics.racesclient.data.network.model.HorseModel
import com.acterics.racesclient.di.TestComponentsManager
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
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


    private val horseMapper: HorseMapper = HorseMapper()

    @Test
    fun entityToDomain() {
        val entity = createFakeHorseEntity()
        val horse = horseMapper.toDomain(entity)

        horse shouldBeInstanceOf Horse::class
        horse.name shouldEqual fakeHorseName
        horse.id shouldEqual fakeHorseId
    }


    @Test
    fun modelToDomain() {
        val model = createFakeHorseModel()
        val horse = horseMapper.toDomain(model)

        horse shouldBeInstanceOf Horse::class
        horse.name shouldEqual fakeHorseName
        horse.id shouldEqual fakeHorseId

    }

    @Test
    fun modelToEntity() {
        val model = createFakeHorseModel()
        val entity = horseMapper.toEntity(model)

        entity shouldBeInstanceOf HorseEntity::class
        entity.name shouldEqual fakeHorseName
        entity.id shouldEqual fakeHorseId
    }


    private fun createFakeHorseEntity() = HorseEntity(fakeHorseId, fakeHorseName)
    private fun createFakeHorseModel() = HorseModel(fakeHorseId, fakeHorseName)
}