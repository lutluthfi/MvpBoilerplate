package com.arsldev.lutluthfi.mvpboilerplate.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.Unbinder;

public abstract class PlateBaseDialog extends DialogFragment implements IPlateDialogView {

    private PlateBaseActivity mActivity;
    private Unbinder mUnBinder;

    protected abstract void setupView(View view);

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        final Dialog dialog = new Dialog(getBaseActivity());
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setContentView(root);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        return dialog;
    }

    public void show (FragmentManager fm, String tag) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prevFragment = fm.findFragmentByTag(tag);
        if (prevFragment != null) ft.remove(prevFragment);
        ft.addToBackStack(null);
        show(ft, tag);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PlateBaseActivity) {
            this.mActivity = (PlateBaseActivity) context;
            this.mActivity.onFragmentAttached();
        }
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) mUnBinder.unbind();
        super.onDestroy();
    }

    public PlateBaseActivity getBaseActivity() {
        return mActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void dismissDialog(String tag) {
        dismiss();
        getBaseActivity().onFragmentDetached(tag);
    }

    @Override
    public void showLoading() {
        if (mActivity != null) mActivity.showLoading();
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) mActivity.hideLoading();
    }

    @Override
    public boolean isLoading() {
        if (mActivity != null) {
            mActivity.isLoading();
        }
        return false;
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) mActivity.hideKeyboard();
    }

    @Override
    public void onError(String message) {
        if (mActivity != null) mActivity.onError(message);
    }

    @Override
    public void onError(int resId) {
        if (mActivity != null) mActivity.onError(resId);
    }

    @Override
    public void showMessage(String message) {
        if (mActivity != null) mActivity.showMessage(message);
    }

    @Override
    public void showMessage(int resId) {
        if (mActivity != null) mActivity.showMessage(resId);
    }

    @Override
    public void printLog(String tag, String message) {
        if (mActivity != null) mActivity.printLog(tag, message);
    }

    @Override
    public void printLog(String tag, int resId) {
        if (mActivity != null) mActivity.printLog(tag, resId);
    }

    @Override
    public void printLog(String tag, String message, Throwable tr) {
        if (mActivity != null) mActivity.printLog(tag, message, tr);
    }

    @Override
    public void printLog(String tag, int resId, Throwable tr) {
        if (mActivity != null) mActivity.printLog(tag, resId, tr);
    }
}
