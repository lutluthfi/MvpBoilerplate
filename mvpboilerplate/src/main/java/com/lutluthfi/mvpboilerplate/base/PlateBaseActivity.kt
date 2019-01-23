package com.lutluthfi.mvpboilerplate.base

import android.support.v7.app.AppCompatActivity

abstract class PlateBaseActivity : AppCompatActivity(), IPlateBaseView {

    protected abstract fun setupView()

    protected abstract fun setupListener()
}
