package com.nky.nkyble.wifi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import io.dcloud.common.util.net.NetCheckReceiver;

/* loaded from: classes.dex */
public class WiFiBrocastReceiver extends BroadcastReceiver {
    private Context context;
    private WifiChangeLiseners liseners;

    public interface WifiChangeLiseners {
        void scanResultAction();

        void wifiStateChange();
    }

    public WiFiBrocastReceiver(Context context, WifiChangeLiseners wifiChangeLiseners) {
        this.context = context;
        this.liseners = wifiChangeLiseners;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            action.hashCode();
            switch (action) {
                case "android.net.conn.CONNECTIVITY_CHANGE":
                case "android.net.wifi.STATE_CHANGE":
                case "android.net.wifi.supplicant.STATE_CHANGE":
                case "android.net.wifi.LINK_CONFIGURATION_CHANGED":
                case "android.net.wifi.CONFIGURED_NETWORKS_CHANGE":
                    WifiChangeLiseners wifiChangeLiseners = this.liseners;
                    if (wifiChangeLiseners != null) {
                        wifiChangeLiseners.wifiStateChange();
                        break;
                    }
                    break;
                case "android.net.wifi.SCAN_RESULTS":
                    WifiChangeLiseners wifiChangeLiseners2 = this.liseners;
                    if (wifiChangeLiseners2 != null) {
                        wifiChangeLiseners2.scanResultAction();
                        break;
                    }
                    break;
            }
        }
    }

    public void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetCheckReceiver.netACTION);
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.net.wifi.CONFIGURED_NETWORKS_CHANGE");
        intentFilter.addAction("android.net.wifi.LINK_CONFIGURATION_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        this.context.registerReceiver(this, intentFilter);
    }

    public void unRegister() {
        this.context.unregisterReceiver(this);
    }
}
