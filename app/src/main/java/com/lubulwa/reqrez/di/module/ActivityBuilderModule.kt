package com.lubulwa.reqrez.di.module

import com.lubulwa.reqrez.ui.component.home.HomeActivity
import com.lubulwa.reqrez.ui.component.splash.SplashActivity
import com.lubulwa.reqrez.ui.component.user.CreateUserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
  @ContributesAndroidInjector(modules = [RetrofitModule::class])
  abstract fun providesHomeActivity(): HomeActivity

  @ContributesAndroidInjector
  abstract fun providesSplashActivity(): SplashActivity

  @ContributesAndroidInjector(modules = [RetrofitModule::class])
  abstract fun providesCreateUserActivity(): CreateUserActivity
}
