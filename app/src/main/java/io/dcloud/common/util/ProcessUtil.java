package io.dcloud.common.util;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class ProcessUtil {
    private static String currentProcessName;

    public static String getCurrentProcessName(Context context) throws IOException {
        if (!TextUtils.isEmpty(currentProcessName)) {
            return currentProcessName;
        }
        String currentProcessNameByApplication = getCurrentProcessNameByApplication();
        currentProcessName = currentProcessNameByApplication;
        if (!TextUtils.isEmpty(currentProcessNameByApplication)) {
            return currentProcessName;
        }
        String currentProcessNameByActivityThread = getCurrentProcessNameByActivityThread();
        currentProcessName = currentProcessNameByActivityThread;
        if (!TextUtils.isEmpty(currentProcessNameByActivityThread)) {
            return currentProcessName;
        }
        String currentProcessNameByCmdline = getCurrentProcessNameByCmdline();
        currentProcessName = currentProcessNameByCmdline;
        if (!TextUtils.isEmpty(currentProcessNameByCmdline)) {
            return currentProcessName;
        }
        String packageName = context.getPackageName();
        currentProcessName = packageName;
        return packageName;
    }

    public static String getCurrentProcessNameByActivityThread() {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", null);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, null);
            if (objInvoke instanceof String) {
                return (String) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String getCurrentProcessNameByApplication() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        return null;
    }

    public static String getCurrentProcessNameByCmdline() throws IOException {
        FileInputStream fileInputStream;
        byte[] bArr;
        int i;
        try {
            try {
                fileInputStream = new FileInputStream("/proc/self/cmdline");
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bArr = new byte[256];
            i = 0;
            while (true) {
                int i2 = fileInputStream.read();
                if (i2 <= 0 || i >= 256) {
                    break;
                }
                bArr[i] = (byte) i2;
                i++;
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                th.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return null;
            } catch (Throwable th3) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th3;
            }
        }
        if (i <= 0) {
            fileInputStream.close();
            return null;
        }
        String str = new String(bArr, 0, i, "UTF-8");
        try {
            fileInputStream.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return str;
    }
}
