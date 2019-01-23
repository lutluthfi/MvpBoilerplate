package com.lutluthfi.mvpboilerplate.base

interface IBasePresenter<V : IBaseView> {
    fun onAttach()

    fun onDetach()

    fun isAttached(): Boolean
}
