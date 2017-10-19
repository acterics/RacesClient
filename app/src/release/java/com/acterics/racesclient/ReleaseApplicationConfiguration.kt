package com.acterics.racesclient

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by root on 19.10.17.
 */
class ReleaseApplicationConfiguration: ApplicationConfiguration {
    override fun initialize(app: Application) {
        JodaTimeAndroid.init(app)
    }

}