package com.nky.nkyble.wifi;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public class WifiScanHelper {
    private WiFiScanCallback callback;

    public static class RequestHelper {
        public static WifiScanHelper instance = new WifiScanHelper();
    }

    public static WifiScanHelper getInstance() {
        return RequestHelper.instance;
    }

    public WiFiScanCallback getCallback() {
        return this.callback;
    }

    public void requestScan(Context context, WiFiScanCallback wiFiScanCallback) {
        this.callback = wiFiScanCallback;
        context.startActivity(new Intent(context, (Class<?>) WiFiScan.class));
    }
}
