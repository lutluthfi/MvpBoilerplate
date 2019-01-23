package com.lutluthfi.mvpboilerplate;

import android.os.Bundle;

import com.lutluthfi.mvpboilerplate.base.BaseActivity;

public class MainActivity extends BaseActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setupView() {
    }

    @Override
    protected void setupListener() {

    }
}
