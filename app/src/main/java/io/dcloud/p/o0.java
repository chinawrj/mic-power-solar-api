package io.dcloud.p;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;

/* loaded from: classes3.dex */
public abstract class o0 {
    private static String a = "";
    private static String b = "";

    private static boolean a() throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            return !TextUtils.isEmpty((String) cls.getMethod("getOsBrand", null).invoke(cls, null));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(a)) {
            e(str);
        }
        return a;
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(a)) {
            e(str);
        }
        return b;
    }

    private static String d(String str) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void e(java.lang.String r5) {
        /*
            Method dump skipped, instructions count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.o0.e(java.lang.String):void");
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replaceAll(Operators.SPACE_STR, "").toUpperCase();
    }
}
