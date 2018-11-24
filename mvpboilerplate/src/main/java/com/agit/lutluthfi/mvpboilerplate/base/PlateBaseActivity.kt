package com.agit.lutluthfi.mvpboilerplate.base

import android.app.ProgressDialog
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.agit.lutluthfi.mvpboilerplate.R
import com.agit.lutluthfi.mvpboilerplate.util.CommonUtils
import com.agit.lutluthfi.mvpboilerplate.util.NetworkUtils

abstract class PlateBaseActivity : AppCompatActivity(), IPlateBaseView {

    private var snackbar: Snackbar? = null
    private var progressDialog: ProgressDialog? = null

    protected abstract fun setupView()

    protected abstract fun setupListener()

    override fun showLoading() {
        runOnUiThread {
            if (isLoading()) hideLoading()
            progressDialog = CommonUtils.showLoadingDialog(context = this)
        }
    }

    override fun hideLoading() {
        runOnUiThread { progressDialog?.run { if (isShowing) cancel() } }
    }

    override fun isLoading(): Boolean {
        var isShowing = false
        runOnUiThread { progressDialog?.run { isShowing = this.isShowing } }
        return isShowing
    }

    override fun isNetworkConnected(): Boolean = NetworkUtils.isNetworkConnected(context = this)

    override fun showMessage(message: String?) {
        val viewError = getString(R.string.utils_error_view)
        message?.let {
            runOnUiThread {
                if (it.isNotEmpty()) {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, viewError, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun showMessage(resId: Int) = showMessage(getString(resId))

    override fun printLog(tag: String, message: String?) {
        message?.let {
            return@let if (it.isNotEmpty()) Log.d(tag, it)
            else Log.d(tag, getString(R.string.utils_error_view))
        }
    }

    override fun printLog(tag: String, resId: Int) = printLog(tag, getString(resId))

    override fun printLog(tag: String, tr: Throwable?) {
        tr?.let { Log.d(tag, it.message, it) }
    }

    override fun showSnackbar(message: String?, duration: Int, textColor: Int) {
        message?.let {
            runOnUiThread {
                hideSnackbar()
                createSnackbar(it, duration, textColor)?.run { show() }
            }
        }
    }

    override fun showSnackbar(resId: Int, duration: Int, textColor: Int) {
        runOnUiThread {
            if (isSnackbarShowing()) hideSnackbar()
            createSnackbar(getString(resId), duration, textColor)?.run { show() }
        }
    }

    override fun showSnackbarWithAction(message: String?,
                                        duration: Int,
                                        textColor: Int,
                                        action: String,
                                        listener: View.OnClickListener) {
        message?.let {
            runOnUiThread {
                if (isSnackbarShowing()) hideSnackbar()
                createSnackbar(it, duration, textColor)?.run {
                    setAction(action, listener)
                    show()
                }
            }
        }
    }

    override fun showSnackbarWithAction(resId: Int,
                                        duration: Int,
                                        textColor: Int,
                                        action: String,
                                        listener: View.OnClickListener) {
        runOnUiThread {
            if (isSnackbarShowing()) hideSnackbar()
            createSnackbar(getString(resId), duration, textColor)?.run {
                setAction(action, listener)
                show()
            }
        }
    }

    override fun hideSnackbar() {
        runOnUiThread { snackbar?.run { if (isShown) dismiss() } }
    }

    override fun isSnackbarShowing(): Boolean {
        var isShowing = false
        snackbar?.run { isShowing = this.isShown }
        return isShowing
    }

    private fun createSnackbar(message: String?, duration: Int, textColor: Int): Snackbar? {
        val color = ContextCompat.getColor(this@PlateBaseActivity, textColor)
        val commonError = getString(R.string.utils_error_common)
        message?.let {
            if (it.isNotEmpty()) {
                snackbar = Snackbar.make(findViewById(android.R.id.content), it, duration)
                val snackbarView = snackbar?.run { view }
                return@let snackbarView?.run {
                    val textView = findViewById<TextView>(android.support.design.R.id.snackbar_text)
                    textView.setTextColor(color)
                }
            } else {
                snackbar = Snackbar.make(findViewById(android.R.id.content), commonError, duration)
                val snackbarView = snackbar?.run { view }
                snackbarView?.run {
                    val textView = findViewById<TextView>(android.support.design.R.id.snackbar_text)
                    return@let textView.setTextColor(color)
                }
            }
        }
        return snackbar as Snackbar
    }
}
