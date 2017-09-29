package com.acterics.racesclient

import android.app.Application

/**
 * Created by root on 28.09.17.
 */
interface ApplicationConfiguration {
    fun initialize(app: Application)
}