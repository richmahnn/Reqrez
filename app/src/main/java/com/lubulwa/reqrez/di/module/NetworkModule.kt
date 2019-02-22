package com.lubulwa.reqrez.di.module

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
import java.util.concurrent.TimeUnit


@Module
class NetworkModule {

  companion object {
    private const val CLIENT_TIME_OUT = 10L
  }

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
