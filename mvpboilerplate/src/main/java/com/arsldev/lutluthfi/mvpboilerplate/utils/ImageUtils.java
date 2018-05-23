package com.arsldev.lutluthfi.mvpboilerplate.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public final class ImageUtils {

    private ImageUtils() {
        // This utility is not publicly instantiable
    }

    public static void loadImage(Context context, String path, ImageView holder) {
        Glide.with(context).load(path).asBitmap().centerCrop().into(holder);
    }

    // TODO : not provide for custom target
}
