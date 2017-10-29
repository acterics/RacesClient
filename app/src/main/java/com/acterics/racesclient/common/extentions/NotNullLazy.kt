package com.acterics.racesclient.common.extentions

import kotlin.reflect.KProperty

/**
 * Created by root on 29.10.17.
 */
class NotNullLazy<T>(private val initializer: () -> T) {
    var value: T? = null
        get() {
            if (field == null) {
                field = initializer()
            }
            return field
        }
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T? {
        return value
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: T?) {
        this.value = value
    }
}

public fun <T> notNullLazy(initializer: () -> T) = NotNullLazy(initializer)