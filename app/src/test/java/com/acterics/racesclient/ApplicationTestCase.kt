package com.acterics.racesclient

import android.app.Application
import android.content.Context
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.io.File

/**
 * Created by oleg on 18.01.18.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = ApplicationStub::class, sdk = [21])
abstract class ApplicationTestCase {

    companion object {
        fun application(): Application = RuntimeEnvironment.application
        fun context(): Context = RuntimeEnvironment.application
        fun cacheDir(): File = RuntimeEnvironment.application.cacheDir
    }

    @get:Rule
    val injectMockRule: TestRule = TestRule { base, _ ->
        MockitoAnnotations.initMocks(ApplicationTestCase::class)
        base
    }







}