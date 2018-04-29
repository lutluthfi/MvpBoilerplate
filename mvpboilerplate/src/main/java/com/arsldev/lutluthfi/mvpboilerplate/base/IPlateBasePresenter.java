package com.arsldev.lutluthfi.mvpboilerplate.base;

public interface IPlateBasePresenter<V extends IPlateBaseView> {
    void onAttach(V view);

    void onDetach();
}
