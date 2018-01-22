package com.acterics.racesclient.data.network

import com.acterics.domain.model.dto.SignInCredentials
import com.acterics.domain.model.dto.SignUpCredentials
import com.acterics.racesclient.data.network.model.RaceModel
import com.acterics.racesclient.data.network.model.UserModel
import com.acterics.racesclient.data.network.model.request.BetRequest
import com.acterics.racesclient.data.network.model.response.*
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by root on 28.09.17.
 */
interface ApiService {
    @POST("auth/sign_in/")
    fun signIn(@Body signInCredentials: SignInCredentials): Single<BaseResponse<TokenResponse>>

    @POST("auth/sign_up/")
    fun signUp(@Body signUpCredentials: SignUpCredentials): Single<BaseResponse<TokenResponse>>

    @GET("user/")
    fun getUser(): Single<BaseResponse<UserModel>>

    @GET("races/")
    fun getRaces(@Query("skip") skip: Int,
                 @Query("count") count: Int): Single<BaseResponse<ScheduleResponse>>

    @GET("races/{id}")
    fun getRace(@Path("id") raceId: Long): Single<BaseResponse<RaceModel>>

    @POST("bet/")
    fun addBet(@Body betRequest: BetRequest): Single<BaseResponse<BooleanResponse>>

    @GET("bet/")
    fun getBets(@Query("skip") skip: Int,
                @Query("count") count: Int): Single<BaseResponse<HistoryResponse>>


}