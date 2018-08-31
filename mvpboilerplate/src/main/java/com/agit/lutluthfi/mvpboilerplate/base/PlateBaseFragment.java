package com.agit.lutluthfi.mvpboilerplate.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.agit.lutluthfi.mvpboilerplate.R;
import com.agit.lutluthfi.mvpboilerplate.utils.CommonUtils;
import com.agit.lutluthfi.mvpboilerplate.utils.KeyboardUtils;
import com.agit.lutluthfi.mvpboilerplate.utils.NetworkUtils;

public abstract class PlateBaseFragment extends Fragment implements IPlateBaseView {

    private ProgressDialog mProgressDialog;
    protected Context fragmentContext;

    protected abstract void setupView(View view);

    protected abstract void setupListener();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (fragmentContext == null) fragmentContext = getActivity() != null ? getActivity() : getContext();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        setupListener();
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(fragmentContext);
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
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(fragmentContext);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void showMessage(String message) {
        if (message != null && !message.isEmpty()) {
            Toast.makeText(fragmentContext, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(fragmentContext, getString(R.string.utils_error_view), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(int resId) {
        showMessage(getString(resId));
    }

    @Override
    public void printLog(String tag, String message) {
        if (message != null && !message.isEmpty()) Log.d(tag, message);
        else Log.d(tag, getString(R.string.utils_error_view));
    }

    @Override
    public void printLog(String tag, int resId) {
        printLog(tag, getString(resId));
    }

    @Override
    public void printLog(String tag, Throwable tr) {
        Log.d(tag, tr.getMessage(), tr);
    }

    @Override
    public void showSnackbar(String message, int duration, int textColor) {

    }

    @Override
    public void showSnackbar(int resId, int duration, int textColor) {

    }

    @Override
    public void showSnackbarWithAction(String message, int duration, int textColor, String action, View.OnClickListener listener) {

    }

    @Override
    public void showSnackbarWithAction(int resId, int duration, int textColor, String action, View.OnClickListener listener) {

    }

    @Override
    public void hideSnackbar() {

    }

    @Override
    public boolean isSnackbarShowing() {
        return false;
    }
}
