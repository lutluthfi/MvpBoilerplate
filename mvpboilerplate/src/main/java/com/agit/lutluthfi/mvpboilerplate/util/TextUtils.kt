package com.agit.lutluthfi.mvpboilerplate.util

import android.content.ClipData
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.regex.Pattern

object TextUtils {

    /**
     * You could make use of \w, but it also no tolerates the underscore character.
     *
     * @param message A message (ex: mvpboilerplate)
     * @return true message is exclude non alphanumeric | false message is include non alphanumeric
     */
    fun isExcludeNonAlphanumeric(message: String): Boolean {
        val regex = "^[a-zA-Z0-9]+$"
        return Pattern.compile(regex).matcher(message).matches()
    }

    fun isPasswordStrong(password: String): Boolean {
        return password.length >= 6 && !password.contains(" ")
        // final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$).{8,}";
        // return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    /**
     * Positive integers of maximum length 12
     *
     * @param phone A phone number (ex: 085212341234)
     * @return true phone number is valid | false phone number is invalid
     */
    fun isPhoneNumberValid(phone: String): Boolean {
        val regex = "^\\d{1,12}$"
        return Pattern.compile(regex).matcher(phone).matches()
    }

    /**
     * There is no 100% reliable solution since the RFC is way too complex.
     * This is the best solution and should work 99% of the time is.
     * Consult this page for more details on this problem. Always turn off case sensitivity!
     *
     * @param email An email (ex: boilerplate@mail.com)
     * @return true email is valid | false email is invalid
     */
    fun isEmailValid(email: String): Boolean {
        val regex = "^[-a-z0-9~!$%^&*_=+}{'?]+(\\.[-a-z0-9~!$%^&*_=+}{'?]+)*@([a-z0-9_][-a-z0-9_]" +
                "*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|" +
                "[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$"
        // final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
        //         + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regex).matcher(email).matches()
    }

    fun parseCurrencyToIDR(money: String): String {
        val currencyIndonesia = DecimalFormat.getCurrencyInstance() as DecimalFormat
        val format = DecimalFormatSymbols()
        with(format) {
            currencySymbol = "Rp. "
            monetaryDecimalSeparator = ','
            groupingSeparator = '.'
            currencyIndonesia.decimalFormatSymbols = this
        }
        return currencyIndonesia.format(Integer.parseInt(money).toLong())
    }

    fun copyToClipboard(context: Context, content: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(ClipDescription.MIMETYPE_TEXT_PLAIN, content)
        clipboard.primaryClip = clip
    }
}// class is not publicly instantiate
