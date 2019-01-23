package com.lutluthfi.mvpboilerplate.base

interface IBasePresenter<V : IBaseView> {
    fun onAttached()

    fun onDetached()
}
