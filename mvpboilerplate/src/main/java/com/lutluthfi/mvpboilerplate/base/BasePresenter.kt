package com.lutluthfi.mvpboilerplate.base

open class BasePresenter<V : IBaseView>(view: V) : IBasePresenter<V> {

    private var isAttached: Boolean = false
    protected var view: V? = null
        private set

    init {
        this.view = view
    }

    override fun onAttach() {
        this.isAttached = true
    }

    override fun onDetach() {
        this.view = null
        this.isAttached = false
    }

    override fun isAttached(): Boolean {
        return isAttached
    }
}
