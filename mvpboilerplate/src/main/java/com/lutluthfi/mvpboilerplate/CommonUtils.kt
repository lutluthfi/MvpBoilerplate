package com.lutluthfi.mvpboilerplate

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CommonUtils {

    private val TAG = CommonUtils::class.java.simpleName

    // Shows  "Monday, 8 October 2012"
    val timeStamp: String
        get() = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.US).format(Date())

    fun showLoadingDialog(context: Context): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        with(progressDialog) {
            window?.run { setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) }
            setContentView(R.layout.util_progress_dialog)
            isIndeterminate = true
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            show()
        }
        return progressDialog
    }

    @SuppressLint(value = ["RestrictedApi"])
    fun shiftingBottomNavigation(navigation: BottomNavigationView) {
        val menuView = navigation.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("shiftingMode")
            with(shiftingMode) {
                isAccessible = true
                setBoolean(menuView, false)
                isAccessible = false
            }
            for (i in 0 until menuView.childCount) {
                val itemMenu = menuView.getChildAt(i) as BottomNavigationItemView
                with(itemMenu) {
                    setShifting(false)
                    setChecked(itemMenu.itemData.isChecked)
                }
            }
        } catch (e: NoSuchFieldException) {
            Log.e(TAG, "Unable to get shift mode field", e)
        } catch (e: IllegalAccessException) {
            Log.e(TAG, "Unable to get shift mode field", e)
        }

    }

    @SuppressLint(value = ["HardwareIds"])
    fun getDeviceID(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}// This utility class is not publicly instantiable
