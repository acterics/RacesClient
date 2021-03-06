package com.acterics.racesclient.di.app

import android.content.Context
import com.acterics.racesclient.BuildConfig
import com.acterics.racesclient.common.extentions.getToken
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by root on 10.10.17.
 */
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(context: Context): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
        }
        clientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    .header("Authorization", "Bearer ${context.getToken()}")
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build()
            chain.proceed(request)
        }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
                .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.ROOT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build()
    }

}