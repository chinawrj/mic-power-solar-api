package com.nky.nkyble.callback;

import com.clj.fastble.data.BleDevice;
import com.nky.nkyble.bean.BleBean;
import java.util.List;

/* loaded from: classes.dex */
public interface BleScanCallback {
    void bleScanFail();

    void bleScanStart();

    void off();

    void scanFinish(List<BleDevice> list);

    void scanning(BleBean bleBean);
}
