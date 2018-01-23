package com.acterics.racesclient.data.repository

import com.acterics.domain.model.Bet
import com.acterics.domain.model.Participant
import com.acterics.racesclient.ApplicationTestCase
import com.acterics.racesclient.data.mapper.BetMapper
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.network.model.request.BetRequest
import com.acterics.racesclient.data.network.model.response.BaseResponse
import com.acterics.racesclient.data.network.model.response.BooleanResponse
import com.acterics.racesclient.exception.FailedToAddBetException
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.rubylichtenstein.rxtest.assertions.should
import com.rubylichtenstein.rxtest.assertions.shouldHave
import com.rubylichtenstein.rxtest.extentions.test
import com.rubylichtenstein.rxtest.matchers.complete
import com.rubylichtenstein.rxtest.matchers.noErrors
import com.rubylichtenstein.rxtest.matchers.subscribed
import io.reactivex.Single
import org.junit.Test

/**
 * Created by oleg on 22.01.18.
 */
class BetRepositoryImplTest: ApplicationTestCase() {


    private val successBooleanResponse = BooleanResponse(true)
    private val failBooleanResponse = BooleanResponse(false)

    private val fakeBetRequest = BetRequest()
    private val fakeBet = Bet()
    private val fakeParticipant = Participant()

    private val mockBetMapper: BetMapper by lazy { mock<BetMapper> {
        on { toRequest(fakeBet, fakeParticipant) } doReturn fakeBetRequest
    } }

    @Test
    fun addBet_successResponse_successResult() {
        val mockApiService = mock<ApiService> {
            on { addBet(fakeBetRequest) } doReturn Single.just(BaseResponse(BaseResponse.STATUS_SUCCESS, successBooleanResponse))
        }

        val betRepository = BetRepositoryImpl(mockApiService, mockBetMapper)


        betRepository.addBet(fakeBet, fakeParticipant)
                .test {
                    it should subscribed()
                    it should complete()
                    it shouldHave noErrors()
                }
        verify(mockApiService).addBet(fakeBetRequest)
        verify(mockBetMapper).toRequest(fakeBet, fakeParticipant)
    }

    @Test
    fun addBet_successResponse_failResult() {
        val mockApiService = mock<ApiService> {
            on { addBet(fakeBetRequest) } doReturn Single.just(BaseResponse(BaseResponse.STATUS_SUCCESS, failBooleanResponse))
        }
        val betRepository = BetRepositoryImpl(mockApiService, mockBetMapper)

        betRepository.addBet(fakeBet, fakeParticipant)
                .test {
                    it should subscribed()
                    it shouldHave com.rubylichtenstein.rxtest.matchers.error(FailedToAddBetException::class.java)
                }
        verify(mockApiService).addBet(fakeBetRequest)
        verify(mockBetMapper).toRequest(fakeBet, fakeParticipant)
    }





}