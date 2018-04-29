package com.arsldev.lutluthfi.mvpboilerplate.base;

import android.support.annotation.StringRes;

public interface IPlateBaseView {

    void showLoading();

    void hideLoading();

    boolean isLoading();

    boolean isNetworkConnected();

    void hideKeyboard();

    void onError(String message);

    void onError(@StringRes int resId);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void printLog(String tag, String message);

    void printLog(String tag, @StringRes int resId);

    void printLog(String tag, String message, Throwable tr);

    void printLog(String tag, @StringRes int resId, Throwable tr);
}
