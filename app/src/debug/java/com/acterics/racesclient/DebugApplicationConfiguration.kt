package com.acterics.racesclient

import android.app.Application
import timber.log.Timber

/**
 * Created by root on 28.09.17.
 */
class DebugApplicationConfiguration: ApplicationConfiguration {


    override fun initialize(app: Application) {
        //Timber
        Timber.plant(Timber.DebugTree())

    }


}