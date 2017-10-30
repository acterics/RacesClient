package com.acterics.racesclient.domain

/**
 * Created by root on 30.10.17.
 */
interface DomainModelMapper<out T> {
    fun map(): T
}