package io.dcloud.p;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;

/* loaded from: classes3.dex */
public abstract class e1 {
    private static String a;
    private static String b;

    public static String a() {
        if (TextUtils.isEmpty(b)) {
            try {
                b = a("@\\ED=XD][Z]F\\AEM");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    public static String b() {
        if (TextUtils.isEmpty(a)) {
            try {
                a = a("LKdg}l.:\"8V9+>88");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return a;
    }

    private static String a(String str) throws UnsupportedEncodingException {
        byte[] bytes = str.getBytes("GBK");
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] ^ 8);
        }
        return new String(bytes, "GBK");
    }
}
