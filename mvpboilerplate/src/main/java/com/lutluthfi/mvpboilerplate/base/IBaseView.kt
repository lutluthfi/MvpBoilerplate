package com.lutluthfi.mvpboilerplate.base

import android.support.annotation.StringRes
import android.view.View

interface IBaseView {

    fun isNetworkConnected(): Boolean

    fun printLog(tag: String, message: String?)

    fun printLog(tag: String, @StringRes resId: Int)

    fun showLoading(show: Boolean)

    fun toastMessage(@StringRes resId: Int)

    fun toastMessage(message: String?)
}
