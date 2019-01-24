package com.lutluthfi.mvpboilerplate

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log

/**
 * Created by Arif Luthfiansyah on 24-January-2019
 * Contact me on lutluthfiansyah@gmail.com
 */
object BottomNavigationUtils {

    private val TAG: String = BottomNavigationUtils::class.java.simpleName

    @SuppressLint(value = ["RestrictedApi"])
    fun shiftingBottomNavigation(navigation: BottomNavigationView) {
        val menuView = navigation.getChildAt(0) as BottomNavigationMenuView
        try {
            menuView.javaClass.getDeclaredField("shiftingMode").apply {
                isAccessible = true
                setBoolean(menuView, false)
                isAccessible = false
            }
            for (i in 0 until menuView.childCount) {
                (menuView.getChildAt(i) as BottomNavigationItemView).apply {
                    setShifting(false)
                    setChecked(itemData.isChecked)
                }
            }
        } catch (e: NoSuchFieldException) {
            Log.e(TAG, "Unable to get shift mode field", e)
        } catch (e: IllegalAccessException) {
            Log.e(TAG, "Unable to get shift mode field", e)
        }
    }
}