package com.agit.lutluthfi.mvpboilerplate

import android.content.Context
import android.content.Intent

object ShareUtils {

    fun shareTextViaApplication(context: Context?,
                                title: String = "Share via",
                                content: String = "") {
        context?.run {
            startActivity(Intent.createChooser(Intent().apply {
                type = "text/plain"
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, content)
            }, title))
        }
    }

    fun shareTextViaEmail(context: Context?,
                          email: String = "",
                          subject: String = "Hello there",
                          content: String = "") {
        context?.run {
            startActivity(Intent().apply {
                action = Intent.ACTION_SEND
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, email)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, content)
            })
        }
    }
}// This class is not publicly instantiate

