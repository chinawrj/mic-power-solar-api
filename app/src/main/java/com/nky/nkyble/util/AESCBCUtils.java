package com.nky.nkyble.util;

import com.taobao.weex.performance.WXInstanceApm;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class AESCBCUtils {
    private static String Algorithm = "AES";
    private static String AlgorithmProvider = "AES/CBC/NoPadding";
    private static String SECRET_KEY = "growatt_aes16key";
    private static String iv = "growatt_aes16Ivs";

    public static byte[] AESEncryption(byte[] bArr) throws Exception {
        return encrypt(bArr, SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] AESDecryption(byte[] bArr) throws Exception {
        return decrypt(bArr, SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public static String byteToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(WXInstanceApm.VALUE_ERROR_CODE_DEFAULT);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, Algorithm);
        IvParameterSpec iv2 = getIv();
        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
        cipher.init(1, secretKeySpec, iv2);
        return cipher.doFinal(bArr);
    }

    public static IvParameterSpec getIv() throws Exception {
        return new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, Algorithm);
        IvParameterSpec iv2 = getIv();
        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
        cipher.init(2, secretKeySpec, iv2);
        return cipher.doFinal(bArr);
    }

    public static byte[] hexStringToBytes(String str) {
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (charToByte(charArray[i2 + 1]) | (charToByte(charArray[i2]) << 4));
        }
        return bArr;
    }

    private static byte charToByte(char c) {
        return (byte) iv.indexOf(c);
    }
}
