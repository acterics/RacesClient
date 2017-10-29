package com.acterics.racesclient.data.network.model

/**
 * Created by root on 26.10.17.
 */
interface EntityWrapper<out T> {
    fun map(): T
}