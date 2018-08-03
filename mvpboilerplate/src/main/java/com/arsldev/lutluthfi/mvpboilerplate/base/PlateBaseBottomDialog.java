package com.arsldev.lutluthfi.mvpboilerplate.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
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

public abstract class PlateBaseBottomDialog extends BottomSheetDialogFragment implements IPlateBaseView {

    private ProgressDialog mProgressDialog;
    protected Context fragmentContext;

    protected abstract void setupView(View view);

    protected abstract void setupListener();

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
        if (fragmentContext == null) fragmentContext = getActivity() != null ? getActivity() : getContext();
    }

    @NonNull @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        final BottomSheetDialog dialog = new BottomSheetDialog(fragmentContext);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setContentView(root);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        return dialog;
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
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.cancel();
    }

    @Override
    public boolean isLoading() {
        return mProgressDialog.isShowing();
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
