package com.lubulwa.reqrez.ui.component.user

import com.lubulwa.reqrez.data.model.CreateUserModel
import com.lubulwa.reqrez.data.model.CreateUserResponse
import com.lubulwa.reqrez.ui.base.BasePresenter
import com.lubulwa.reqrez.ui.base.BaseView

interface CreateUserContract {

    interface Presenter : BasePresenter<View> {
        fun createUser(name: String, my_job: String)
    }

    interface View : BaseView {
        fun createUserStarted()
        fun createUserSuccess(createUserResponse: CreateUserResponse)
        fun createUserFailed(message: String)
    }

}