package com.acterics.racesclient.data.rest

import com.acterics.racesclient.data.model.RaceModel
import com.acterics.racesclient.data.model.UserModel
import com.acterics.racesclient.data.model.request.SignInRequest
import com.acterics.racesclient.data.model.request.SignUpRequest
import com.acterics.racesclient.data.model.response.BaseResponse
import com.acterics.racesclient.data.model.response.ScheduleResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by root on 28.09.17.
 */
interface ApiService {
    @POST("/signin")
    fun signIn(@Body signInRequest: SignInRequest): Observable<BaseResponse<UserModel>>

    @POST("/signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Observable<BaseResponse<UserModel>>

    @GET("/schedule")
    fun getSchedule(@Query("skip") skip: Int, @Query("count") count: Int): Observable<BaseResponse<ScheduleResponse>>

    @GET("/race/{id}")
    fun getRace(@Path("id") raceId: Long): Observable<BaseResponse<RaceModel>>


}