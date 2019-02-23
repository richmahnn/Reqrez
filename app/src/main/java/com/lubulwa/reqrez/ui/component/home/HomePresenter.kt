package com.lubulwa.reqrez.ui.component.home

import android.content.Context
import com.lubulwa.reqrez.R
import com.lubulwa.reqrez.data.ApiService
import com.lubulwa.reqrez.data.model.UserModel
import com.lubulwa.reqrez.utils.Constants
import com.lubulwa.reqrez.utils.Network
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val apiService: ApiService,
    private val mContext: Context
) : HomeContract.Presenter {

    private val disposable = CompositeDisposable()
    private var view: HomeContract.View? = null

    override fun takeView(view: HomeContract.View) {
        this.view = view
    }

    override fun dropView() {
        disposable.clear()
        view = null
    }

    /**
     * Get users from api.
     */
    override fun fetchUsers(pageNumber: Int?) {
        loadUsers(apiService.fetchUsers(pageNumber!!))
    }

    /**
     * Common code for subscription.
     *
     * @param observable Observable to added to the subscription.
     */
    private fun loadUsers(observable: Observable<UserModel>) {
        view?.fetchUsersStarted()

        if (!Network.isConnected(mContext)) {
            view?.fetchUsersFailed(mContext.getString(R.string.no_internet))
            return
        }

        disposable.add(observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userModel ->
                if (view?.isActive() == false) return@subscribe

                view?.fetchUsersSuccess(userModel)
            }, { throwable ->
                throwable.printStackTrace()
                view?.fetchUsersFailed(throwable.localizedMessage)
            }))
    }

}