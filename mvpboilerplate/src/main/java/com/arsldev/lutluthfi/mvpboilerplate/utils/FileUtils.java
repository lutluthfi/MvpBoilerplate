package com.arsldev.lutluthfi.mvpboilerplate.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public final class FileUtils {

    private FileUtils() {
        // This utility is not publicly instantiable
    }

    public static String loadJSONFromAsset(Context context, String fileName) throws IOException {
        InputStream is = context.getAssets().open(fileName);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer, "UTF-8");
    }
}
