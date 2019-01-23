package com.lutluthfi.mvpboilerplate.base

interface IPlateBasePresenter<V : IPlateBaseView> {
    fun onAttach()

    fun onDetach()

    fun isAttached(): Boolean
}
