package com.lubulwa.reqrez.ui.component.splash

import android.os.Bundle
import android.os.Handler
import com.lubulwa.reqrez.R
import com.lubulwa.reqrez.ui.base.BaseActivity
import com.lubulwa.reqrez.ui.component.home.HomeActivity
import com.lubulwa.reqrez.utils.Constants
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View  {

    @Inject
    lateinit var splashPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Injection dependencies
        AndroidInjection.inject(this)

        splashPresenter.initialize()
    }

    override fun goToHomeScreen() {
        navigateToActivity(HomeActivity::class.java)
        finish()
    }

    override fun onResume() {
        super.onResume()

        splashPresenter.takeView(this)
    }

    override fun onPause() {
        splashPresenter.dropView()

        super.onPause()
    }

}
