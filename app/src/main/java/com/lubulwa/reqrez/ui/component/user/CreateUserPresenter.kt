package com.lubulwa.reqrez.ui.component.user

import android.content.Context
import com.lubulwa.reqrez.R
import com.lubulwa.reqrez.data.ApiService
import com.lubulwa.reqrez.data.model.CreateUserModel
import com.lubulwa.reqrez.data.model.CreateUserResponse
import com.lubulwa.reqrez.utils.Network
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreateUserPresenter @Inject constructor(
    private val apiService: ApiService,
    private val mContext: Context
) : CreateUserContract.Presenter {

    private val disposable = CompositeDisposable()
    private var view: CreateUserContract.View? = null

    override fun takeView(view: CreateUserContract.View) {
        this.view = view
    }

    override fun dropView() {
        disposable.clear()
        view = null
    }

    /**
     * Create user
     */
    override fun createUser(name: String, my_job: String) {

        if (name.isEmpty() || my_job.isEmpty()) {
            view?.createUserFailed(mContext.getString(R.string.empty_name_and_job))
            return
        }
        
        val createUserModel = CreateUserModel(
            name,
            my_job
        )
        createNewUser(apiService.createUser(createUserModel))
    }

    /**
     * @param observable Observable to added to the subscription.
     */
    private fun createNewUser(observable: Observable<CreateUserResponse>) {

        view?.createUserStarted()

        if (!Network.isConnected(mContext)) {
            view?.createUserFailed(mContext.getString(R.string.no_internet))
            return
        }

        disposable.add(observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ createUserResponse ->
                if (view?.isActive() == false) return@subscribe

                view?.createUserSuccess(createUserResponse)
            }, { throwable ->
                throwable.printStackTrace()
                view?.createUserFailed(throwable.localizedMessage)
            }))

    }

}