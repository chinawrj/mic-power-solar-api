package io.dcloud.p;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

/* loaded from: classes3.dex */
public abstract class s4 {
    private static String a = "";
    private static String b;
    private static String c;

    private static boolean a(String str) {
        return TextUtils.isEmpty(str) || str.contains("Unknown") || str.contains("00000000");
    }

    private static StringBuilder b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                inputStream.close();
                return sb;
            }
            sb.append(line);
            sb.append("\n");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:86:0x0115 A[PHI: r1
  0x0115: PHI (r1v3 java.lang.String) = (r1v1 java.lang.String), (r1v4 java.lang.String) binds: [B:84:0x0112, B:76:0x0103] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r11, boolean r12, boolean r13) throws java.lang.NoSuchMethodException, java.lang.SecurityException {
        /*
            Method dump skipped, instructions count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.s4.a(android.content.Context, boolean, boolean):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00b8 A[Catch: Exception -> 0x00ed, all -> 0x00fe, TryCatch #1 {Exception -> 0x00ed, blocks: (B:24:0x00b1, B:28:0x00c1, B:30:0x00c7, B:32:0x00d1, B:33:0x00db, B:27:0x00b8), top: B:46:0x00b1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r11) {
        /*
            java.lang.String r0 = ".imei.txt"
            r1 = 0
            boolean r2 = io.dcloud.p.k1.a(r11)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r3.<init>()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.io.File r4 = r11.getFilesDir()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r3.append(r4)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.lang.String r4 = java.io.File.separator     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r3.append(r4)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r3.append(r0)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.io.File r5 = new java.io.File     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r5.<init>(r3)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            boolean r6 = r5.exists()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.lang.String r7 = ".DC4278477faeb9.txt"
            if (r6 != 0) goto L47
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r3.<init>()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.io.File r5 = r11.getFilesDir()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r3.append(r5)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r3.append(r4)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r3.append(r7)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.io.File r5 = new java.io.File     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r5.<init>(r3)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
        L47:
            java.lang.String r6 = "mounted"
            java.lang.String r8 = android.os.Environment.getExternalStorageState()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            boolean r6 = r6.equalsIgnoreCase(r8)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            if (r6 == 0) goto L92
            if (r2 != 0) goto L92
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r6.<init>()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.io.File r8 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r6.append(r8)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r6.append(r4)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r6.append(r0)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.lang.String r0 = r6.toString()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r6.<init>(r0)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            boolean r8 = r6.exists()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            if (r8 != 0) goto L94
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r0.<init>()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.io.File r6 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r0.append(r6)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r0.append(r4)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r0.append(r7)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r6.<init>(r0)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            goto L94
        L92:
            r0 = r1
            r6 = r0
        L94:
            boolean r4 = r5.isDirectory()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            if (r4 == 0) goto L9d
            r5.delete()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
        L9d:
            if (r6 == 0) goto Lf8
            boolean r4 = r6.exists()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            if (r4 == 0) goto Lf8
            long r7 = r6.length()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            r9 = 0
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 <= 0) goto Lf8
            if (r2 == 0) goto Lb8
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            r4 = 29
            if (r2 < r4) goto Lb8
            goto Lc1
        Lb8:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            r2.<init>(r6)     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            java.lang.String r1 = a(r2)     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
        Lc1:
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            if (r2 != 0) goto Led
            java.io.File r2 = r5.getParentFile()     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            boolean r2 = r2.exists()     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            if (r2 != 0) goto Ldb
            java.io.File r2 = r5.getParentFile()     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            r2.mkdirs()     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            r5.createNewFile()     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
        Ldb:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            r2.<init>(r5)     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            byte[] r4 = r1.getBytes()     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            r2.write(r4)     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            r2.flush()     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
            r2.close()     // Catch: java.lang.Exception -> Led java.lang.Throwable -> Lfe
        Led:
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            if (r2 == 0) goto L104
            java.lang.String r11 = a(r5, r6, r3, r0, r11)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
            goto Lfc
        Lf8:
            java.lang.String r11 = a(r5, r6, r3, r0, r11)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L100
        Lfc:
            r1 = r11
            goto L104
        Lfe:
            r11 = move-exception
            throw r11
        L100:
            r11 = move-exception
            r11.printStackTrace()
        L104:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.p.s4.a(android.content.Context):java.lang.String");
    }

    private static String a(File file, File file2, String str, String str2, Context context) throws IOException {
        String strA;
        if (file.exists() && file.length() > 0) {
            try {
                strA = a(new FileInputStream(file));
                if (file2 == null) {
                    return strA;
                }
                try {
                    if (k1.a(context)) {
                        return strA;
                    }
                    if (!file2.getParentFile().exists()) {
                        file2.getParentFile().mkdirs();
                        file2.createNewFile();
                    }
                    v0.a(str, str2);
                    return strA;
                } catch (Exception unused) {
                    return strA == null ? a(context, file, file2, ".DC4278477faeb9.txt") : strA;
                }
            } catch (Exception unused2) {
                strA = null;
            }
        } else {
            return a(context, file, file2, ".DC4278477faeb9.txt");
        }
    }

    private static String a(Context context, File file, File file2, String str) throws IOException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        String strReplace = UUID.randomUUID().toString().replaceAll(Operators.SUB, "").replace("\n", "");
        byte[] bytes = strReplace.getBytes();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (!k1.a(context)) {
                v0.a(file.getPath(), file2.getPath());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return strReplace;
    }

    public static String a(InputStream inputStream) {
        if (inputStream == null) {
            return "";
        }
        return b(inputStream).toString();
    }
}
