package com.nky.nkyble.wifi;

/* loaded from: classes.dex */
public class WiFiInfo {
    private boolean is5g;
    private String wifiName;
    private String wifiRssi;

    public String getWifiName() {
        return this.wifiName;
    }

    public void setWifiName(String str) {
        this.wifiName = str;
    }

    public String getWifiRssi() {
        return this.wifiRssi;
    }

    public void setWifiRssi(String str) {
        this.wifiRssi = str;
    }

    public boolean isIs5g() {
        return this.is5g;
    }

    public void setIs5g(boolean z) {
        this.is5g = z;
    }
}
