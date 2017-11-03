package com.acterics.racesclient.data.network

import com.acterics.racesclient.data.network.model.BetModel
import com.acterics.racesclient.data.network.model.HistoryBetModel
import com.acterics.racesclient.data.network.model.RaceModel
import com.acterics.racesclient.data.network.model.UserModel
import com.acterics.racesclient.data.network.model.request.BetRequest
import com.acterics.racesclient.data.network.model.request.SignInRequest
import com.acterics.racesclient.data.network.model.request.SignUpRequest
import com.acterics.racesclient.data.network.model.response.BaseResponse
import com.acterics.racesclient.data.network.model.response.ScheduleResponse
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by root on 28.09.17.
 */
interface ApiService {
    @POST("/signin")
    fun signIn(@Body signInRequest: SignInRequest): Single<BaseResponse<UserModel>>

    @POST("/signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Single<BaseResponse<UserModel>>

    @GET("/schedule")
    fun getSchedule(@Query("skip") skip: Int, @Query("count") count: Int): Single<BaseResponse<ScheduleResponse>>

    @GET("/race/{id}")
    fun getRace(@Path("id") raceId: Long): Single<BaseResponse<RaceModel>>

    @POST("user/{userId}/bet")
    fun addBet(@Path("userId") userId: Long, @Body betRequest: BetRequest):
            Single<BaseResponse<BetModel>>


    @GET("/user/{userId}/history")
    fun getHistory(@Path("userId") userId: Long,
                   @Query("skip") skip: Int,
                   @Query("count") count: Int): Single<BaseResponse<List<HistoryBetModel>>>


}