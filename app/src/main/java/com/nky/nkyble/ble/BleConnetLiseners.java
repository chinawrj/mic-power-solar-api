package com.nky.nkyble.ble;

import com.nky.nkyble.bean.ConnectError;

/* loaded from: classes.dex */
public interface BleConnetLiseners {
    void bleConnectedFail(ConnectError connectError);

    void bleConnectedStart();

    void bleConnectedSuccess();

    void bleDisConnected();
}
