package com.arsldev.lutluthfi.mvpboilerplate.base;

public class PlateBasePresenter<V extends IPlateBaseView> implements IPlateBasePresenter<V> {

    private V mView;

    @Override
    public void onAttach(V view) {
        this.mView = view;
    }

    @Override
    public void onDetach() {
        this.mView = null;
    }

    protected V getView() {
        return mView;
    }
}
