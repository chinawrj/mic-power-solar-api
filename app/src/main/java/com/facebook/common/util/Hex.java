package com.facebook.common.util;

import com.taobao.weex.el.parse.Operators;

/* loaded from: classes.dex */
public class Hex {
    private static final byte[] DIGITS;
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] FIRST_CHAR = new char[256];
    private static final char[] SECOND_CHAR = new char[256];

    static {
        for (int i = 0; i < 256; i++) {
            char[] cArr = FIRST_CHAR;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(i >> 4) & 15];
            SECOND_CHAR[i] = cArr2[i & 15];
        }
        DIGITS = new byte[103];
        for (int i2 = 0; i2 <= 70; i2++) {
            DIGITS[i2] = -1;
        }
        for (byte b = 0; b < 10; b = (byte) (b + 1)) {
            DIGITS[b + 48] = b;
        }
        for (byte b2 = 0; b2 < 6; b2 = (byte) (b2 + 1)) {
            byte[] bArr = DIGITS;
            byte b3 = (byte) (b2 + 10);
            bArr[b2 + 65] = b3;
            bArr[b2 + 97] = b3;
        }
    }

    public static String byte2Hex(int value) {
        if (value > 255 || value < 0) {
            throw new IllegalArgumentException("The int converting to hex should be in range 0~255");
        }
        return String.valueOf(FIRST_CHAR[value]) + String.valueOf(SECOND_CHAR[value]);
    }

    public static String encodeHex(byte[] array, boolean zeroTerminated) {
        int i;
        char[] cArr = new char[array.length * 2];
        int i2 = 0;
        for (int i3 = 0; i3 < array.length && ((i = array[i3] & 255) != 0 || !zeroTerminated); i3++) {
            int i4 = i2 + 1;
            cArr[i2] = FIRST_CHAR[i];
            i2 += 2;
            cArr[i4] = SECOND_CHAR[i];
        }
        return new String(cArr, 0, i2);
    }

    public static byte[] decodeHex(String hexString) {
        byte[] bArr;
        byte b;
        byte b2;
        int length = hexString.length();
        if ((length & 1) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        }
        byte[] bArr2 = new byte[length >> 1];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            char cCharAt = hexString.charAt(i);
            if (cCharAt <= 'f' && (b = (bArr = DIGITS)[cCharAt]) != -1) {
                i += 2;
                char cCharAt2 = hexString.charAt(i3);
                if (cCharAt2 <= 'f' && (b2 = bArr[cCharAt2]) != -1) {
                    bArr2[i2] = (byte) (b2 | (b << 4));
                    i2++;
                }
            }
            throw new IllegalArgumentException("Invalid hexadecimal digit: " + hexString);
        }
        return bArr2;
    }

    public static byte[] hexStringToByteArray(String s) {
        return decodeHex(s.replaceAll(Operators.SPACE_STR, ""));
    }
}
