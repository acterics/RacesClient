package com.acterics.racesclient.data.mapper

import com.acterics.domain.model.Bet
import com.acterics.racesclient.ApplicationTestCase
import com.acterics.racesclient.data.database.entity.BetEntity
import com.acterics.racesclient.data.network.model.BetModel
import com.acterics.racesclient.data.network.model.request.BetRequest
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * Created by oleg on 18.01.18.
 */
class BetMapperTest: ApplicationTestCase() {
    private val fakeValue = 30.1f
    private val fakeRate = 1.56f
    private val fakeParticipantId = 123L
    private val fakeBetId = 234L

    private val betMapper: BetMapper = BetMapper()


    @Test
    fun entityToDomain() {
        val entity = createFakeBetEntity()
        val bet = betMapper.toDomain(entity)

        bet shouldBeInstanceOf Bet::class
        bet.bet shouldEqual fakeValue
        bet.rating shouldEqual fakeRate
    }


    @Test
    fun modelToDomain() {
        val model = createFakeBetModel()
        val bet = betMapper.toDomain(model)

        bet shouldBeInstanceOf Bet::class
        bet.bet shouldEqual fakeValue
        bet.rating shouldEqual fakeRate

    }

    @Test
    fun requestToDomain() {
        val request = createFakeBetRequest()
        val bet = betMapper.toDomain(request)

        bet shouldBeInstanceOf Bet::class
        bet.bet shouldEqual fakeValue
        bet.rating shouldEqual fakeRate
    }


    @Test
    fun modelToEntity() {
        val model = createFakeBetModel()
        val entity = betMapper.toEntity(model)

        entity shouldBeInstanceOf BetEntity::class
        entity.value shouldEqual fakeValue
        entity.rate shouldEqual fakeRate
        entity.id shouldEqual fakeBetId
        entity.participantId shouldEqual fakeParticipantId
    }


    private fun createFakeBetEntity() = BetEntity(fakeBetId, fakeValue, fakeRate, fakeParticipantId)
    private fun createFakeBetModel() = BetModel(fakeBetId, fakeValue, fakeRate, fakeParticipantId)
    private fun createFakeBetRequest() = BetRequest(fakeParticipantId, fakeValue, fakeRate)
}