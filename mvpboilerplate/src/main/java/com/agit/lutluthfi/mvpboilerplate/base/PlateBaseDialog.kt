package com.agit.lutluthfi.mvpboilerplate.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View
import android.widget.Toast

import com.agit.lutluthfi.mvpboilerplate.R
import com.agit.lutluthfi.mvpboilerplate.CommonUtils
import com.agit.lutluthfi.mvpboilerplate.NetworkUtils

abstract class PlateBaseDialog : DialogFragment(), IPlateBaseView {

    private var progressDialog: ProgressDialog? = null

    protected var fragmentContext: Context? = null
        private set

    protected abstract fun setupView(view: View)

    protected abstract fun setupListener()

    override fun show(fm: FragmentManager, tag: String) {
        val ft = fm.beginTransaction()
        val prevFragment = fm.findFragmentByTag(tag)
        prevFragment?.let { ft.remove(it) }
        ft.addToBackStack(null)
        show(ft, tag)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (fragmentContext == null) fragmentContext = if (activity != null) activity else context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupListener()
    }

    override fun showLoading() {
        activity?.run {
            runOnUiThread {
                hideLoading()
                fragmentContext?.run {
                    progressDialog = CommonUtils.showLoadingDialog(context = this)
                }
            }
        }
    }

    override fun hideLoading() {
        activity?.run {
            runOnUiThread {
                progressDialog?.run { if (isShowing) cancel() }
            }
        }
    }

    override fun isLoading(): Boolean {
        var isShowing = false
        activity?.run {
            runOnUiThread {
                progressDialog?.run { isShowing = this.isShowing }
            }
        }
        return isShowing
    }

    override fun isNetworkConnected(): Boolean {
        var isConnected = false
        fragmentContext?.run { isConnected = NetworkUtils.isNetworkConnected(this) }
        return isConnected
    }

    override fun showMessage(message: String?) {
        val viewError = getString(R.string.utils_error_view)
        activity?.run {
            runOnUiThread {
                fragmentContext?.run {
                    message?.let {
                        if (it.isNotEmpty()) {
                            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, viewError, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun showMessage(resId: Int) {
        showMessage(getString(resId))
    }

    override fun printLog(tag: String, message: String?) {
        message?.run {
            return@run if (isNotEmpty()) Log.d(tag, this)
            else Log.d(tag, getString(R.string.utils_error_view))
        }
    }

    override fun printLog(tag: String, resId: Int) {
        printLog(tag, getString(resId))
    }

    override fun printLog(tag: String, tr: Throwable?) {
        tr?.run { Log.d(tag, message, this) }
    }

    @Deprecated(message = "Unchecked")
    override fun showSnackbar(message: String?, duration: Int, textColor: Int) {
    }

    @Deprecated(message = "Unchecked")
    override fun showSnackbar(resId: Int, duration: Int, textColor: Int) {
    }

    @Deprecated(message = "Unchecked")
    override fun showSnackbarWithAction(message: String?, duration: Int, textColor: Int, action: String, listener: View.OnClickListener) {
    }

    @Deprecated(message = "Unchecked")
    override fun showSnackbarWithAction(resId: Int, duration: Int, textColor: Int, action: String, listener: View.OnClickListener) {
    }

    @Deprecated(message = "Unchecked")
    override fun hideSnackbar() {
    }

    @Deprecated(message = "Unchecked", replaceWith = ReplaceWith("false"))
    override fun isSnackbarShowing(): Boolean {
        return false
    }
}
