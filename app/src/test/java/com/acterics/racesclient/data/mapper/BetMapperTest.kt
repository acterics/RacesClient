package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.Bet
import com.acterics.racesclient.ApplicationTestCase
import com.acterics.racesclient.data.database.entity.BetEntity
import com.acterics.racesclient.data.network.model.BetModel
import com.acterics.racesclient.data.network.model.request.BetRequest
import com.acterics.racesclient.di.TestComponentsManager
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/**
 * Created by oleg on 18.01.18.
 */
class BetMapperTest: ApplicationTestCase() {
    private val fakeValue = 30.1f
    private val fakeRate = 1.56f
    private val fakeParticipantId = 123L
    private val fakeBetId = 234L

    @Inject
    lateinit var betMapper: BetMapper


    @Before
    fun setup() {
        TestComponentsManager.initAppComponent(application())
        TestComponentsManager.testAppComponent.inject(this)
    }

    @Test
    fun testEntityToDomainMapping() {
        val entity = createFakeBetEntity()
        val bet = betMapper.toDomain(entity)

        assertThat(bet, `is`(instanceOf(Bet::class.java)))
        assertThat(bet.bet, `is`(fakeValue))
        assertThat(bet.rating, `is`(fakeRate))
    }


    @Test
    fun testModelToDomainMapping() {
        val model = createFakeBetModel()
        val bet = betMapper.toDomain(model)

        assertThat(bet, `is`(instanceOf(Bet::class.java)))
        assertThat(bet.bet, `is`(fakeValue))
        assertThat(bet.rating, `is`(fakeRate))

    }

    @Test
    fun testRequestToDomainMapping() {
        val request = createFakeBetRequest()
        val bet = betMapper.toDomain(request)

        assertThat(bet, `is`(instanceOf(Bet::class.java)))
        assertThat(bet.bet, `is`(fakeValue))
        assertThat(bet.rating, `is`(fakeRate))
    }


    @Test
    fun testModelToEntityMapping() {
        val model = createFakeBetModel()
        val entity = betMapper.toEntity(model)

        assertThat(entity, `is`(instanceOf(BetEntity::class.java)))
        assertThat(entity.value, `is`(fakeValue))
        assertThat(entity.rate, `is`(fakeRate))
        assertThat(entity.id, `is`(fakeBetId))
        assertThat(entity.participantId, `is`(fakeParticipantId))
    }


    private fun createFakeBetEntity() = BetEntity(fakeBetId, fakeValue, fakeRate, fakeParticipantId)
    private fun createFakeBetModel() = BetModel(fakeBetId, fakeValue, fakeRate, fakeParticipantId)
    private fun createFakeBetRequest() = BetRequest(fakeParticipantId, fakeValue, fakeRate)
}