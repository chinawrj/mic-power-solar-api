package com.dcloud.zxing2.qrcode;

import com.dcloud.zxing2.BarcodeFormat;
import com.dcloud.zxing2.EncodeHintType;
import com.dcloud.zxing2.Writer;
import com.dcloud.zxing2.WriterException;
import com.dcloud.zxing2.common.BitMatrix;
import com.dcloud.zxing2.qrcode.decoder.ErrorCorrectionLevel;
import com.dcloud.zxing2.qrcode.encoder.ByteMatrix;
import com.dcloud.zxing2.qrcode.encoder.Encoder;
import com.dcloud.zxing2.qrcode.encoder.QRCode;
import java.util.Map;

/* loaded from: classes.dex */
public final class QRCodeWriter implements Writer {
    private static final int QUIET_ZONE_SIZE = 4;

    private static BitMatrix renderResult(QRCode qRCode, int i, int i2, int i3) {
        ByteMatrix matrix = qRCode.getMatrix();
        if (matrix == null) {
            throw new IllegalStateException();
        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int i4 = i3 * 2;
        int i5 = width + i4;
        int i6 = i4 + height;
        int iMax = Math.max(i, i5);
        int iMax2 = Math.max(i2, i6);
        int iMin = Math.min(iMax / i5, iMax2 / i6);
        int i7 = (iMax - (width * iMin)) / 2;
        int i8 = (iMax2 - (height * iMin)) / 2;
        BitMatrix bitMatrix = new BitMatrix(iMax, iMax2);
        int i9 = 0;
        while (i9 < height) {
            int i10 = 0;
            int i11 = i7;
            while (i10 < width) {
                if (matrix.get(i10, i9) == 1) {
                    bitMatrix.setRegion(i11, i8, iMin, iMin);
                }
                i10++;
                i11 += iMin;
            }
            i9++;
            i8 += iMin;
        }
        return bitMatrix;
    }

    @Override // com.dcloud.zxing2.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, null);
    }

    @Override // com.dcloud.zxing2.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException, NumberFormatException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (barcodeFormat != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + barcodeFormat);
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        }
        ErrorCorrectionLevel errorCorrectionLevelValueOf = ErrorCorrectionLevel.L;
        int i3 = 4;
        if (map != null) {
            EncodeHintType encodeHintType = EncodeHintType.ERROR_CORRECTION;
            if (map.containsKey(encodeHintType)) {
                errorCorrectionLevelValueOf = ErrorCorrectionLevel.valueOf(map.get(encodeHintType).toString());
            }
            EncodeHintType encodeHintType2 = EncodeHintType.MARGIN;
            if (map.containsKey(encodeHintType2)) {
                i3 = Integer.parseInt(map.get(encodeHintType2).toString());
            }
        }
        return renderResult(Encoder.encode(str, errorCorrectionLevelValueOf, map), i, i2, i3);
    }
}
