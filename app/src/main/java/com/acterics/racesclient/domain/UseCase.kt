package com.acterics.racesclient.domain

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by root on 24.10.17.
 */
abstract class UseCase<out Type, in Params> where Type: Any {
    internal val disposables = CompositeDisposable()

    fun dispose() = disposables.dispose()
    fun isDispose() = disposables.isDisposed

    abstract fun build(params: Params?): Type


    abstract class AsSingle<T, in P>: UseCase<Single<T>, P>() {
        fun execute(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit, params: P? = null) {
            disposables.add(build(params).subscribe(onSuccess, onError))

        }
    }

    abstract class AsObservable<T, in P>: UseCase<Observable<T>, P>() {
        fun execute(onNext: (T) -> Unit, onError: (Throwable) -> Unit, params: P? = null) {
            disposables.add(build(params).subscribe(onNext, onError))
        }
    }

    abstract class RxCompletable<in P> : UseCase<Completable, P>() {
        fun execute(params: P? = null) = execute({}, params)

        fun execute(onComplete: () -> Unit = {}, params: P? = null) =
                disposables.add(build(params).subscribe(onComplete))
    }

    class None
}