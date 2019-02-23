package com.lubulwa.reqrez.ui.component.home

import com.lubulwa.reqrez.data.ApiService
import com.lubulwa.reqrez.data.model.UserModel
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import test.TestImmediateSchedulerProvider

class HomePresenterTest {

    private val provider = TestImmediateSchedulerProvider()
    private var view: HomeContract.View = mock()
    private var apiService: ApiService = mock()
    private lateinit var presenter: HomePresenter

    @Before
    fun setUp() {
        whenever(view.isActive()).thenReturn(true)
    }

    @Test
    fun testFetchUsersSuccess() {
        val pageNumber = 1
        val response = UserModel()
        presenter = HomePresenter(apiService, provider)
        presenter.takeView(view)

        whenever(apiService.fetchUsers(eq(pageNumber))).thenReturn(Observable.just(response))

        presenter.fetchUsers(pageNumber)

        verify(view).isActive()
        verify(view).fetchUsersSuccess(eq(response))
    }

}