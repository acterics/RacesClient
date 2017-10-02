package com.acterics.racesclient.di.modules

import com.acterics.racesclient.utils.validators.EmailValidator
import com.acterics.racesclient.utils.validators.PasswordValidator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by root on 02.10.17.
 */
@Module
class ValidationModule {


    @Provides
    @Singleton
    fun provideEmailValidator(): EmailValidator {
        return EmailValidator()
    }

    @Provides
    @Singleton
    fun providePasswordValidator(): PasswordValidator {
        return PasswordValidator()
    }
}