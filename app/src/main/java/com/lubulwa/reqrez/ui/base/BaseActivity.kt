package com.lubulwa.reqrez.ui.base

import android.content.Intent
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(){

    fun navigateToActivity(activityName: Class<*>) {
        startActivity(Intent(this, activityName))
    }

}