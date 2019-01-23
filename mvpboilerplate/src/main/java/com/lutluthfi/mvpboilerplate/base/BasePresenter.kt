package com.lutluthfi.mvpboilerplate.base

open class BasePresenter<V : IBaseView>(view: V) : IBasePresenter<V> {

    protected var mIsAttached: Boolean = false
        private set

    protected var mView: V? = null
        private set

    init {
        this.mView = view
    }

    override fun onAttached() {
        mIsAttached = true
    }

    override fun onDetached() {
        mView = null
        mIsAttached = false
    }
}
