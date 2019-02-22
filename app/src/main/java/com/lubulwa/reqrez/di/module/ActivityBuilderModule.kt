package com.lubulwa.reqrez.di.module

import com.lubulwa.reqrez.ui.component.home.MainActivity
import com.lubulwa.reqrez.ui.component.splash.SplashActivity
import com.lubulwa.reqrez.ui.component.user.CreateUserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
  @ContributesAndroidInjector(modules = [RetrofitModule::class])
  abstract fun providesMainActivity(): MainActivity
  abstract fun providesSplashActivity(): SplashActivity
  abstract fun providesCreateUserActivity(): CreateUserActivity
}
