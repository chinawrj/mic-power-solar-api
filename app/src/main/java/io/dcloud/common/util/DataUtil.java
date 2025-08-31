package io.dcloud.common.util;

import android.text.TextUtils;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.adapter.util.PlatformUtil;
import io.dcloud.p.d1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.Character;
import java.util.Locale;

/* loaded from: classes3.dex */
public class DataUtil {
    public static String GBK2Unicode(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (isNeedConvert(cCharAt)) {
                stringBuffer.append("\\u" + Integer.toHexString(cCharAt));
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        return stringBuffer.toString();
    }

    public static String Unicode2GBK(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (i < length - 1) {
                int i2 = i + 2;
                if ("\\u".equals(str.substring(i, i2))) {
                    i += 6;
                    stringBuffer.append((char) Integer.parseInt(str.substring(i2, i), 16));
                }
            }
            stringBuffer.append(str.charAt(i));
            i++;
        }
        return stringBuffer.toString();
    }

    public static void datToJsString(final String str, final ICallBack iCallBack) {
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.common.util.DataUtil.1
            @Override // java.lang.Runnable
            public void run() {
                String strDatToJsStringSync = DataUtil.datToJsStringSync(str);
                if (TextUtils.isEmpty(strDatToJsStringSync)) {
                    return;
                }
                iCallBack.onCallBack(0, strDatToJsStringSync);
            }
        }, true);
    }

    public static String datToJsStringSync(String str) {
        InputStream fileInputStream;
        File file = new File(DeviceInfo.sBaseFsRootPath + File.separator + str);
        boolean z = true;
        if (file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                z = false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                fileInputStream = null;
            }
        } else {
            fileInputStream = PlatformUtil.getResInputStream(str);
        }
        boolean z2 = str.equals(BaseInfo.sUniNViewServiceJsPath) ? false : z;
        if (fileInputStream != null) {
            try {
                if (!z2) {
                    return new String(IOUtil.getBytes(fileInputStream));
                }
                if (!TextUtils.isEmpty(d1.c())) {
                    return AESUtil.decrypt(d1.c(), d1.b(), IOUtil.getBytes(fileInputStream));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static boolean isNeedConvert(char c) {
        return (c & 255) != c;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0064 A[PHI: r5
  0x0064: PHI (r5v20 char) = (r5v16 char), (r5v17 char), (r5v18 char), (r5v19 char) binds: [B:26:0x0062, B:29:0x0068, B:32:0x006d, B:35:0x0072] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String unicodeToUtf8(java.lang.String r7) {
        /*
            if (r7 != 0) goto L5
            java.lang.String r7 = ""
            return r7
        L5:
            int r0 = r7.length()
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>(r0)
            r2 = 0
            r3 = r2
        L10:
            if (r3 >= r0) goto L7e
            int r4 = r3 + 1
            char r5 = r7.charAt(r3)
            r6 = 92
            if (r5 != r6) goto L79
            int r3 = r3 + 2
            char r4 = r7.charAt(r4)
            r5 = 117(0x75, float:1.64E-43)
            if (r4 != r5) goto L60
            r4 = r2
            r5 = r4
        L28:
            r6 = 4
            if (r4 >= r6) goto L5b
            int r6 = r3 + 1
            char r3 = r7.charAt(r3)
            switch(r3) {
                case 48: goto L42;
                case 49: goto L42;
                case 50: goto L42;
                case 51: goto L42;
                case 52: goto L42;
                case 53: goto L42;
                case 54: goto L42;
                case 55: goto L42;
                case 56: goto L42;
                case 57: goto L42;
                default: goto L34;
            }
        L34:
            switch(r3) {
                case 65: goto L48;
                case 66: goto L48;
                case 67: goto L48;
                case 68: goto L48;
                case 69: goto L48;
                case 70: goto L48;
                default: goto L37;
            }
        L37:
            switch(r3) {
                case 97: goto L50;
                case 98: goto L50;
                case 99: goto L50;
                case 100: goto L50;
                case 101: goto L50;
                case 102: goto L50;
                default: goto L3a;
            }
        L3a:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Malformed   \\uxxxx   encoding."
            r7.<init>(r0)
            throw r7
        L42:
            int r5 = r5 << 4
            int r5 = r5 + r3
            int r5 = r5 + (-48)
            goto L57
        L48:
            int r5 = r5 << 4
            int r5 = r5 + 10
            int r5 = r5 + r3
            int r5 = r5 + (-65)
            goto L57
        L50:
            int r5 = r5 << 4
            int r5 = r5 + 10
            int r5 = r5 + r3
            int r5 = r5 + (-97)
        L57:
            int r4 = r4 + 1
            r3 = r6
            goto L28
        L5b:
            char r4 = (char) r5
            r1.append(r4)
            goto L10
        L60:
            r5 = 116(0x74, float:1.63E-43)
            if (r4 != r5) goto L66
        L64:
            r4 = r5
            goto L75
        L66:
            r5 = 114(0x72, float:1.6E-43)
            if (r4 != r5) goto L6b
            goto L64
        L6b:
            r5 = 110(0x6e, float:1.54E-43)
            if (r4 != r5) goto L70
            goto L64
        L70:
            r5 = 102(0x66, float:1.43E-43)
            if (r4 != r5) goto L75
            goto L64
        L75:
            r1.append(r4)
            goto L10
        L79:
            r1.append(r5)
            r3 = r4
            goto L10
        L7e:
            java.lang.String r7 = r1.toString()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.DataUtil.unicodeToUtf8(java.lang.String):java.lang.String");
    }

    public static String utf8ToUnicode(String str) {
        char[] charArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            Character.UnicodeBlock unicodeBlockOf = Character.UnicodeBlock.of(charArray[i]);
            if (unicodeBlockOf == Character.UnicodeBlock.BASIC_LATIN || unicodeBlockOf == Character.UnicodeBlock.LATIN_1_SUPPLEMENT) {
                stringBuffer.append(charArray[i]);
            } else if (unicodeBlockOf == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                stringBuffer.append((char) (charArray[i] - 65248));
            } else {
                stringBuffer.append(("\\u" + Integer.toHexString(charArray[i])).toLowerCase(Locale.ENGLISH));
            }
        }
        return stringBuffer.toString();
    }

    public String gbk2utf8(String str) {
        return unicodeToUtf8(GBK2Unicode(str));
    }

    public String utf82gbk(String str) {
        return Unicode2GBK(utf8ToUnicode(str));
    }
}
