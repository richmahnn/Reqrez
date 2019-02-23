package com.lubulwa.reqrez.ui.component.home

import com.lubulwa.reqrez.data.ApiService
import com.lubulwa.reqrez.data.model.UserModel
import com.lubulwa.reqrez.schedulers.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val apiService: ApiService,
    private val schedulerProvider: SchedulerProvider
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

        disposable.add(observable
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ userModel ->
                if (view?.isActive() == false) return@subscribe

                view?.fetchUsersSuccess(userModel)
            }, { throwable ->
                throwable.printStackTrace()
                view?.fetchUsersFailed(throwable.localizedMessage)
            }))
    }

}