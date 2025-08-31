package io.dcloud.p;

import android.util.Log;
import io.dcloud.common.adapter.util.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;

/* loaded from: classes3.dex */
public abstract class y2 extends Logger {
    private static String a;
    private static File b;
    private static Boolean c = Boolean.TRUE;

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0033 -> B:31:0x0043). Please report as a decompilation issue!!! */
    private static void WriteLogToSDcard(String str, String str2, String str3) throws Throwable {
        FileOutputStream fileOutputStream;
        String strGenerateLog = Logger.generateLog(str, str2, str3);
        if (b == null || strGenerateLog == null) {
            return;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                fileOutputStream = new FileOutputStream(b, true);
                try {
                    fileOutputStream.write(strGenerateLog.getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream2 = fileOutputStream;
                    e.printStackTrace();
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = fileOutputStream2;
        }
    }

    public static void a(String str) throws IOException, ParseException {
        if (c.booleanValue()) {
            a = str;
            storeLogToSDcard();
            c = Boolean.FALSE;
        }
    }

    public static void d(String str, String str2) throws Throwable {
        Log.d(str, str2);
        WriteLogToSDcard(Logger.D, str, str2);
    }

    public static void e(String str, String str2) throws Throwable {
        Log.e(str, str2);
        WriteLogToSDcard(Logger.E, str, str2);
    }

    public static void i(String str, String str2) throws Throwable {
        Log.i(str, str2);
        WriteLogToSDcard(Logger.I, str, str2);
    }

    public static void storeLogToSDcard() throws IOException, ParseException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(a).append(File.separatorChar).append(Logger.generateTimeStamp(Boolean.FALSE)).append(".log");
        File file = new File(a);
        b = new File(stringBuffer.toString());
        if (file.exists()) {
            Logger.deleteOldLog(file);
        } else {
            file.mkdirs();
        }
        if (b.exists()) {
            return;
        }
        try {
            b.createNewFile();
        } catch (IOException e) {
            b = null;
            e.printStackTrace();
        }
    }

    public static void a(String str, String str2) throws Throwable {
        Log.i(str, str2);
        WriteLogToSDcard(Logger.W, str, str2);
    }
}
