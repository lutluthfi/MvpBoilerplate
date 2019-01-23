package com.agit.lutluthfi.mvpboilerplate

import android.content.Context
import android.content.Intent

object ShareUtils {

    fun shareTextViaApplication(context: Context?,
                                title: String = "Share via",
                                content: String = "") {
        val intent = Intent()
        context?.let {
            with(intent) {
                type = "text/plain"
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, content)
                it.startActivity(Intent.createChooser(this, title))
            }
        }
    }

    fun shareTextViaEmail(context: Context?,
                          email: String = "",
                          subject: String = "Hello there",
                          content: String = "") {
        val intent = Intent()
        context?.let {
            with(intent) {
                action = Intent.ACTION_SEND
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, email)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, content)
                it.startActivity(this)
            }
        }
    }
}// This class is not publicly instantiate

