package com.nky.nkyble.bean;

import com.clj.fastble.data.BleDevice;
import java.io.Serializable;

/* loaded from: classes.dex */
public class BleBean implements Serializable {
    private String address;
    private String bleName;
    private BleDevice device;
    private String status;
    private String type;

    public String getBleName() {
        return this.bleName;
    }

    public void setBleName(String str) {
        this.bleName = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public BleDevice getDevice() {
        return this.device;
    }

    public void setDevice(BleDevice bleDevice) {
        this.device = bleDevice;
    }
}
