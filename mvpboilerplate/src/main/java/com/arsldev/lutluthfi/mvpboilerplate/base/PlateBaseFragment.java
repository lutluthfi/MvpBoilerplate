package com.arsldev.lutluthfi.mvpboilerplate.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.arsldev.lutluthfi.mvpboilerplate.utils.CommonUtils;

import butterknife.Unbinder;

public abstract class PlateBaseFragment extends Fragment implements IPlateBaseView {

    private PlateBaseActivity mActivity;
    private ProgressDialog mProgressDialog;
    private Unbinder mUnbinder;

    public abstract void setupView(View view);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PlateBaseActivity) this.mActivity = (PlateBaseActivity) context;
    }

    @Override
    public void onDestroy() {
        if (mUnbinder != null) mUnbinder.unbind();
        super.onDestroy();
    }

    public PlateBaseActivity getBaseActivity() { return mActivity; }

    public void setUnBinder(Unbinder unBinder) {
        mUnbinder = unBinder;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }

    @Override
    public boolean isLoading() {
        return mProgressDialog.isShowing();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.cancel();
    }

    @Override
    public void onError(String message) {
        if (mActivity != null) mActivity.onError(message);
    }

    @Override
    public void onError(@StringRes int resId) {
        if (mActivity != null) mActivity.onError(resId);
    }

    @Override
    public void printLog(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void printLog(String tag, int resId) {
        Log.d(tag, getString(resId));
    }

    @Override
    public void printLog(String tag, String message, Throwable tr) {
        Log.e(tag, message, tr);
    }

    @Override
    public void printLog(String tag, int resId, Throwable tr) {
        Log.e(tag, getString(resId), tr);
    }

    @Override
    public boolean isNetworkConnected() { return mActivity != null && mActivity.isNetworkConnected(); }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) mActivity.hideKeyboard();
    }
}
