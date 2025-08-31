package com.dcloud.zxing2.common.detector;

import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ResultPoint;
import com.dcloud.zxing2.common.BitMatrix;

/* loaded from: classes.dex */
public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    public MonochromeRectangleDetector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0035 A[EDGE_INSN: B:64:0x0035->B:20:0x0035 BREAK  A[LOOP:1: B:12:0x001e->B:68:0x001e], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x006f A[EDGE_INSN: B:81:0x006f->B:43:0x006f BREAK  A[LOOP:3: B:35:0x0059->B:86:0x0059], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int[] blackWhiteRange(int r6, int r7, int r8, int r9, boolean r10) {
        /*
            r5 = this;
            int r0 = r8 + r9
            r1 = 2
            int r0 = r0 / r1
            r2 = r0
        L5:
            if (r2 < r8) goto L3e
            if (r10 == 0) goto L12
            com.dcloud.zxing2.common.BitMatrix r3 = r5.image
            boolean r3 = r3.get(r2, r6)
            if (r3 == 0) goto L1d
            goto L1a
        L12:
            com.dcloud.zxing2.common.BitMatrix r3 = r5.image
            boolean r3 = r3.get(r6, r2)
            if (r3 == 0) goto L1d
        L1a:
            int r2 = r2 + (-1)
            goto L5
        L1d:
            r3 = r2
        L1e:
            int r3 = r3 + (-1)
            if (r3 < r8) goto L35
            if (r10 == 0) goto L2d
            com.dcloud.zxing2.common.BitMatrix r4 = r5.image
            boolean r4 = r4.get(r3, r6)
            if (r4 == 0) goto L1e
            goto L35
        L2d:
            com.dcloud.zxing2.common.BitMatrix r4 = r5.image
            boolean r4 = r4.get(r6, r3)
            if (r4 == 0) goto L1e
        L35:
            int r4 = r2 - r3
            if (r3 < r8) goto L3e
            if (r4 <= r7) goto L3c
            goto L3e
        L3c:
            r2 = r3
            goto L5
        L3e:
            r8 = 1
            int r2 = r2 + r8
        L40:
            if (r0 >= r9) goto L78
            if (r10 == 0) goto L4d
            com.dcloud.zxing2.common.BitMatrix r3 = r5.image
            boolean r3 = r3.get(r0, r6)
            if (r3 == 0) goto L58
            goto L55
        L4d:
            com.dcloud.zxing2.common.BitMatrix r3 = r5.image
            boolean r3 = r3.get(r6, r0)
            if (r3 == 0) goto L58
        L55:
            int r0 = r0 + 1
            goto L40
        L58:
            r3 = r0
        L59:
            int r3 = r3 + r8
            if (r3 >= r9) goto L6f
            if (r10 == 0) goto L67
            com.dcloud.zxing2.common.BitMatrix r4 = r5.image
            boolean r4 = r4.get(r3, r6)
            if (r4 == 0) goto L59
            goto L6f
        L67:
            com.dcloud.zxing2.common.BitMatrix r4 = r5.image
            boolean r4 = r4.get(r6, r3)
            if (r4 == 0) goto L59
        L6f:
            int r4 = r3 - r0
            if (r3 >= r9) goto L78
            if (r4 <= r7) goto L76
            goto L78
        L76:
            r0 = r3
            goto L40
        L78:
            int r0 = r0 + (-1)
            if (r0 <= r2) goto L84
            int[] r6 = new int[r1]
            r7 = 0
            r6[r7] = r2
            r6[r8] = r0
            goto L85
        L84:
            r6 = 0
        L85:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dcloud.zxing2.common.detector.MonochromeRectangleDetector.blackWhiteRange(int, int, int, int, boolean):int[]");
    }

    private ResultPoint findCornerFromCenter(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) throws NotFoundException {
        int[] iArr = null;
        int i10 = i;
        int i11 = i5;
        while (i11 < i8 && i11 >= i7 && i10 < i4 && i10 >= i3) {
            int[] iArrBlackWhiteRange = i2 == 0 ? blackWhiteRange(i11, i9, i3, i4, true) : blackWhiteRange(i10, i9, i7, i8, false);
            if (iArrBlackWhiteRange == null) {
                if (iArr == null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (i2 == 0) {
                    int i12 = i11 - i6;
                    int i13 = iArr[0];
                    if (i13 >= i) {
                        return new ResultPoint(iArr[1], i12);
                    }
                    int i14 = iArr[1];
                    if (i14 > i) {
                        return new ResultPoint(i6 > 0 ? i13 : i14, i12);
                    }
                    return new ResultPoint(i13, i12);
                }
                int i15 = i10 - i2;
                int i16 = iArr[0];
                if (i16 >= i5) {
                    return new ResultPoint(i15, iArr[1]);
                }
                int i17 = iArr[1];
                if (i17 > i5) {
                    return new ResultPoint(i15, i2 < 0 ? i16 : i17);
                }
                return new ResultPoint(i15, i16);
            }
            i11 += i6;
            i10 += i2;
            iArr = iArrBlackWhiteRange;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i = height / 2;
        int i2 = width / 2;
        int iMax = Math.max(1, height / 256);
        int iMax2 = Math.max(1, width / 256);
        int i3 = -iMax;
        int i4 = i2 / 2;
        int y = ((int) findCornerFromCenter(i2, 0, 0, width, i, i3, 0, height, i4).getY()) - 1;
        int i5 = i / 2;
        ResultPoint resultPointFindCornerFromCenter = findCornerFromCenter(i2, -iMax2, 0, width, i, 0, y, height, i5);
        int x = ((int) resultPointFindCornerFromCenter.getX()) - 1;
        ResultPoint resultPointFindCornerFromCenter2 = findCornerFromCenter(i2, iMax2, x, width, i, 0, y, height, i5);
        int x2 = ((int) resultPointFindCornerFromCenter2.getX()) + 1;
        ResultPoint resultPointFindCornerFromCenter3 = findCornerFromCenter(i2, 0, x, x2, i, iMax, y, height, i4);
        return new ResultPoint[]{findCornerFromCenter(i2, 0, x, x2, i, i3, y, ((int) resultPointFindCornerFromCenter3.getY()) + 1, i2 / 4), resultPointFindCornerFromCenter, resultPointFindCornerFromCenter2, resultPointFindCornerFromCenter3};
    }
}
