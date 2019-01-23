package com.lutluthfi.mvpboilerplate.base

import android.support.annotation.StringRes
import android.view.View

interface IBaseView {

    fun isNetworkConnected(): Boolean

    fun printLog(tag: String = "IBaseView", message: String = "Hello")

    fun printLog(tag: String = "IBaseView", @StringRes resId: Int = 0)

    fun showLoading(show: Boolean)

    fun toastMessage(@StringRes resId: Int = 0)

    fun toastMessage(message: String = "Hello")
}
