package com.lubulwa.reqrez.ui.component.splash

import com.lubulwa.reqrez.ui.base.BasePresenter

interface SplashContract {

    interface View {
        fun goToHomeScreen()
    }

    interface Presenter : BasePresenter<View> {
        fun initialize()
    }

}