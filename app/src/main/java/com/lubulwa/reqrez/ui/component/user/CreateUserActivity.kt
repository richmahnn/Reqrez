package com.lubulwa.reqrez.ui.component.user

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.lubulwa.reqrez.R
import com.lubulwa.reqrez.data.model.CreateUserResponse
import com.lubulwa.reqrez.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_create_user.*
import javax.inject.Inject

class CreateUserActivity : BaseActivity(), CreateUserContract.View {

    @Inject lateinit var createUserPresenter: CreateUserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        setupToolbar()
        initSetup()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun initSetup() {
        btn_create_user.setOnClickListener {
            val name = et_username_create.text.toString()
            val my_job = et_job_create.text.toString()

            createUserPresenter.createUser(name, my_job)
        }
    }

    override fun createUserStarted() {
        showProgressDialog()
    }

    override fun createUserSuccess(createUserResponse: CreateUserResponse) {
        dismissProgressDilog()

        et_username_create.text = null
        et_job_create.text = null

        Snackbar.make(mainLayout, getString(R.string.user_created_message, createUserResponse.name), Snackbar.LENGTH_LONG).show()
    }

    override fun createUserFailed(message: String) {
        dismissProgressDilog()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun isActive(): Boolean = !isFinishing

    override fun onResume() {
        super.onResume()

        createUserPresenter.takeView(this)
    }

    override fun onPause() {
        createUserPresenter.dropView()

        super.onPause()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()

            else -> return super.onOptionsItemSelected(item)
        }
        return false
    }

}
