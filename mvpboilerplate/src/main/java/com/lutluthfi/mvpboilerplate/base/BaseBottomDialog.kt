package com.lutluthfi.mvpboilerplate.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View
import android.widget.Toast

import com.lutluthfi.mvpboilerplate.R
import com.lutluthfi.mvpboilerplate.CommonUtils
import com.lutluthfi.mvpboilerplate.NetworkUtils

abstract class BaseBottomDialog : BottomSheetDialogFragment(), IBaseView {

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
}
