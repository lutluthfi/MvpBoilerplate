package com.agit.lutluthfi.mvpboilerplate.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Pattern;

public final class TextUtils {

    private TextUtils() {
        // class is not publicly instantiate
    }

    public static boolean isPasswordStrong(String password) {
        final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$).{8,}";
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    public static boolean isPhoneNumberValid(String phone) {
        final String PHONE_PATTERN = "\\+?([ -]?\\d+)+|\\(\\d+\\)([ -]\\d+)";
        return Pattern.compile(PHONE_PATTERN).matcher(phone).matches();
    }

    public static boolean isEmailValid(String email) {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public static String parseCurrencyToIDR(String money) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols format = new DecimalFormatSymbols();
        format.setCurrencySymbol("Rp. ");
        format.setMonetaryDecimalSeparator(',');
        format.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(format);
        return kursIndonesia.format(Integer.parseInt(money));
    }
}
