package com.arsldev.lutluthfi.mvpboilerplate.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public final class FormatUtils {

    private FormatUtils() {
        // This utility class is not publicly instantiable
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
