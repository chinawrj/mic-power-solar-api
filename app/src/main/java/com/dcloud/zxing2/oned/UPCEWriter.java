package com.dcloud.zxing2.oned;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.EncodeHintType;
import com.dcloud.zxing2.WriterException;
import com.dcloud.zxing2.common.BitMatrix;
import java.util.Map;

/* loaded from: classes.dex */
public final class UPCEWriter extends UPCEANWriter {
    private static final int CODE_WIDTH = 51;

    @Override // com.dcloud.zxing2.oned.OneDimensionalCodeWriter, com.dcloud.zxing2.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.UPC_E) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode UPC_E, but got " + barcodeFormat);
    }

    @Override // com.dcloud.zxing2.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) throws NumberFormatException {
        if (str.length() == 8) {
            int i = UPCEReader.CHECK_DIGIT_ENCODINGS[Integer.parseInt(str.substring(7, 8))];
            boolean[] zArr = new boolean[51];
            int iAppendPattern = OneDimensionalCodeWriter.appendPattern(zArr, 0, UPCEANReader.START_END_PATTERN, true);
            int i2 = 1;
            while (i2 <= 6) {
                int i3 = i2 + 1;
                int i4 = Integer.parseInt(str.substring(i2, i3));
                if (((i >> (6 - i2)) & 1) == 1) {
                    i4 += 10;
                }
                iAppendPattern += OneDimensionalCodeWriter.appendPattern(zArr, iAppendPattern, UPCEANReader.L_AND_G_PATTERNS[i4], false);
                i2 = i3;
            }
            OneDimensionalCodeWriter.appendPattern(zArr, iAppendPattern, UPCEANReader.END_PATTERN, false);
            return zArr;
        }
        throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + str.length());
    }
}
