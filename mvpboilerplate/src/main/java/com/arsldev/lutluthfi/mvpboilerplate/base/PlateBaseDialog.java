package com.arsldev.lutluthfi.mvpboilerplate.base;

import android.app.Dialog;
import android.app.ProgressDialog;
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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arsldev.lutluthfi.mvpboilerplate.R;
import com.arsldev.lutluthfi.mvpboilerplate.utils.CommonUtils;
import com.arsldev.lutluthfi.mvpboilerplate.utils.KeyboardUtils;
import com.arsldev.lutluthfi.mvpboilerplate.utils.NetworkUtils;

import butterknife.Unbinder;

public abstract class PlateBaseDialog extends DialogFragment implements IPlateBaseView {

    private Context mContext;
    private ProgressDialog mProgressDialog;
    private Unbinder mUnBinder;

    protected abstract void setupView(View view);

    public void show(FragmentManager fm, String tag) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prevFragment = fm.findFragmentByTag(tag);
        if (prevFragment != null) ft.remove(prevFragment);
        ft.addToBackStack(null);
        show(ft, tag);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mContext == null) mContext = getActivity() != null ? getActivity() : getContext();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        final Dialog dialog = new Dialog(mContext);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setContentView(root);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) mUnBinder.unbind();
        super.onDestroy();
    }

    public Context getPlateContext() {
        return mContext;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(mContext);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.cancel();
    }

    @Override
    public boolean isLoading() {
        return mProgressDialog.isShowing();
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(mContext);
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.hideSoftInput((PlateBaseActivity) mContext);
    }

    @Override
    public void onError(String message) {
        ((PlateBaseActivity) mContext).onError(message);
    }

    @Override
    public void onError(int resId) {
        ((PlateBaseActivity) mContext).onError(resId);
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, getString(R.string.error_general), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(int resId) {
        showMessage(getString(resId));
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
}
