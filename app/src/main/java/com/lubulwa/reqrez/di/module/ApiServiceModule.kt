package com.lubulwa.reqrez.di.module

import com.lubulwa.reqrez.data.ApiService
import com.lubulwa.reqrez.core.Config
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiServiceModule(private var baseUrl: String = Config.BASE_URL) {

  @Provides fun providesServiceApi(retrofit: Retrofit.Builder): ApiService = retrofit
    .baseUrl(baseUrl)
    .build()
    .create(ApiService::class.java)

}
