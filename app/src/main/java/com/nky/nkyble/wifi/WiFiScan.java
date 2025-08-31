package com.nky.nkyble.wifi;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import com.nky.nkyble.wifi.receiver.WiFiBrocastReceiver;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class WiFiScan extends AppCompatActivity {
    private List<WiFiInfo> accessPoints = new ArrayList();
    private WiFiBrocastReceiver wiFiBrocastReceiver;
    private WifiManager wifiManager;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
        this.wifiManager = wifiManager;
        if (wifiManager != null && !wifiManager.isWifiEnabled()) {
            this.wifiManager.setWifiEnabled(true);
        }
        startScan();
        new Handler().postDelayed(new Runnable() { // from class: com.nky.nkyble.wifi.WiFiScan$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m237lambda$onCreate$0$comnkynkyblewifiWiFiScan();
            }
        }, 4000L);
    }

    /* renamed from: lambda$onCreate$0$com-nky-nkyble-wifi-WiFiScan, reason: not valid java name */
    /* synthetic */ void m237lambda$onCreate$0$comnkynkyblewifiWiFiScan() {
        WiFiScanCallback callback = WifiScanHelper.getInstance().getCallback();
        if (callback != null) {
            updateAccessPoints();
            callback.onResult(this.accessPoints);
        }
        finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    private void initReceiver() {
        this.wiFiBrocastReceiver = new WiFiBrocastReceiver(this, new WiFiBrocastReceiver.WifiChangeLiseners() { // from class: com.nky.nkyble.wifi.WiFiScan.1
            @Override // com.nky.nkyble.wifi.receiver.WiFiBrocastReceiver.WifiChangeLiseners
            public void wifiStateChange() {
            }

            @Override // com.nky.nkyble.wifi.receiver.WiFiBrocastReceiver.WifiChangeLiseners
            public void scanResultAction() {
                WiFiScan.this.updateAccessPoints();
            }
        });
    }

    public void updateAccessPoints() {
        this.accessPoints = new ArrayList();
        List<ScanResult> scanResults = this.wifiManager.getScanResults();
        if (scanResults != null) {
            for (ScanResult scanResult : scanResults) {
                if (!TextUtils.isEmpty(scanResult.SSID)) {
                    WiFiInfo wiFiInfo = new WiFiInfo();
                    wiFiInfo.setWifiName(scanResult.SSID);
                    wiFiInfo.setWifiRssi(String.valueOf(WifiManager.calculateSignalLevel(scanResult.level, 100)));
                    this.accessPoints.add(wiFiInfo);
                }
            }
        }
    }

    public void startScan() {
        WifiManager wifiManager = this.wifiManager;
        if (wifiManager != null) {
            wifiManager.startScan();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean is5GHz(String str) {
        try {
            WifiInfo connectionInfo = this.wifiManager.getConnectionInfo();
            if (connectionInfo != null) {
                int frequency = connectionInfo.getFrequency();
                return frequency > 4900 && frequency < 5900;
            }
            return str.toUpperCase().endsWith("5G");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
