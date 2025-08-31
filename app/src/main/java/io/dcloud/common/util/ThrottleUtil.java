package io.dcloud.common.util;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes3.dex */
public class ThrottleUtil {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable readyToRun;

    public void throttlePost(final Runnable runnable, long j) {
        Runnable runnable2 = this.readyToRun;
        if (runnable2 != null) {
            this.handler.removeCallbacks(runnable2);
        }
        Runnable runnable3 = new Runnable() { // from class: io.dcloud.common.util.ThrottleUtil.1
            @Override // java.lang.Runnable
            public void run() {
                runnable.run();
            }
        };
        this.readyToRun = runnable3;
        this.handler.postDelayed(runnable3, j);
    }
}
