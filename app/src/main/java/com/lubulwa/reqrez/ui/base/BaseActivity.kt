package com.lubulwa.reqrez.ui.base

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.lubulwa.reqrez.R
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(){

    private var loadingDialog: Dialog? = null

    fun navigateToActivity(activityName: Class<*>) {
        startActivity(Intent(this, activityName))
    }

    fun showProgressDialog() {
        loadingDialog = Dialog(this)
        loadingDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingDialog?.setCancelable(false)
        loadingDialog?.setContentView(R.layout.loading_view)
        loadingDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        loadingDialog?.show()
    }

    protected fun dismissProgressDilog() {
        if (loadingDialog != null) {
            loadingDialog?.dismiss()
        }
    }

}