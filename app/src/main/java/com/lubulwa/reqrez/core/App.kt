package com.lubulwa.reqrez.core

import com.lubulwa.reqrez.di.component.DaggerAppComponent
import dagger.android.support.DaggerApplication

open class App: DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()

}