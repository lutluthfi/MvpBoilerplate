package com.lutluthfi.mvpboilerplate.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.lutluthfi.mvpboilerplate.NetworkUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity(), IBaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initContentView())
        initComponentView()
        initDependency()
        setupListener()
        setupView()
    }

    override fun isNetworkConnected(): Boolean {
        var connected = false
        NetworkUtils.isNetworkConnected(context = this)
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
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
    }

    override fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected abstract fun initContentView(): Int

    protected abstract fun initComponentView()

    protected abstract fun initDependency()

    protected abstract fun setupView()

    protected abstract fun setupListener()

}
