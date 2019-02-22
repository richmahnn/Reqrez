package com.lubulwa.reqrez.ui.base

interface BasePresenter<in T> {
    fun takeView(view: T)
    fun dropView()
}