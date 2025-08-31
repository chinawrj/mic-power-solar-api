package com.nky.nkyble.bean;

/* loaded from: classes.dex */
public class ConnectError {
    public static final int BLE_CONNECTED = 301;
    public static final int BLE_CONNECTING = 304;
    public static final int BLUETOOTHADAPTER_NOT_INITIALIZED = 303;
    public static final int ERROR_CODE_GATT = 307;
    public static final int ERROR_CODE_OTHER = 308;
    public static final int ERROR_CODE_TIMEOUT = 306;
    public static final int MTU_SET_FAIL = 302;
    public static final int SERVICE_NULL = 305;
    public int code;
    public String msg;

    public ConnectError() {
    }

    public ConnectError(int i, String str) {
        this.code = i;
        this.msg = str;
    }
}
