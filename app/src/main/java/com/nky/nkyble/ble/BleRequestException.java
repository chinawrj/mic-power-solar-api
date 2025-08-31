package com.nky.nkyble.ble;

import java.io.Serializable;

/* loaded from: classes.dex */
public class BleRequestException implements Serializable {
    public static final int ERROR_CLIENT_DISCONNETED = 99;
    private int code;
    private String description;

    public BleRequestException(int i, String str) {
        this.code = i;
        this.description = str;
    }

    public int getCode() {
        return this.code;
    }

    public BleRequestException setCode(int i) {
        this.code = i;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public BleRequestException setDescription(String str) {
        this.description = str;
        return this;
    }

    public String toString() {
        return "BleException { code=" + this.code + ", description='" + this.description + "'}";
    }
}
