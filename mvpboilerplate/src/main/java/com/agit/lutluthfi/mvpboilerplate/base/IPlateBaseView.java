package com.agit.lutluthfi.mvpboilerplate.base;

import android.support.annotation.StringRes;
import android.view.View;

public interface IPlateBaseView {

    void showLoading();

    void hideLoading();

    boolean isLoading();

    boolean isNetworkConnected();

    void onError(String message);

    void onError(@StringRes int resId);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void printLog(String tag, String message);

    void printLog(String tag, @StringRes int resId);

    void printLog(String tag, Throwable tr);

    void showSnackbar(String message, int duration, int textColor);

    void showSnackbar(@StringRes int resId, int duration, int textColor);

    void showSnackbarWithAction(String message, int duration, int textColor, String action, View.OnClickListener listener);

    void showSnackbarWithAction(@StringRes int resId, int duration, int textColor, String action, View.OnClickListener listener);

    void hideSnackbar();

    boolean isSnackbarShowing();
}
