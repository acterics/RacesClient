package com.acterics.racesclient.data.rest

import com.acterics.racesclient.data.entity.User
import com.acterics.racesclient.data.model.request.SignInRequest
import com.acterics.racesclient.data.model.request.SignUpRequest
import com.acterics.racesclient.data.model.response.BaseResponse
import com.acterics.racesclient.data.model.response.RaceDetailResponse
import com.acterics.racesclient.data.model.response.ScheduleResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by root on 28.09.17.
 */
interface ApiService {
    @POST("/signin")
    fun signIn(@Body signInRequest: SignInRequest): Observable<BaseResponse<User>>

    @POST("/signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Observable<BaseResponse<User>>

    @GET("/schedule")
    fun getSchedule(@Query("page") page: Int): Observable<BaseResponse<ScheduleResponse>>

    @GET("/race/{id}")
    fun getRace(@Path("id") raceId: Long): Observable<BaseResponse<RaceDetailResponse>>



}