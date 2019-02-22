package com.lubulwa.reqrez.di.module

import dagger.Module

@Module(includes = [NetworkModule::class, ApiServiceModule::class])
abstract class RetrofitModule
