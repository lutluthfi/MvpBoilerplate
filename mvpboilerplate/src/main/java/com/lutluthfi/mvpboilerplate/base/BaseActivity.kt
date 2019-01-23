package com.lutluthfi.mvpboilerplate.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), IBaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initContentView())
        initComponentView()
        initDependency()
        setupListener()
        setupView()
    }

    protected abstract fun initContentView(): Int

    protected abstract fun initComponentView()

    protected abstract fun initDependency()

    protected abstract fun setupView()

    protected abstract fun setupListener()

}
