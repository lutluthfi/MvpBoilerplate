package com.agit.lutluthfi.mvpboilerplate.base;

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

import com.agit.lutluthfi.mvpboilerplate.R;
import com.agit.lutluthfi.mvpboilerplate.utils.CommonUtils;
import com.agit.lutluthfi.mvpboilerplate.utils.KeyboardUtils;
import com.agit.lutluthfi.mvpboilerplate.utils.NetworkUtils;

public abstract class PlateBaseActivity extends AppCompatActivity implements IPlateBaseView {

    private Snackbar mSnackbar;
    private ProgressDialog mProgressDialog;

    protected abstract void setupView();

    protected abstract void setupListener();

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.cancel();
    }

    @Override
    public boolean isLoading() {
        return mProgressDialog != null && mProgressDialog.isShowing();
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void onError(String message) {
        if (message != null && !message.isEmpty()) {
            showSnackbar(message, Snackbar.LENGTH_SHORT, R.color.colorWhite);
        } else {
            showSnackbar(getString(R.string.utils_error_view), Snackbar.LENGTH_SHORT, R.color.colorWhite);
        }
    }

    @Override
    public void onError(int resId) {
        showSnackbar(getString(resId), Snackbar.LENGTH_SHORT, R.color.colorWhite);
    }

    @Override
    public void showMessage(String message) {
        if (message != null && !message.isEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.utils_error_view), Toast.LENGTH_SHORT).show();
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
        hideSnackbar();
        createSnackbar(message, duration, textColor).show();
    }

    @Override
    public void showSnackbar(int resId, int duration, int textColor) {
        hideSnackbar();
        createSnackbar(getString(resId), duration, textColor).show();
    }
    @Override
    public void showSnackbarWithAction(String message, int duration, int textColor, String action, View.OnClickListener listener) {
        hideSnackbar();
        createSnackbar(message, duration, textColor).setAction(action, listener).show();
    }

    @Override
    public void showSnackbarWithAction(int resId, int duration, int textColor, String action, View.OnClickListener listener) {
        hideSnackbar();
        createSnackbar(getString(resId), duration, textColor).setAction(action, listener).show();
    }

    @Override
    public void hideSnackbar() {
        if (mSnackbar != null && mSnackbar.isShown()) mSnackbar.dismiss();
    }

    @Override
    public boolean isSnackbarShowing() {
        return mSnackbar != null && mSnackbar.isShown();
    }

    private Snackbar createSnackbar(String message, int duration, int textColor) {
        if (message != null && !message.isEmpty()) {
            mSnackbar = Snackbar.make(findViewById(android.R.id.content), message, duration);
            View snackbarView = mSnackbar.getView();
            TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(this, textColor));
        } else {
            mSnackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.utils_error_common), duration);
            View snackbarView = mSnackbar.getView();
            TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(this, textColor));
        }
        return mSnackbar;
    }
}
