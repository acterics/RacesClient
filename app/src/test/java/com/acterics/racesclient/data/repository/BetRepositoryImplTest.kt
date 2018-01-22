package com.acterics.racesclient.data.repository

import com.acterics.domain.model.Bet
import com.acterics.domain.model.Participant
import com.acterics.racesclient.ApplicationTestCase
import com.acterics.racesclient.data.mapper.BetMapper
import com.acterics.racesclient.data.network.ApiService
import com.acterics.racesclient.data.network.model.request.BetRequest
import com.acterics.racesclient.data.network.model.response.BaseResponse
import com.acterics.racesclient.data.network.model.response.BooleanResponse
import com.acterics.racesclient.di.TestComponentsManager
import com.acterics.racesclient.exception.FailedToAddBetException
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import javax.inject.Inject

/**
 * Created by oleg on 22.01.18.
 */
class BetRepositoryImplTest: ApplicationTestCase() {



    private val fakeParticipantId = 123L
    private val fakeBetValue = 55.5f
    private val fakeBetRate = 1.4f
    private val fakeBetRequest = BetRequest(fakeParticipantId, fakeBetValue, fakeBetRate)
    private val fakeBet = Bet(fakeBetValue, fakeBetRate)
    private val fakeParticipant = Participant(fakeParticipantId)

    private val successBooleanResponse = BooleanResponse(true)
    private val failBooleanResponse = BooleanResponse(false)

    @Inject
    lateinit var betMapper: BetMapper



    @Before
    fun setup() {
        TestComponentsManager.initAppComponent(ApplicationTestCase.application())
        TestComponentsManager.testAppComponent.inject(this)
    }

    @Test
    fun addBet_successResponse_successResult() {
        val mockApiService = mock<ApiService> {
            on { addBet(fakeBetRequest) } doReturn Single.just(BaseResponse(BaseResponse.STATUS_SUCCESS, successBooleanResponse))
        }

        val betRepository = BetRepositoryImpl(mockApiService, betMapper)


        val testObserver = betRepository
                .addBet(fakeBet, fakeParticipant)
                .test()

        testObserver.assertNoErrors()
        testObserver.assertComplete()
        testObserver.assertSubscribed()
    }

    @Test
    fun addBet_successResponse_failResult() {
        val mockApiService = mock<ApiService> {
            on { addBet(fakeBetRequest) } doReturn Single.just(BaseResponse(BaseResponse.STATUS_SUCCESS, failBooleanResponse))
        }

        val betRepository = BetRepositoryImpl(mockApiService, betMapper)

        val testObserver = betRepository
                .addBet(fakeBet, fakeParticipant)
                .test()

        testObserver.assertError(FailedToAddBetException::class.java)
        testObserver.assertSubscribed()
    }





}