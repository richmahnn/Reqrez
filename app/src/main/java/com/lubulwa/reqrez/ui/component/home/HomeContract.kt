package com.lubulwa.reqrez.ui.component.home

import com.lubulwa.reqrez.data.model.UserModel
import com.lubulwa.reqrez.ui.base.BasePresenter
import com.lubulwa.reqrez.ui.base.BaseView

interface HomeContract {

    interface Presenter : BasePresenter<View> {
        fun fetchUsers(pageNumber: Int?)
    }

    interface View : BaseView {
        fun fetchUsersStarted()
        fun fetchUsersSuccess(userModel: UserModel)
        fun fetchUsersFailed(message: String)
    }

}