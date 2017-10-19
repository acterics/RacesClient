package com.acterics.racesclient.utils

import com.acterics.racesclient.data.error.NetworkStatusException
import com.acterics.racesclient.data.model.response.BaseResponse
import io.reactivex.Observable

/**
 * Created by root on 19.10.17.
 */



fun <D, T: BaseResponse<D>> Observable<T>.checkStatus(): Observable<D> {
    return this.flatMap {
        val observable = if (it.status == BaseResponse.STATUS_SUCCESS) {
            Observable.just(it.data)
        } else {
            io.reactivex.Observable.error(NetworkStatusException(it.message))
        }
        observable
    }
}