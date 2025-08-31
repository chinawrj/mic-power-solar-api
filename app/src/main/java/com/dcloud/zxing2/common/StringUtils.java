package com.dcloud.zxing2.common;

import android.text.TextUtils;
import java.nio.charset.Charset;
import org.mozilla.universalchardet.UniversalDetector;

/* loaded from: classes.dex */
public final class StringUtils {
    private static final boolean ASSUME_SHIFT_JIS;
    private static final String EUC_JP = "EUC_JP";
    public static final String GB2312 = "GB2312";
    private static final String ISO88591 = "ISO8859_1";
    private static final String PLATFORM_DEFAULT_ENCODING;
    public static final String SHIFT_JIS = "SJIS";
    public static final String UTF8 = "UTF-8";

    static {
        String strName = Charset.defaultCharset().name();
        PLATFORM_DEFAULT_ENCODING = strName;
        ASSUME_SHIFT_JIS = SHIFT_JIS.equalsIgnoreCase(strName) || EUC_JP.equalsIgnoreCase(strName);
    }

    private StringUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:98:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String guessEncoding(byte[] r21, java.util.Map<com.dcloud.zxing2.DecodeHintType, ?> r22) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dcloud.zxing2.common.StringUtils.guessEncoding(byte[], java.util.Map):java.lang.String");
    }

    public static String guessEncodingSmart(byte[] bArr) {
        UniversalDetector universalDetector = new UniversalDetector();
        universalDetector.handleData(bArr);
        universalDetector.dataEnd();
        String detectedCharset = universalDetector.getDetectedCharset();
        universalDetector.reset();
        return !TextUtils.isEmpty(detectedCharset) ? detectedCharset : GB2312;
    }
}
