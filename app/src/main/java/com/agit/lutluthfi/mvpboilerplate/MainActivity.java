package com.agit.lutluthfi.mvpboilerplate;

import android.os.Bundle;

import com.agit.lutluthfi.mvpboilerplate.base.PlateBaseActivity;
import com.agit.lutluthfi.mvpboilerplate.utils.FileUtils;

import java.io.IOException;

public class MainActivity extends PlateBaseActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setupView() {
        try {
            FileUtils.loadJSONFromAsset(this, "{path}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        showMessage("");
    }

    @Override
    protected void setupListener() {

    }
}
