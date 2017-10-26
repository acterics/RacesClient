package com.acterics.racesclient.data.rest

import com.acterics.racesclient.data.model.RaceModel
import com.acterics.racesclient.data.model.UserModel
import com.acterics.racesclient.data.model.request.SignInRequest
import com.acterics.racesclient.data.model.request.SignUpRequest
import com.acterics.racesclient.data.model.response.BaseResponse
import com.acterics.racesclient.data.model.response.ScheduleResponse
import io.reactivex.Observable
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


}