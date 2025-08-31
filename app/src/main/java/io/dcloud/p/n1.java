package io.dcloud.p;

import android.content.Context;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class n1 {
    private static volatile n1 a;

    private n1() {
    }

    public static n1 a() {
        if (a == null) {
            synchronized (n1.class) {
                if (a == null) {
                    a = new n1();
                }
            }
        }
        return a;
    }

    public String a(Context context) {
        try {
            if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.containsKey("com.google.android.gms.ads.APPLICATION_ID")) {
                Method declaredMethod = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getDeclaredMethod("getAdvertisingIdInfo", Context.class);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(null, context);
                Method declaredMethod2 = objInvoke.getClass().getDeclaredMethod("getId", null);
                declaredMethod2.setAccessible(true);
                return (String) declaredMethod2.invoke(objInvoke, null);
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }
}
