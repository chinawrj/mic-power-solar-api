package io.dcloud.common.util;

import com.taobao.weex.el.parse.Operators;
import io.dcloud.p.d1;
import java.io.UnsupportedEncodingException;

/* loaded from: classes3.dex */
public final class Base64 {
    private static final char[] BASE64CHARS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final String CRLF = "\r\n";
    private static final char PAD = '=';

    public static String decode2String(String str) {
        try {
            return new String(decode2bytes(str), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        } catch (RuntimeException e2) {
            e2.printStackTrace();
            return "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0079 A[Catch: all -> 0x00a5, Exception -> 0x00af, TryCatch #8 {Exception -> 0x00af, all -> 0x00a5, blocks: (B:3:0x0006, B:4:0x000e, B:8:0x0014, B:24:0x0037, B:33:0x0051, B:40:0x0070, B:43:0x0094, B:42:0x0079, B:44:0x0098), top: B:71:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0094 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] decode2bytes(java.lang.String r11) throws java.io.IOException {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            byte[] r11 = r11.getBytes()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r2 = 0
            r3 = r2
            r4 = r3
            r5 = r4
        Le:
            int r6 = r11.length     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            if (r3 >= r6) goto L98
            r6 = 4
            if (r4 >= r6) goto L77
            r7 = r11[r3]     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            int r7 = decodeInt(r7)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r8 = -1
            if (r7 != r8) goto L1f
            goto L94
        L1f:
            r8 = 3
            r9 = 2
            r10 = -2
            if (r7 != r10) goto L31
            if (r4 == r9) goto L31
            if (r4 == r8) goto L31
            r0.close()     // Catch: java.io.IOException -> L2c
            goto L30
        L2c:
            r11 = move-exception
            r11.printStackTrace()
        L30:
            return r1
        L31:
            if (r7 != r10) goto L4b
            if (r4 != r9) goto L4b
            int r11 = r5 >> 4
            int r11 = eightbit(r11)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r0.write(r11)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            byte[] r11 = r0.toByteArray()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r0.close()     // Catch: java.io.IOException -> L46
            goto L4a
        L46:
            r0 = move-exception
            r0.printStackTrace()
        L4a:
            return r11
        L4b:
            if (r7 != r10) goto L6e
            if (r4 != r8) goto L6e
            int r11 = r5 >> 10
            int r11 = eightbit(r11)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r0.write(r11)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            int r11 = r5 >> 2
            int r11 = eightbit(r11)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r0.write(r11)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            byte[] r11 = r0.toByteArray()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r0.close()     // Catch: java.io.IOException -> L69
            goto L6d
        L69:
            r0 = move-exception
            r0.printStackTrace()
        L6d:
            return r11
        L6e:
            int r5 = r5 << 6
            int r7 = sixbit(r7)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r5 = r5 | r7
            int r4 = r4 + 1
        L77:
            if (r4 != r6) goto L94
            int r4 = r5 >> 16
            int r4 = eightbit(r4)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r0.write(r4)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            int r4 = r5 >> 8
            int r4 = eightbit(r4)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r0.write(r4)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            int r4 = eightbit(r5)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r0.write(r4)     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r4 = r2
            r5 = r4
        L94:
            int r3 = r3 + 1
            goto Le
        L98:
            byte[] r11 = r0.toByteArray()     // Catch: java.lang.Throwable -> La5 java.lang.Exception -> Laf
            r0.close()     // Catch: java.io.IOException -> La0
            goto La4
        La0:
            r0 = move-exception
            r0.printStackTrace()
        La4:
            return r11
        La5:
            r11 = move-exception
            r0.close()     // Catch: java.io.IOException -> Laa
            goto Lae
        Laa:
            r0 = move-exception
            r0.printStackTrace()
        Lae:
            throw r11
        Laf:
            r0.close()     // Catch: java.io.IOException -> Lb3
            goto Lb7
        Lb3:
            r11 = move-exception
            r11.printStackTrace()
        Lb7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.Base64.decode2bytes(java.lang.String):byte[]");
    }

    private static int decodeInt(int i) {
        if (i >= 65 && i <= 90) {
            return i - 65;
        }
        if (i >= 97 && i <= 122) {
            return i - 71;
        }
        if (i >= 48 && i <= 57) {
            return i + 4;
        }
        if (i == 43) {
            return 62;
        }
        if (i == 47) {
            return 63;
        }
        return i == 61 ? -2 : -1;
    }

    public static String decodeString(String str, boolean z, int i) {
        return d1.a(str, z, i);
    }

    private static int eightbit(int i) {
        return i & 255;
    }

    public static String encode(String str) {
        try {
            return encode(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static String encodeString(String str, boolean z, int i) {
        return d1.b(str, z, i);
    }

    private static int sixbit(int i) {
        return i & 63;
    }

    public static String encode(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i += 3) {
            if (i % 57 == 0 && i != 0) {
                stringBuffer.append(CRLF);
            }
            int i2 = i + 1;
            if (bArr.length <= i2) {
                int iEightbit = eightbit(bArr[i]) << 16;
                char[] cArr = BASE64CHARS;
                stringBuffer.append(cArr[sixbit(iEightbit >> 18)]);
                stringBuffer.append(cArr[sixbit(iEightbit >> 12)]);
                stringBuffer.append(Operators.EQUAL2);
            } else {
                int i3 = i + 2;
                if (bArr.length <= i3) {
                    int iEightbit2 = (eightbit(bArr[i]) << 16) | (eightbit(bArr[i2]) << 8);
                    char[] cArr2 = BASE64CHARS;
                    stringBuffer.append(cArr2[sixbit(iEightbit2 >> 18)]);
                    stringBuffer.append(cArr2[sixbit(iEightbit2 >> 12)]);
                    stringBuffer.append(cArr2[sixbit(iEightbit2 >> 6)]);
                    stringBuffer.append(PAD);
                } else {
                    int iEightbit3 = (eightbit(bArr[i]) << 16) | (eightbit(bArr[i2]) << 8) | eightbit(bArr[i3]);
                    char[] cArr3 = BASE64CHARS;
                    stringBuffer.append(cArr3[sixbit(iEightbit3 >> 18)]);
                    stringBuffer.append(cArr3[sixbit(iEightbit3 >> 12)]);
                    stringBuffer.append(cArr3[sixbit(iEightbit3 >> 6)]);
                    stringBuffer.append(cArr3[sixbit(iEightbit3)]);
                }
            }
        }
        return stringBuffer.toString();
    }
}
