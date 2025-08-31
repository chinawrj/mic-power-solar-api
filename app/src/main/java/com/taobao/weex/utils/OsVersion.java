package com.taobao.weex.utils;

import android.os.Build;

/* loaded from: classes3.dex */
public class OsVersion {
    private static boolean sIsAtLeastJB_MR2;

    static {
        sIsAtLeastJB_MR2 = getApiVersion() >= 18;
    }

    public static int getApiVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static boolean isAtLeastJB_MR2() {
        return sIsAtLeastJB_MR2;
    }
}
