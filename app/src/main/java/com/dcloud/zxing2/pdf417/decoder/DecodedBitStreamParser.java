package com.dcloud.zxing2.pdf417.decoder;

import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.pdf417.PDF417ResultMetadata;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.taobao.weex.utils.WXUtils;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import kotlin.text.Typography;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes.dex */
final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = {';', Typography.less, Typography.greater, TemplateDom.SEPARATOR, Operators.ARRAY_START, '\\', Operators.ARRAY_END, '_', '`', '~', '!', '\r', '\t', Operators.ARRAY_SEPRATOR, Operators.CONDITION_IF_MIDDLE, '\n', '-', Operators.DOT, '$', '/', '\"', '|', '*', Operators.BRACKET_START, Operators.BRACKET_END, Operators.CONDITION_IF, Operators.BLOCK_START, Operators.BLOCK_END, Operators.SINGLE_QUOTE};
    private static final char[] MIXED_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', Typography.amp, '\r', '\t', Operators.ARRAY_SEPRATOR, Operators.CONDITION_IF_MIDDLE, '#', '-', Operators.DOT, '$', '/', '+', WXUtils.PERCENT, '*', '=', '^'};
    private static final Charset DEFAULT_ENCODING = Charset.forName(InternalZipConstants.AES_HASH_CHARSET);

    /* renamed from: com.dcloud.zxing2.pdf417.decoder.DecodedBitStreamParser$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$dcloud$zxing2$pdf417$decoder$DecodedBitStreamParser$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$dcloud$zxing2$pdf417$decoder$DecodedBitStreamParser$Mode = iArr;
            try {
                iArr[Mode.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$dcloud$zxing2$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$dcloud$zxing2$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$dcloud$zxing2$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$dcloud$zxing2$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$dcloud$zxing2$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger bigIntegerValueOf = BigInteger.valueOf(900L);
        bigIntegerArr[1] = bigIntegerValueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(bigIntegerValueOf);
            i++;
        }
    }

    private DecodedBitStreamParser() {
    }

    private static int byteCompaction(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        int i3;
        int i4;
        int i5;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i6 = MACRO_PDF417_TERMINATOR;
        int i7 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
        int i8 = 928;
        long j = 900;
        if (i == BYTE_COMPACTION_MODE_LATCH) {
            int[] iArr2 = new int[6];
            i3 = i2 + 1;
            int i9 = iArr[i2];
            long j2 = 0;
            boolean z = false;
            int i10 = 0;
            while (true) {
                i4 = iArr[0];
                if (i3 >= i4 || z) {
                    break;
                }
                int i11 = i10 + 1;
                iArr2[i10] = i9;
                j2 = (j2 * j) + i9;
                int i12 = i3 + 1;
                i9 = iArr[i3];
                if (i9 == TEXT_COMPACTION_MODE_LATCH || i9 == BYTE_COMPACTION_MODE_LATCH || i9 == NUMERIC_COMPACTION_MODE_LATCH || i9 == BYTE_COMPACTION_MODE_LATCH_6 || i9 == 928 || i9 == i7 || i9 == i6) {
                    i10 = i11;
                    i6 = MACRO_PDF417_TERMINATOR;
                    i7 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                    j = 900;
                    z = true;
                } else if (i11 % 5 != 0 || i11 <= 0) {
                    i3 = i12;
                    i10 = i11;
                    i6 = MACRO_PDF417_TERMINATOR;
                    i7 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                    j = 900;
                } else {
                    int i13 = 0;
                    while (i13 < 6) {
                        byteArrayOutputStream.write((byte) (j2 >> ((5 - i13) * 8)));
                        i13++;
                        i6 = MACRO_PDF417_TERMINATOR;
                        i7 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                    }
                    i3 = i12;
                    i10 = 0;
                    j = 900;
                    j2 = 0;
                }
            }
            if (i3 != i4 || i9 >= TEXT_COMPACTION_MODE_LATCH) {
                i5 = i10;
            } else {
                i5 = i10 + 1;
                iArr2[i10] = i9;
            }
            for (int i14 = 0; i14 < i5; i14++) {
                byteArrayOutputStream.write((byte) iArr2[i14]);
            }
        } else if (i == BYTE_COMPACTION_MODE_LATCH_6) {
            int i15 = i2;
            boolean z2 = false;
            int i16 = 0;
            loop3: while (true) {
                long j3 = 0;
                while (i15 < iArr[0] && !z2) {
                    int i17 = i15 + 1;
                    int i18 = iArr[i15];
                    if (i18 < TEXT_COMPACTION_MODE_LATCH) {
                        i16++;
                        j3 = (j3 * 900) + i18;
                        i15 = i17;
                    } else {
                        if (i18 != TEXT_COMPACTION_MODE_LATCH && i18 != BYTE_COMPACTION_MODE_LATCH && i18 != NUMERIC_COMPACTION_MODE_LATCH && i18 != BYTE_COMPACTION_MODE_LATCH_6 && i18 != i8) {
                            if (i18 != BEGIN_MACRO_PDF417_OPTIONAL_FIELD && i18 != MACRO_PDF417_TERMINATOR) {
                                i15 = i17;
                            }
                        }
                        z2 = true;
                    }
                    if (i16 % 5 != 0 || i16 <= 0) {
                        i8 = 928;
                    } else {
                        for (int i19 = 0; i19 < 6; i19++) {
                            byteArrayOutputStream.write((byte) (j3 >> ((5 - i19) * 8)));
                        }
                        i16 = 0;
                        i8 = 928;
                    }
                }
                break loop3;
            }
            i3 = i15;
        } else {
            i3 = i2;
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.dcloud.zxing2.common.DecoderResult decode(int[] r7, java.lang.String r8) throws com.dcloud.zxing2.FormatException {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r7.length
            r2 = 2
            int r1 = r1 * r2
            r0.<init>(r1)
            java.lang.String r1 = "UTF-8"
            java.nio.charset.Charset r1 = java.nio.charset.Charset.forName(r1)
            r3 = 1
            r3 = r7[r3]
            com.dcloud.zxing2.pdf417.PDF417ResultMetadata r4 = new com.dcloud.zxing2.pdf417.PDF417ResultMetadata
            r4.<init>()
        L16:
            r5 = 0
            r5 = r7[r5]
            if (r2 >= r5) goto L78
            r5 = 913(0x391, float:1.28E-42)
            if (r3 == r5) goto L5f
            switch(r3) {
                case 900: goto L31;
                case 901: goto L55;
                case 902: goto L2c;
                default: goto L22;
            }
        L22:
            switch(r3) {
                case 922: goto L5a;
                case 923: goto L5a;
                case 924: goto L55;
                case 925: goto L52;
                case 926: goto L4f;
                case 927: goto L3b;
                case 928: goto L36;
                default: goto L25;
            }
        L25:
            int r2 = r2 + (-1)
            int r2 = textCompaction(r7, r2, r0)
            goto L68
        L2c:
            int r2 = numericCompaction(r7, r2, r0)
            goto L68
        L31:
            int r2 = textCompaction(r7, r2, r0)
            goto L68
        L36:
            int r2 = decodeMacroBlock(r7, r2, r4)
            goto L68
        L3b:
            int r1 = r2 + 1
            r2 = r7[r2]
            com.dcloud.zxing2.common.CharacterSetECI r2 = com.dcloud.zxing2.common.CharacterSetECI.getCharacterSetECIByValue(r2)
            java.lang.String r2 = r2.name()
            java.nio.charset.Charset r2 = java.nio.charset.Charset.forName(r2)
            r6 = r2
            r2 = r1
            r1 = r6
            goto L68
        L4f:
            int r2 = r2 + 2
            goto L68
        L52:
            int r2 = r2 + 1
            goto L68
        L55:
            int r2 = byteCompaction(r3, r7, r1, r2, r0)
            goto L68
        L5a:
            com.dcloud.zxing2.FormatException r7 = com.dcloud.zxing2.FormatException.getFormatInstance()
            throw r7
        L5f:
            int r3 = r2 + 1
            r2 = r7[r2]
            char r2 = (char) r2
            r0.append(r2)
            r2 = r3
        L68:
            int r3 = r7.length
            if (r2 >= r3) goto L73
            int r3 = r2 + 1
            r2 = r7[r2]
            r6 = r3
            r3 = r2
            r2 = r6
            goto L16
        L73:
            com.dcloud.zxing2.FormatException r7 = com.dcloud.zxing2.FormatException.getFormatInstance()
            throw r7
        L78:
            int r7 = r0.length()
            if (r7 == 0) goto L8c
            com.dcloud.zxing2.common.DecoderResult r7 = new com.dcloud.zxing2.common.DecoderResult
            java.lang.String r0 = r0.toString()
            r1 = 0
            r7.<init>(r1, r0, r1, r8)
            r7.setOther(r4)
            return r7
        L8c:
            com.dcloud.zxing2.FormatException r7 = com.dcloud.zxing2.FormatException.getFormatInstance()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dcloud.zxing2.pdf417.decoder.DecodedBitStreamParser.decode(int[], java.lang.String):com.dcloud.zxing2.common.DecoderResult");
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigIntegerAdd = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigIntegerAdd = bigIntegerAdd.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String string = bigIntegerAdd.toString();
        if (string.charAt(0) == '1') {
            return string.substring(1);
        }
        throw FormatException.getFormatInstance();
    }

    private static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i + 2 > iArr[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] iArr2 = new int[2];
        int i2 = 0;
        while (i2 < 2) {
            iArr2[i2] = iArr[i];
            i2++;
            i++;
        }
        pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
        StringBuilder sb = new StringBuilder();
        int iTextCompaction = textCompaction(iArr, i, sb);
        pDF417ResultMetadata.setFileId(sb.toString());
        int i3 = iArr[iTextCompaction];
        if (i3 != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
            if (i3 != MACRO_PDF417_TERMINATOR) {
                return iTextCompaction;
            }
            pDF417ResultMetadata.setLastSegment(true);
            return iTextCompaction + 1;
        }
        int i4 = iTextCompaction + 1;
        int[] iArr3 = new int[iArr[0] - i4];
        boolean z = false;
        int i5 = 0;
        while (i4 < iArr[0] && !z) {
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            if (i7 < TEXT_COMPACTION_MODE_LATCH) {
                iArr3[i5] = i7;
                i5++;
                i4 = i6;
            } else {
                if (i7 != MACRO_PDF417_TERMINATOR) {
                    throw FormatException.getFormatInstance();
                }
                pDF417ResultMetadata.setLastSegment(true);
                i4 += 2;
                z = true;
            }
        }
        pDF417ResultMetadata.setOptionalData(Arrays.copyOf(iArr3, i5));
        return i4;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static void decodeTextCompaction(int[] iArr, int[] iArr2, int i, StringBuilder sb) {
        Mode mode;
        int i2;
        Mode mode2 = Mode.ALPHA;
        Mode mode3 = mode2;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            char c = ' ';
            switch (AnonymousClass1.$SwitchMap$com$dcloud$zxing2$pdf417$decoder$DecodedBitStreamParser$Mode[mode2.ordinal()]) {
                case 1:
                    if (i4 < 26) {
                        i2 = i4 + 65;
                        c = (char) i2;
                        break;
                    } else if (i4 != 26) {
                        if (i4 == 27) {
                            mode = Mode.LOWER;
                        } else if (i4 == 28) {
                            mode = Mode.MIXED;
                        } else if (i4 == 29) {
                            mode3 = Mode.PUNCT_SHIFT;
                            c = 0;
                            Mode mode4 = mode3;
                            mode3 = mode2;
                            mode2 = mode4;
                            break;
                        } else {
                            if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                sb.append((char) iArr2[i3]);
                            } else if (i4 == TEXT_COMPACTION_MODE_LATCH) {
                                mode = Mode.ALPHA;
                            }
                            c = 0;
                            break;
                        }
                        c = 0;
                        Mode mode5 = mode3;
                        mode3 = mode;
                        mode2 = mode5;
                        Mode mode42 = mode3;
                        mode3 = mode2;
                        mode2 = mode42;
                    }
                    break;
                case 2:
                    if (i4 < 26) {
                        i2 = i4 + 97;
                        c = (char) i2;
                        break;
                    } else if (i4 != 26) {
                        if (i4 != 27) {
                            if (i4 == 28) {
                                mode = Mode.MIXED;
                            } else if (i4 != 29) {
                                if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                    sb.append((char) iArr2[i3]);
                                } else if (i4 == TEXT_COMPACTION_MODE_LATCH) {
                                    mode = Mode.ALPHA;
                                }
                                c = 0;
                                break;
                            } else {
                                mode3 = Mode.PUNCT_SHIFT;
                            }
                            c = 0;
                            Mode mode52 = mode3;
                            mode3 = mode;
                            mode2 = mode52;
                            Mode mode422 = mode3;
                            mode3 = mode2;
                            mode2 = mode422;
                            break;
                        } else {
                            mode3 = Mode.ALPHA_SHIFT;
                        }
                        c = 0;
                        Mode mode4222 = mode3;
                        mode3 = mode2;
                        mode2 = mode4222;
                    }
                    break;
                case 3:
                    if (i4 < 25) {
                        c = MIXED_CHARS[i4];
                        break;
                    } else {
                        if (i4 == 25) {
                            mode = Mode.PUNCT;
                        } else if (i4 != 26) {
                            if (i4 == 27) {
                                mode = Mode.LOWER;
                            } else if (i4 == 28) {
                                mode = Mode.ALPHA;
                            } else if (i4 == 29) {
                                mode3 = Mode.PUNCT_SHIFT;
                                c = 0;
                                Mode mode42222 = mode3;
                                mode3 = mode2;
                                mode2 = mode42222;
                                break;
                            } else {
                                if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                    sb.append((char) iArr2[i3]);
                                } else if (i4 == TEXT_COMPACTION_MODE_LATCH) {
                                    mode = Mode.ALPHA;
                                }
                                c = 0;
                                break;
                            }
                        }
                        c = 0;
                        Mode mode522 = mode3;
                        mode3 = mode;
                        mode2 = mode522;
                        Mode mode422222 = mode3;
                        mode3 = mode2;
                        mode2 = mode422222;
                    }
                    break;
                case 4:
                    if (i4 < 29) {
                        c = PUNCT_CHARS[i4];
                        break;
                    } else {
                        if (i4 != 29) {
                            if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                sb.append((char) iArr2[i3]);
                            } else if (i4 == TEXT_COMPACTION_MODE_LATCH) {
                                mode = Mode.ALPHA;
                            }
                            c = 0;
                            break;
                        } else {
                            mode = Mode.ALPHA;
                        }
                        c = 0;
                        Mode mode5222 = mode3;
                        mode3 = mode;
                        mode2 = mode5222;
                        Mode mode4222222 = mode3;
                        mode3 = mode2;
                        mode2 = mode4222222;
                        break;
                    }
                case 5:
                    if (i4 < 26) {
                        c = (char) (i4 + 65);
                    } else if (i4 != 26) {
                        if (i4 == TEXT_COMPACTION_MODE_LATCH) {
                            mode = Mode.ALPHA;
                            c = 0;
                            Mode mode52222 = mode3;
                            mode3 = mode;
                            mode2 = mode52222;
                            Mode mode42222222 = mode3;
                            mode3 = mode2;
                            mode2 = mode42222222;
                            break;
                        }
                        c = 0;
                    }
                    mode2 = mode3;
                    Mode mode422222222 = mode3;
                    mode3 = mode2;
                    mode2 = mode422222222;
                case 6:
                    if (i4 < 29) {
                        c = PUNCT_CHARS[i4];
                        mode2 = mode3;
                        Mode mode4222222222 = mode3;
                        mode3 = mode2;
                        mode2 = mode4222222222;
                        break;
                    } else {
                        if (i4 == 29) {
                            mode = Mode.ALPHA;
                        } else {
                            if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                sb.append((char) iArr2[i3]);
                            } else if (i4 == TEXT_COMPACTION_MODE_LATCH) {
                                mode = Mode.ALPHA;
                            }
                            c = 0;
                            mode2 = mode3;
                            Mode mode42222222222 = mode3;
                            mode3 = mode2;
                            mode2 = mode42222222222;
                        }
                        c = 0;
                        Mode mode522222 = mode3;
                        mode3 = mode;
                        mode2 = mode522222;
                        Mode mode422222222222 = mode3;
                        mode3 = mode2;
                        mode2 = mode422222222222;
                    }
                default:
                    c = 0;
                    break;
            }
            if (c != 0) {
                sb.append(c);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0042 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0007 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int numericCompaction(int[] r8, int r9, java.lang.StringBuilder r10) throws com.dcloud.zxing2.FormatException {
        /*
            r0 = 15
            int[] r0 = new int[r0]
            r1 = 0
            r2 = r1
            r3 = r2
        L7:
            r4 = r8[r1]
            if (r9 >= r4) goto L4b
            if (r2 != 0) goto L4b
            int r5 = r9 + 1
            r6 = r8[r9]
            r7 = 1
            if (r5 != r4) goto L15
            r2 = r7
        L15:
            r4 = 900(0x384, float:1.261E-42)
            if (r6 >= r4) goto L1f
            r0[r3] = r6
            int r3 = r3 + 1
        L1d:
            r9 = r5
            goto L36
        L1f:
            if (r6 == r4) goto L35
            r4 = 901(0x385, float:1.263E-42)
            if (r6 == r4) goto L35
            r4 = 924(0x39c, float:1.295E-42)
            if (r6 == r4) goto L35
            r4 = 928(0x3a0, float:1.3E-42)
            if (r6 == r4) goto L35
            r4 = 923(0x39b, float:1.293E-42)
            if (r6 == r4) goto L35
            r4 = 922(0x39a, float:1.292E-42)
            if (r6 != r4) goto L1d
        L35:
            r2 = r7
        L36:
            int r4 = r3 % 15
            if (r4 == 0) goto L40
            r4 = 902(0x386, float:1.264E-42)
            if (r6 == r4) goto L40
            if (r2 == 0) goto L7
        L40:
            if (r3 <= 0) goto L7
            java.lang.String r3 = decodeBase900toBase10(r0, r3)
            r10.append(r3)
            r3 = r1
            goto L7
        L4b:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dcloud.zxing2.pdf417.decoder.DecodedBitStreamParser.numericCompaction(int[], int, java.lang.StringBuilder):int");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:14:0x0030. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x0033. Please report as an issue. */
    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int i2 = (iArr[0] - i) * 2;
        int[] iArr2 = new int[i2];
        int[] iArr3 = new int[i2];
        boolean z = false;
        int i3 = 0;
        while (i < iArr[0] && !z) {
            int i4 = i + 1;
            int i5 = iArr[i];
            if (i5 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i3] = i5 / 30;
                iArr2[i3 + 1] = i5 % 30;
                i3 += 2;
            } else if (i5 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i5 != 928) {
                    switch (i5) {
                        case TEXT_COMPACTION_MODE_LATCH /* 900 */:
                            iArr2[i3] = TEXT_COMPACTION_MODE_LATCH;
                            i3++;
                            break;
                        case BYTE_COMPACTION_MODE_LATCH /* 901 */:
                        case NUMERIC_COMPACTION_MODE_LATCH /* 902 */:
                            break;
                        default:
                            switch (i5) {
                            }
                    }
                }
                z = true;
            } else {
                iArr2[i3] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i += 2;
                iArr3[i3] = iArr[i4];
                i3++;
            }
            i = i4;
        }
        decodeTextCompaction(iArr2, iArr3, i3, sb);
        return i;
    }
}
