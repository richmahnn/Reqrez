package com.lubulwa.reqrez.ui.component.user

import com.lubulwa.reqrez.data.ApiService
import com.lubulwa.reqrez.data.model.CreateUserModel
import com.lubulwa.reqrez.data.model.CreateUserResponse
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import test.TestImmediateSchedulerProvider

class CreateUserPresenterTest {

    private val provider = TestImmediateSchedulerProvider()
    private var view: CreateUserContract.View = mock()
    private var apiService: ApiService = mock()
    private lateinit var presenter: CreateUserPresenter

    @Before
    fun setUp() {
        whenever(view.isActive()).thenReturn(true)
    }

    @Test
    fun testCreateSuccess() {
        val response: CreateUserResponse = mock()

        presenter = CreateUserPresenter(apiService, provider)
        presenter.takeView(view)

        val userModel = CreateUserModel(
            "John Doe",
            "Leader"
        )
        whenever(apiService.createUser(eq(userModel))).thenReturn(Observable.just(response))

        presenter.createUser(userModel.name, userModel.job)

        verify(view).isActive()
        verify(view).createUserSuccess(eq(response))
    }


}