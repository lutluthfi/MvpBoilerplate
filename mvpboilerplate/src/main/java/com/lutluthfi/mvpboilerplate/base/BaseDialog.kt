package com.lutluthfi.mvpboilerplate.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lutluthfi.mvpboilerplate.NetworkUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseDialog : DialogFragment(), IBaseView {

    private var progressDialog: ProgressDialog? = null

    var fragmentContext: Context? = null
        private set

    override fun show(fm: FragmentManager, tag: String) {
        show(fm.beginTransaction()
                .apply {
                    fm.findFragmentByTag(tag)?.let { remove(it) }
                    addToBackStack(null)
                    commit()
                }, tag)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (fragmentContext == null) fragmentContext = if (activity != null) activity else context
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(initContentView(), container, false).apply {
            initComponentView()
            initDependency()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupView()
    }

    override fun isNetworkConnected(): Boolean {
        var connected = false
        NetworkUtils.isNetworkConnected(context = requireContext())
                .subscribe(object : Observer<Boolean> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Boolean) {
                        connected = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        return connected
    }

    override fun printLog(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun printLog(tag: String, resId: Int) {
        Log.d(tag, getString(resId))
    }

    override fun showLoading(show: Boolean) {
    }

    override fun toastMessage(resId: Int) {
        Toast.makeText(fragmentContext, getString(resId), Toast.LENGTH_SHORT).show()
    }

    override fun toastMessage(message: String) {
        Toast.makeText(fragmentContext, message, Toast.LENGTH_SHORT).show()
    }

    protected abstract fun initContentView(): Int

    protected abstract fun initComponentView()

    protected abstract fun initDependency()

    protected abstract fun setupView()

    protected abstract fun setupListener()
}
