package com.arsldev.lutluthfi.mvpboilerplate.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.arsldev.lutluthfi.mvpboilerplate.R;
import com.arsldev.lutluthfi.mvpboilerplate.utils.CommonUtils;
import com.arsldev.lutluthfi.mvpboilerplate.utils.KeyboardUtils;
import com.arsldev.lutluthfi.mvpboilerplate.utils.NetworkUtils;

import butterknife.Unbinder;

public abstract class PlateBaseActivity extends AppCompatActivity implements IPlateBaseView {

    private ProgressDialog mProgressDialog;
    private Unbinder mUnbinder;

    public abstract void setupView();

    @Override
    protected void onDestroy() {
        if (mUnbinder != null) mUnbinder.unbind();
        super.onDestroy();
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnbinder = unBinder;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public boolean isLoading() {
        return mProgressDialog.isShowing();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        snackbar.show();
    }

    @Override
    public void onError(String message) {
        if (message != null) showSnackBar(message);
        else showSnackBar(getString(R.string.error_general));
    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.error_general), Toast.LENGTH_SHORT).show();
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
        printLog(tag, getString(resId));
    }

    @Override
    public void printLog(String tag, String message, Throwable tr) {
        Log.e(tag, message, tr);
    }

    @Override
    public void printLog(String tag, int resId, Throwable tr) {
        printLog(tag, getString(resId), tr);
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.hideSoftInput(this);
    }
}
