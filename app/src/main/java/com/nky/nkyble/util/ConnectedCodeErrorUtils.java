package com.nky.nkyble.util;

import com.nky.nkyble.bean.ConnectError;

/* loaded from: classes.dex */
public class ConnectedCodeErrorUtils {
    public static ConnectError getErrorByCode(int i) {
        switch (i) {
            case ConnectError.BLE_CONNECTED /* 301 */:
                return new ConnectError(i, "设备已经连接");
            case ConnectError.MTU_SET_FAIL /* 302 */:
                return new ConnectError(i, "mtu 设置失败");
            case ConnectError.BLUETOOTHADAPTER_NOT_INITIALIZED /* 303 */:
                return new ConnectError(i, "蓝牙适配器Adapter未初始化");
            case ConnectError.BLE_CONNECTING /* 304 */:
                return new ConnectError(i, "正在连接中");
            case ConnectError.SERVICE_NULL /* 305 */:
                return new ConnectError(i, "找不到service");
            case ConnectError.ERROR_CODE_TIMEOUT /* 306 */:
                return new ConnectError(i, "连接超时");
            case 307:
                return new ConnectError(i, "Gatt 发生异常中断");
            case 308:
                return new ConnectError(i, "其他错误");
            default:
                return null;
        }
    }
}
