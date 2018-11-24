package com.agit.lutluthfi.mvpboilerplate.base

import android.support.annotation.StringRes
import android.view.View

interface IPlateBaseView {

    fun isNetworkConnected(): Boolean

    fun isSnackbarShowing(): Boolean

    fun isLoading(): Boolean

    fun showLoading()

    fun hideLoading()

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)

    fun printLog(tag: String, message: String?)

    fun printLog(tag: String, @StringRes resId: Int)

    fun printLog(tag: String, tr: Throwable?)

    fun showSnackbar(message: String?, duration: Int, textColor: Int)

    fun showSnackbar(@StringRes resId: Int, duration: Int, textColor: Int)

    fun showSnackbarWithAction(message: String?,
                               duration: Int,
                               textColor: Int,
                               action: String,
                               listener: View.OnClickListener)

    fun showSnackbarWithAction(@StringRes resId: Int,
                               duration: Int,
                               textColor: Int,
                               action: String,
                               listener: View.OnClickListener)

    fun hideSnackbar()
}
