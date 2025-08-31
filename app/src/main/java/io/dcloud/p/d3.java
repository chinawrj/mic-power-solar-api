package io.dcloud.p;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

/* loaded from: classes3.dex */
public abstract class d3 {

    public static class a {
        public long a;
        public long b;
        public long c;
        public long d;
    }

    public static long a() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    public static long b() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getBlockCount() * statFs.getBlockSize();
    }

    public static a a(Context context) {
        a aVar = new a();
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            aVar.b = memoryInfo.availMem;
            aVar.a = memoryInfo.totalMem;
            aVar.c = a();
            aVar.d = b();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return aVar;
    }
}
