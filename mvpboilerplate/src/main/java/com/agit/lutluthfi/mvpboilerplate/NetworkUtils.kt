package com.agit.lutluthfi.mvpboilerplate

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Observable

object NetworkUtils {

    fun isNetworkConnected(context: Context): Observable<Boolean> {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return Observable.just(activeNetwork != null && activeNetwork.isConnected)
    }
}// This utility class is not publicly instantiable
