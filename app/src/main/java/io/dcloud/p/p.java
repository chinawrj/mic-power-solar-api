package io.dcloud.p;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes3.dex */
public abstract class p {
    private static final String[] a = {"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};

    public static boolean a(Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "accessibility_enabled") == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0) == 1;
    }

    public static boolean c(Context context) {
        String property = System.getProperty("http.proxyHost");
        String property2 = System.getProperty("http.proxyPort");
        if (property2 == null) {
            property2 = "-1";
        }
        return (TextUtils.isEmpty(property) || Integer.parseInt(property2) == -1) ? false : true;
    }

    public static boolean a() {
        boolean z;
        String[] strArr = a;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (new File(strArr[i]).exists()) {
                z = true;
                break;
            }
            i++;
        }
        String str = Build.TAGS;
        return (str != null && str.contains("test-keys")) || z;
    }
}
