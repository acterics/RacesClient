package com.acterics.racesclient

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import io.fabric.sdk.android.Fabric
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

/**
 * Created by root on 28.09.17.
 */
class DebugApplicationConfiguration: ApplicationConfiguration {


    override fun initialize(app: Application) {

        if (LeakCanary.isInAnalyzerProcess(app)) {
            return
        }
        LeakCanary.install(app)

        //Timber
        Timber.plant(Timber.DebugTree())
        //JodaTime
        JodaTimeAndroid.init(app)

        //Crashlytics
        Fabric.with(app, Crashlytics())

        //Stetho
        Stetho.initializeWithDefaults(app)
    }


}