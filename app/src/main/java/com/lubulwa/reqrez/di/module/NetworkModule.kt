package com.lubulwa.reqrez.di.module

import android.app.Application
import com.lubulwa.reqrez.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


@Module
class NetworkModule {

  companion object {
    private const val CLIENT_TIME_OUT = 10L
    private const val CLIENT_CACHE_SIZE = 10 * 1024 * 1024L // 10 MiB
    private const val CLIENT_CACHE_DIRECTORY = "Reqrez"
  }

  @Provides fun providesCache(
    application: Application
  ): Cache = Cache(File(application.cacheDir, CLIENT_CACHE_DIRECTORY), CLIENT_CACHE_SIZE)

  @Provides fun providesOkHttpClient(cache: Cache?): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(
      HttpLoggingInterceptor()
      .setLevel(if (BuildConfig.DEBUG) Level.BODY else Level.NONE))
    .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
    .writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
    .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
    .cache(cache)
    .build()

  @Provides fun providesRetrofit(
    okHttpClient: OkHttpClient
  ): Retrofit.Builder = Retrofit.Builder()
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)

}
