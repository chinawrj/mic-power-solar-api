package com.nky.nkyble.util;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import com.nky.nkyble.App;
import com.taobao.weex.el.parse.Operators;
import java.util.UUID;

/* loaded from: classes.dex */
public class BluetoothUtils {
    public static final int REQUEST_ENABLE_BT = 10000;

    public static void initScanSettings() {
        ScanSettings.Builder scanMode = new ScanSettings.Builder().setScanMode(2);
        scanMode.setCallbackType(1);
        scanMode.setMatchMode(2);
        if (BluetoothAdapter.getDefaultAdapter().isOffloadedScanBatchingSupported()) {
            scanMode.setReportDelay(0L);
        }
        scanMode.build();
    }

    public static BluetoothManager getBluetoothManager() {
        return (BluetoothManager) App.context.getSystemService("bluetooth");
    }

    public static BluetoothAdapter getBluetoothAdapter() {
        return BluetoothAdapter.getDefaultAdapter();
    }

    public static BluetoothLeScanner getBluetoothLeScanner() {
        BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
        if (bluetoothAdapter == null) {
            return null;
        }
        return bluetoothAdapter.getBluetoothLeScanner();
    }

    public static boolean isSupportBle() {
        return getBluetoothAdapter() != null && App.context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public static boolean isBluetoothOpen() {
        return getBluetoothAdapter().isEnabled();
    }

    public static BluetoothDevice getBluetoothDevice(String str) {
        BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.getRemoteDevice(str);
        }
        return null;
    }

    public static BluetoothGattCharacteristic getCharacter(BluetoothGatt bluetoothGatt, UUID uuid, UUID uuid2) {
        BluetoothGattService service = bluetoothGatt.getService(uuid);
        if (service != null) {
            return service.getCharacteristic(uuid2);
        }
        return null;
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
            sb.append(Operators.SPACE_STR);
        }
        return sb.toString();
    }

    public static void openBluetooth(Activity activity, int i) {
        activity.startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), i);
    }
}
