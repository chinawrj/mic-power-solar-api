package io.dcloud.p;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class v4 {
    private static v4 b;
    ThreadPoolExecutor a = new ThreadPoolExecutor(3, 6, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());

    private v4() {
    }

    public static v4 a() {
        if (b == null) {
            synchronized (v4.class) {
                if (b == null) {
                    b = new v4();
                }
            }
        }
        return b;
    }

    public void a(Runnable runnable) {
        this.a.execute(runnable);
    }
}
