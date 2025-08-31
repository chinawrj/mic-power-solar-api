package io.dcloud.p;

import android.os.Handler;
import android.os.HandlerThread;

/* loaded from: classes3.dex */
public abstract class z2 {
    private static volatile HandlerThread a = new HandlerThread("dcloud_thread", -19);
    private static volatile Handler b;

    static {
        a.start();
        b = new Handler(a.getLooper());
    }

    public static Handler a() {
        if (a == null || !a.isAlive()) {
            synchronized (z2.class) {
                if (a == null || !a.isAlive()) {
                    a = new HandlerThread("dcloud_thread", -19);
                    a.start();
                    b = new Handler(a.getLooper());
                }
            }
        }
        return b;
    }
}
