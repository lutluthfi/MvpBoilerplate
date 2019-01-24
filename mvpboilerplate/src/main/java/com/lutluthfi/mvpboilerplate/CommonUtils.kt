package com.lutluthfi.mvpboilerplate

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CommonUtils {

    // Shows  "Monday, 8 October 2012"
    val timeStamp: String
        get() = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.US).format(Date())

    @SuppressLint(value = ["HardwareIds"])
    fun getDeviceID(context: Context): String =
            Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}// This utility class is not publicly instantiable
