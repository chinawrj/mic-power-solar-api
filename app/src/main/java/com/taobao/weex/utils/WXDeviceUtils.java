package com.taobao.weex.utils;

import android.content.Context;
import android.os.Build;

/* loaded from: classes3.dex */
public class WXDeviceUtils {
    public static boolean isAutoResize(Context context) {
        if (context == null) {
            return false;
        }
        return isMateX(context) || isGalaxyFold(context);
    }

    public static boolean isGalaxyFold(Context context) {
        return "samsung".equalsIgnoreCase(Build.BRAND) && "SM-F9000".equalsIgnoreCase(Build.MODEL);
    }

    public static boolean isMateX(Context context) {
        if ("HUAWEI".equalsIgnoreCase(Build.BRAND)) {
            String str = Build.DEVICE;
            if ("unknownRLI".equalsIgnoreCase(str) || "HWTAH".equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
