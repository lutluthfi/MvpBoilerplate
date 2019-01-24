package com.lutluthfi.mvpboilerplate

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

/**
 * Created by Arif Luthfiansyah on 24-January-2019
 * Contact me on lutluthfiansyah@gmail.com
 */
object LoadingUtils {

    fun showLoadingDialog(context: Context): ProgressDialog = ProgressDialog(context).apply {
        window?.run { setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) }
        setContentView(R.layout.util_loading)
        isIndeterminate = true
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        show()
    }
}