package io.dcloud.p;

import android.content.Context;
import android.os.Build;

/* loaded from: classes3.dex */
public abstract class k1 {
    public static boolean a(Context context) {
        int i = Build.VERSION.SDK_INT;
        return (i >= 29 && context.getApplicationInfo().targetSdkVersion >= 29) || i >= 30;
    }
}
