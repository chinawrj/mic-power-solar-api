package com.nky.nkyble.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes.dex */
public class DatalogApUtil {
    public static Charset charset = Charset.forName(InternalZipConstants.AES_HASH_CHARSET);
    public static final String secretKey = "Growatt";

    public static byte[] getbyte16(byte[] bArr, int i) {
        int length = bArr.length;
        byte[] bArr2 = new byte[((length / i) + 1) * i];
        if (length % i == 0) {
            return bArr;
        }
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    public static byte[] int2Byte(int i) {
        return new byte[]{(byte) (i >> 8), (byte) i};
    }

    public static byte[] intToBytes(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static int byte2Int(byte[] bArr) {
        if (bArr.length <= 1) {
            return 0;
        }
        return (bArr[1] & 255) | (((bArr[0] & 255) << 8) & 65280);
    }

    public static byte[] getEnCode(byte[] bArr) throws Exception {
        int length = bArr.length;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, 8);
        byte[] bArrEnCode = enCode(Arrays.copyOfRange(bArr, 8, length));
        System.arraycopy(bArrCopyOfRange, 0, bArr, 0, bArrCopyOfRange.length);
        System.arraycopy(bArrEnCode, 0, bArr, bArrCopyOfRange.length, bArrEnCode.length);
        return bArr;
    }

    public static byte[] enCode(byte[] bArr) throws Exception {
        byte[] bytes = secretKey.getBytes();
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            if (i % bytes.length == 0) {
                i2 = 0;
            }
            bArr2[i] = (byte) (bytes[i2] ^ bArr[i]);
            i++;
            i2++;
        }
        return bArr2;
    }

    public static byte[] desCode(byte[] bArr) throws Exception {
        byte[] bArr2 = new byte[bArr.length];
        byte[] bytes = secretKey.getBytes();
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            if (i % bytes.length == 0) {
                i2 = 0;
            }
            bArr2[i] = (byte) (bytes[i2] ^ bArr[i]);
            i++;
            i2++;
        }
        return bArr2;
    }

    public static String desCode(String str) throws UnsupportedEncodingException {
        byte[] bytes = secretKey.getBytes();
        byte[] bytes2 = str.getBytes("ISO8859-1");
        int length = bytes2.length;
        byte[] bArr = new byte[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            if (i % bytes.length == 0) {
                i2 = 0;
            }
            bArr[i] = (byte) (bytes[i2] ^ bytes2[i]);
            i++;
            i2++;
        }
        try {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
            byteBufferAllocate.put(bArr);
            byteBufferAllocate.flip();
            return String.valueOf(charset.newDecoder().decode(byteBufferAllocate));
        } catch (CharacterCodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static byte[] msgDesCodeByAESCBC(byte[] bArr) throws Exception {
        return AESCBCUtils.AESDecryption(bArr);
    }
}
