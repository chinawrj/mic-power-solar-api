package com.nky.nkyble.ble;

import com.clj.fastble.data.BleDevice;

/* loaded from: classes.dex */
public interface IBle {
    void connet(BleDevice bleDevice, BleConnetLiseners bleConnetLiseners);

    void disConnet();

    boolean isConneted();

    void sendDataNoResepone(byte[] bArr, BleRequestCallback bleRequestCallback);

    void sendDataResepone(byte[] bArr, BleRequestCallback bleRequestCallback);
}
