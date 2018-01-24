package com.acterics.racesclient.data.repository

import com.acterics.domain.model.Bet
import com.acterics.domain.model.Participant
import com.acterics.domain.model.dto.HistoryBet
import com.acterics.domain.model.dto.Page
import com.acterics.racesclient.ApplicationTestCase
import com.acterics.racesclient.data.mapper.BetMapper
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.network.model.HistoryBetModel
import com.acterics.racesclient.data.network.model.request.BetRequest
import com.acterics.racesclient.data.network.model.response.BaseResponse
import com.acterics.racesclient.data.network.model.response.BooleanResponse
import com.acterics.racesclient.data.network.model.response.HistoryResponse
import com.acterics.racesclient.exception.FailedToAddBetException
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.rubylichtenstein.rxtest.assertions.should
import com.rubylichtenstein.rxtest.assertions.shouldEmit
import com.rubylichtenstein.rxtest.assertions.shouldHave
import com.rubylichtenstein.rxtest.extentions.test
import com.rubylichtenstein.rxtest.matchers.complete
import com.rubylichtenstein.rxtest.matchers.noErrors
import com.rubylichtenstein.rxtest.matchers.subscribed
import io.reactivex.Single
import io.reactivex.functions.Predicate
import org.junit.Test
import org.mockito.internal.verification.Times

/**
 * Created by oleg on 22.01.18.
 */
class BetRepositoryImplTest: ApplicationTestCase() {


    private val successBooleanResponse = BooleanResponse(true)
    private val failBooleanResponse = BooleanResponse(false)

    private val fakeBetRequest = BetRequest()
    private val fakeBet = Bet()
    private val fakeParticipant = Participant()
    private val fakeHistoryBetModel = HistoryBetModel()
    private val fakeHistoryBet = HistoryBet()

    private val mockBetMapper: BetMapper by lazy { mock<BetMapper> {
        on { toRequest(fakeBet, fakeParticipant) } doReturn fakeBetRequest
        on { toDto(fakeHistoryBetModel) } doReturn fakeHistoryBet
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


    @Test
    fun getBetHistory_successResponse_filledList_noCaching() {
        val size = 5
        val bets = (1..size).map { fakeHistoryBetModel }
        val historyResponse = HistoryResponse(bets)
        val page = Page()

        val mockApiService = mock<ApiService> {
            on { getBets(page.skip, page.count) } doReturn Single.just(BaseResponse(BaseResponse.STATUS_SUCCESS, historyResponse))
        }

        val betRepository = BetRepositoryImpl(mockApiService, mockBetMapper)



        betRepository.getBetHistory(page, false)
                .test {
                    it should subscribed()
                    it shouldHave noErrors()
                    it should complete()
                    it shouldEmit Predicate { it.size == size }
                    it shouldEmit Predicate { it.all { bet -> bet == fakeHistoryBet } }
                }
        verify(mockApiService).getBets(page.skip, page.count)
        verify(mockBetMapper, Times(size)).toDto(fakeHistoryBetModel)
    }









}