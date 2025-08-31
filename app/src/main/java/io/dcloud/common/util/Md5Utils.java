package io.dcloud.common.util;

import android.text.TextUtils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes3.dex */
public class Md5Utils {
    public static final String ALGORITHM = "MD5";
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] HEX_LOWER_CASE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String md5(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(str.getBytes("UTF-8"));
            return toHex(messageDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | Exception unused) {
            return str;
        }
    }

    public static String md5LowerCase(String str) throws NoSuchAlgorithmException {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(str.getBytes("UTF-8"));
            return toLowerCaseHex(messageDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | Exception unused) {
            return str;
        }
    }

    public static String md5LowerCase32Bit(String str) throws NoSuchAlgorithmException {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(bytes);
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr = new char[bArrDigest.length * 2];
            int i = 0;
            for (byte b : bArrDigest) {
                int i2 = i + 1;
                char[] cArr2 = HEX;
                cArr[i] = cArr2[(b >>> 4) & 15];
                i += 2;
                cArr[i2] = cArr2[b & 15];
            }
            return new String(cArr);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            char[] cArr = HEX;
            sb.append(cArr[(b & 240) >> 4]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }

    private static String toLowerCaseHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            char[] cArr = HEX_LOWER_CASE;
            sb.append(cArr[(b & 240) >> 4]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }

    public static boolean verifyFileMd5(String str, String str2) {
        String strMd5;
        return str.length() > 0 && (strMd5 = md5(new File(str))) != null && str2 != null && strMd5.compareToIgnoreCase(str2) == 0;
    }

    public static String md5(byte[] bArr) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(bArr);
            return toHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    public static String md5(File file) {
        return md5(file, ALGORITHM);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String md5(java.io.File r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]
            r1 = 0
            io.dcloud.application.DCLoudApplicationImpl r2 = io.dcloud.application.DCLoudApplicationImpl.self()     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            android.content.Context r2 = r2.getContext()     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            java.lang.String r3 = r4.getPath()     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            boolean r2 = io.dcloud.common.util.FileUtil.checkPrivatePath(r2, r3)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            if (r2 == 0) goto L1d
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            goto L29
        L1d:
            io.dcloud.application.DCLoudApplicationImpl r2 = io.dcloud.application.DCLoudApplicationImpl.self()     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            android.content.Context r2 = r2.getContext()     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
            java.io.InputStream r2 = io.dcloud.common.util.FileUtil.getFileInputStream(r2, r4)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L4d
        L29:
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r5)     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L5d
        L2d:
            int r5 = r2.read(r0)     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L5d
            if (r5 <= 0) goto L38
            r3 = 0
            r4.update(r0, r3, r5)     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L5d
            goto L2d
        L38:
            byte[] r4 = r4.digest()     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L5d
            java.lang.String r4 = toHex(r4)     // Catch: java.lang.Exception -> L49 java.lang.Throwable -> L5d
            r2.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r5 = move-exception
            r5.printStackTrace()
        L48:
            return r4
        L49:
            r4 = move-exception
            goto L4f
        L4b:
            r4 = move-exception
            goto L5f
        L4d:
            r4 = move-exception
            r2 = r1
        L4f:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L5d
            if (r2 == 0) goto L5c
            r2.close()     // Catch: java.io.IOException -> L58
            goto L5c
        L58:
            r4 = move-exception
            r4.printStackTrace()
        L5c:
            return r1
        L5d:
            r4 = move-exception
            r1 = r2
        L5f:
            if (r1 == 0) goto L69
            r1.close()     // Catch: java.io.IOException -> L65
            goto L69
        L65:
            r5 = move-exception
            r5.printStackTrace()
        L69:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.Md5Utils.md5(java.io.File, java.lang.String):java.lang.String");
    }
}
