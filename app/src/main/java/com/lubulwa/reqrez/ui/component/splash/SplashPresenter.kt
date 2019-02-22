package com.lubulwa.reqrez.ui.component.splash

import android.os.Handler
import com.lubulwa.reqrez.utils.Constants
import javax.inject.Inject

class SplashPresenter @Inject constructor(): SplashContract.Presenter{

    private var view: SplashContract.View? = null

    override fun takeView(view: SplashContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

    override fun initialize() {
        Handler().postDelayed({
            view?.goToHomeScreen()
        }, Constants.SPLASH_DELAY.toLong())
    }

}