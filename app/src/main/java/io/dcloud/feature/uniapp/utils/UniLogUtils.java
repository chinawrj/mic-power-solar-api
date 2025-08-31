package io.dcloud.feature.uniapp.utils;

import com.taobao.weex.utils.WXLogUtils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes3.dex */
public class UniLogUtils extends WXLogUtils {
    public static final String UNI_PERF_TAG = "uni_perf";
    public static final String UNI_TAG = "uni";

    public static void d(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        WXLogUtils.d(UNI_TAG, str);
    }

    public static void e(String str) {
        WXLogUtils.e(UNI_TAG, str);
    }

    public static void i(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        WXLogUtils.i(UNI_TAG, str);
    }

    public static void info(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        WXLogUtils.i(UNI_TAG, str);
    }

    public static void v(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        WXLogUtils.v(UNI_TAG, str);
    }

    public static void w(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        WXLogUtils.w(UNI_TAG, str);
    }

    public static void d(String str, byte[] bArr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        WXLogUtils.d(str, new String(bArr));
    }

    public static void e(String str, byte[] bArr) {
        WXLogUtils.e(str, new String(bArr));
    }

    public static void i(String str, byte[] bArr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        WXLogUtils.i(str, new String(bArr));
    }

    public static void w(String str, byte[] bArr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        WXLogUtils.w(str, new String(bArr));
    }
}
