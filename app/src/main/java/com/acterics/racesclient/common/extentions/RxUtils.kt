package com.acterics.racesclient.common.extentions

import com.acterics.racesclient.data.error.NetworkStatusException
import com.acterics.racesclient.data.network.model.response.BaseResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by root on 19.10.17.
 */



fun <D, T: BaseResponse<D>> Observable<T>.checkNetworkObservable(): Observable<D> {
    return this.flatMap {
        val observable = if (it.status == BaseResponse.STATUS_SUCCESS) {
            Observable.just(it.data)
        } else {
            Observable.error(NetworkStatusException(it.message))
        }
        observable
    }
}

fun <D, T: BaseResponse<D>> Single<T>.checkNetworkSingle(): Single<D> {
    return this.flatMap {
        val observable = if (it.status == BaseResponse.STATUS_SUCCESS) {
            Single.just(it.data)
        } else {
            Single.error(NetworkStatusException(it.message))
        }
        observable
    }
}

