package com.dcloud.zxing2.oned;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.EncodeHintType;
import com.dcloud.zxing2.WriterException;
import com.dcloud.zxing2.common.BitMatrix;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.mozilla.universalchardet.prober.HebrewProber;
import org.mozilla.universalchardet.prober.contextanalysis.SJISContextAnalysis;

/* loaded from: classes.dex */
public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = 241;
    private static final char ESCAPE_FNC_2 = 242;
    private static final char ESCAPE_FNC_3 = 243;
    private static final char ESCAPE_FNC_4 = 244;

    private static boolean isDigits(CharSequence charSequence, int i, int i2) {
        int i3 = i2 + i;
        int length = charSequence.length();
        while (i < i3 && i < length) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt < '0' || cCharAt > '9') {
                if (cCharAt != 241) {
                    return false;
                }
                i3++;
            }
            i++;
        }
        return i3 <= length;
    }

    @Override // com.dcloud.zxing2.oned.OneDimensionalCodeWriter, com.dcloud.zxing2.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_128, but got " + barcodeFormat);
    }

    @Override // com.dcloud.zxing2.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) throws NumberFormatException {
        int length = str.length();
        if (length >= 1 && length <= 80) {
            int iAppendPattern = 0;
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt < ' ' || cCharAt > '~') {
                    switch (cCharAt) {
                        case SJISContextAnalysis.HIRAGANA_LOWBYTE_END /* 241 */:
                        case 242:
                        case 243:
                        case HebrewProber.NORMAL_PE /* 244 */:
                            break;
                        default:
                            throw new IllegalArgumentException("Bad character in input: " + cCharAt);
                    }
                }
            }
            ArrayList<int[]> arrayList = new ArrayList();
            int i2 = 1;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (i3 < length) {
                int iCharAt = 100;
                int i6 = isDigits(str, i3, i5 == 99 ? 2 : 4) ? 99 : 100;
                if (i6 == i5) {
                    switch (str.charAt(i3)) {
                        case SJISContextAnalysis.HIRAGANA_LOWBYTE_END /* 241 */:
                            iCharAt = 102;
                            break;
                        case 242:
                            iCharAt = 97;
                            break;
                        case 243:
                            iCharAt = 96;
                            break;
                        case HebrewProber.NORMAL_PE /* 244 */:
                            break;
                        default:
                            if (i5 != 100) {
                                iCharAt = Integer.parseInt(str.substring(i3, i3 + 2));
                                i3++;
                                break;
                            } else {
                                iCharAt = str.charAt(i3) - ' ';
                                break;
                            }
                    }
                    i3++;
                } else {
                    iCharAt = i5 == 0 ? i6 == 100 ? 104 : CODE_START_C : i6;
                    i5 = i6;
                }
                arrayList.add(Code128Reader.CODE_PATTERNS[iCharAt]);
                i4 += iCharAt * i2;
                if (i3 != 0) {
                    i2++;
                }
            }
            int[][] iArr = Code128Reader.CODE_PATTERNS;
            arrayList.add(iArr[i4 % 103]);
            arrayList.add(iArr[CODE_STOP]);
            int i7 = 0;
            for (int[] iArr2 : arrayList) {
                for (int i8 : iArr2) {
                    i7 += i8;
                }
            }
            boolean[] zArr = new boolean[i7];
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                iAppendPattern += OneDimensionalCodeWriter.appendPattern(zArr, iAppendPattern, (int[]) it.next(), true);
            }
            return zArr;
        }
        throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got " + length);
    }
}
