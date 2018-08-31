package com.agit.lutluthfi.mvpboilerplate.network;

public final class NetworkConstant {

    public static final int CONNECTION_TIME_OUT = 30000;

    public static final int CONNECTION_READ_TIME_OUT = 120000;

    public static final int CONNECTION_MAX_IDLE = 5;

    public static final int CONNECTION_ALIVE_DURATION = 5 * 60 * 1000;

    private NetworkConstant() {
        // This class is not publicly instantiate
    }
}
