package com.dcloud.zxing2.qrcode.decoder;

import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.common.BitSource;
import com.dcloud.zxing2.common.CharacterSetECI;
import com.dcloud.zxing2.common.DecoderResult;
import com.dcloud.zxing2.common.StringUtils;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.WXUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* loaded from: classes.dex */
final class DecodedBitStreamParser {
    private static final char[] ALPHANUMERIC_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', WXUtils.PERCENT, '*', '+', '-', Operators.DOT, '/', Operators.CONDITION_IF_MIDDLE};
    private static final int GB2312_SUBSET = 1;

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel, Map<DecodeHintType, ?> map) throws FormatException {
        Mode mode;
        Mode mode2;
        int i;
        String strDecodeByteSegment;
        BitSource bitSource = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(50);
        int i2 = 1;
        ArrayList arrayList = new ArrayList(1);
        String str = "";
        int i3 = -1;
        int bits = -1;
        boolean z = false;
        CharacterSetECI characterSetECIByValue = null;
        while (true) {
            try {
                Mode modeForBits = bitSource.available() < 4 ? Mode.TERMINATOR : Mode.forBits(bitSource.readBits(4));
                Mode mode3 = Mode.TERMINATOR;
                if (modeForBits == mode3) {
                    mode = mode3;
                    mode2 = modeForBits;
                    i = i3;
                    strDecodeByteSegment = str;
                } else {
                    if (modeForBits != Mode.FNC1_FIRST_POSITION && modeForBits != Mode.FNC1_SECOND_POSITION) {
                        if (modeForBits != Mode.STRUCTURED_APPEND) {
                            if (modeForBits == Mode.ECI) {
                                characterSetECIByValue = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(bitSource));
                                if (characterSetECIByValue == null) {
                                    throw FormatException.getFormatInstance();
                                }
                            } else if (modeForBits == Mode.HANZI) {
                                int bits2 = bitSource.readBits(4);
                                int bits3 = bitSource.readBits(modeForBits.getCharacterCountBits(version));
                                if (bits2 == i2) {
                                    decodeHanziSegment(bitSource, sb, bits3);
                                }
                            } else {
                                int bits4 = bitSource.readBits(modeForBits.getCharacterCountBits(version));
                                if (modeForBits == Mode.NUMERIC) {
                                    decodeNumericSegment(bitSource, sb, bits4);
                                } else if (modeForBits == Mode.ALPHANUMERIC) {
                                    decodeAlphanumericSegment(bitSource, sb, bits4, z);
                                } else if (modeForBits == Mode.BYTE) {
                                    mode = mode3;
                                    mode2 = modeForBits;
                                    i = i3;
                                    strDecodeByteSegment = decodeByteSegment(bitSource, sb, bits4, characterSetECIByValue, arrayList, map);
                                } else {
                                    mode = mode3;
                                    mode2 = modeForBits;
                                    if (mode2 != Mode.KANJI) {
                                        throw FormatException.getFormatInstance();
                                    }
                                    decodeKanjiSegment(bitSource, sb, bits4);
                                    i = i3;
                                    strDecodeByteSegment = str;
                                }
                            }
                            mode = mode3;
                            mode2 = modeForBits;
                            i = i3;
                            strDecodeByteSegment = str;
                        } else {
                            if (bitSource.available() < 16) {
                                throw FormatException.getFormatInstance();
                            }
                            int bits5 = bitSource.readBits(8);
                            bits = bitSource.readBits(8);
                            strDecodeByteSegment = str;
                            mode = mode3;
                            mode2 = modeForBits;
                            i = bits5;
                        }
                    }
                    mode = mode3;
                    mode2 = modeForBits;
                    i = i3;
                    z = true;
                    strDecodeByteSegment = str;
                }
                if (mode2 == mode) {
                    DecoderResult decoderResult = new DecoderResult(bArr, sb.toString(), arrayList.isEmpty() ? null : arrayList, errorCorrectionLevel == null ? null : errorCorrectionLevel.toString(), i, bits);
                    decoderResult.textCharset = strDecodeByteSegment;
                    return decoderResult;
                }
                str = strDecodeByteSegment;
                i2 = 1;
                i3 = i;
            } catch (IllegalArgumentException unused) {
                throw FormatException.getFormatInstance();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void decodeAlphanumericSegment(com.dcloud.zxing2.common.BitSource r3, java.lang.StringBuilder r4, int r5, boolean r6) throws com.dcloud.zxing2.FormatException {
        /*
            int r0 = r4.length()
        L4:
            r1 = 1
            if (r5 <= r1) goto L2d
            int r1 = r3.available()
            r2 = 11
            if (r1 < r2) goto L28
            int r1 = r3.readBits(r2)
            int r2 = r1 / 45
            char r2 = toAlphaNumericChar(r2)
            r4.append(r2)
            int r1 = r1 % 45
            char r1 = toAlphaNumericChar(r1)
            r4.append(r1)
            int r5 = r5 + (-2)
            goto L4
        L28:
            com.dcloud.zxing2.FormatException r3 = com.dcloud.zxing2.FormatException.getFormatInstance()
            throw r3
        L2d:
            if (r5 != r1) goto L47
            int r5 = r3.available()
            r2 = 6
            if (r5 < r2) goto L42
            int r3 = r3.readBits(r2)
            char r3 = toAlphaNumericChar(r3)
            r4.append(r3)
            goto L47
        L42:
            com.dcloud.zxing2.FormatException r3 = com.dcloud.zxing2.FormatException.getFormatInstance()
            throw r3
        L47:
            if (r6 == 0) goto L72
        L49:
            int r3 = r4.length()
            if (r0 >= r3) goto L72
            char r3 = r4.charAt(r0)
            r5 = 37
            if (r3 != r5) goto L6f
            int r3 = r4.length()
            int r3 = r3 - r1
            if (r0 >= r3) goto L6a
            int r3 = r0 + 1
            char r6 = r4.charAt(r3)
            if (r6 != r5) goto L6a
            r4.deleteCharAt(r3)
            goto L6f
        L6a:
            r3 = 29
            r4.setCharAt(r0, r3)
        L6f:
            int r0 = r0 + 1
            goto L49
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dcloud.zxing2.qrcode.decoder.DecodedBitStreamParser.decodeAlphanumericSegment(com.dcloud.zxing2.common.BitSource, java.lang.StringBuilder, int, boolean):void");
    }

    private static String decodeByteSegment(BitSource bitSource, StringBuilder sb, int i, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        if (i * 8 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) bitSource.readBits(8);
        }
        String strGuessEncoding = characterSetECI == null ? StringUtils.guessEncoding(bArr, map) : characterSetECI.name();
        try {
            sb.append(new String(bArr, strGuessEncoding));
            collection.add(bArr);
            return strGuessEncoding;
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeHanziSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int bits = bitSource.readBits(13);
            int i3 = (bits % 96) | ((bits / 96) << 8);
            int i4 = i3 + (i3 < 959 ? 41377 : 42657);
            bArr[i2] = (byte) ((i4 >> 8) & 255);
            bArr[i2 + 1] = (byte) (i4 & 255);
            i2 += 2;
            i--;
        }
        try {
            sb.append(new String(bArr, StringUtils.GB2312));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeKanjiSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        if (i * 13 > bitSource.available()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int bits = bitSource.readBits(13);
            int i3 = (bits % 192) | ((bits / 192) << 8);
            int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        try {
            sb.append(new String(bArr, "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void decodeNumericSegment(BitSource bitSource, StringBuilder sb, int i) throws FormatException {
        while (i >= 3) {
            if (bitSource.available() < 10) {
                throw FormatException.getFormatInstance();
            }
            int bits = bitSource.readBits(10);
            if (bits >= 1000) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits / 100));
            sb.append(toAlphaNumericChar((bits / 10) % 10));
            sb.append(toAlphaNumericChar(bits % 10));
            i -= 3;
        }
        if (i == 2) {
            if (bitSource.available() < 7) {
                throw FormatException.getFormatInstance();
            }
            int bits2 = bitSource.readBits(7);
            if (bits2 >= 100) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits2 / 10));
            sb.append(toAlphaNumericChar(bits2 % 10));
            return;
        }
        if (i == 1) {
            if (bitSource.available() < 4) {
                throw FormatException.getFormatInstance();
            }
            int bits3 = bitSource.readBits(4);
            if (bits3 >= 10) {
                throw FormatException.getFormatInstance();
            }
            sb.append(toAlphaNumericChar(bits3));
        }
    }

    private static int parseECIValue(BitSource bitSource) throws FormatException {
        int bits = bitSource.readBits(8);
        if ((bits & 128) == 0) {
            return bits & WorkQueueKt.MASK;
        }
        if ((bits & 192) == 128) {
            return bitSource.readBits(8) | ((bits & 63) << 8);
        }
        if ((bits & 224) == 192) {
            return bitSource.readBits(16) | ((bits & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }

    private static char toAlphaNumericChar(int i) throws FormatException {
        char[] cArr = ALPHANUMERIC_CHARS;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw FormatException.getFormatInstance();
    }
}
