package com.acterics.racesclient.data.network.model.response

/**
 * Created by root on 19.10.17.
 */
data class BaseResponse<out T>(val status: Int,
                               val data: T,
                               val message: String? = "" ) {
    companion object {
        const val STATUS_SUCCESS = 200
        const val STATUS_ERROR = 500

        fun isSuccessResponse(status: Int) = status / 200 == 1
    }
}