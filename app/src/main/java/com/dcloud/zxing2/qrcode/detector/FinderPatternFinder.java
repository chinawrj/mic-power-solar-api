package com.dcloud.zxing2.qrcode.detector;

import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ResultPoint;
import com.dcloud.zxing2.ResultPointCallback;
import com.dcloud.zxing2.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    private static final class CenterComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private CenterComparator(float f) {
            this.average = f;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            if (finderPattern2.getCount() != finderPattern.getCount()) {
                return finderPattern2.getCount() - finderPattern.getCount();
            }
            float fAbs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            float fAbs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            if (fAbs < fAbs2) {
                return 1;
            }
            return fAbs == fAbs2 ? 0 : -1;
        }
    }

    private static final class FurthestFromAverageComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private FurthestFromAverageComparator(float f) {
            this.average = f;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            float fAbs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            float fAbs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            if (fAbs < fAbs2) {
                return -1;
            }
            return fAbs == fAbs2 ? 0 : 1;
        }
    }

    public FinderPatternFinder(BitMatrix bitMatrix) {
        this(bitMatrix, null);
    }

    private static float centerFromEnd(int[] iArr, int i) {
        return ((i - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    private boolean crossCheckDiagonal(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i12 = 0;
        while (i >= i12 && i2 >= i12 && this.image.get(i2 - i12, i - i12)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i12++;
        }
        if (i < i12 || i2 < i12) {
            return false;
        }
        while (i >= i12 && i2 >= i12 && !this.image.get(i2 - i12, i - i12)) {
            int i13 = crossCheckStateCount[1];
            if (i13 > i3) {
                break;
            }
            crossCheckStateCount[1] = i13 + 1;
            i12++;
        }
        if (i < i12 || i2 < i12 || crossCheckStateCount[1] > i3) {
            return false;
        }
        while (i >= i12 && i2 >= i12 && this.image.get(i2 - i12, i - i12)) {
            int i14 = crossCheckStateCount[0];
            if (i14 > i3) {
                break;
            }
            crossCheckStateCount[0] = i14 + 1;
            i12++;
        }
        if (crossCheckStateCount[0] > i3) {
            return false;
        }
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i15 = 1;
        while (true) {
            i5 = i + i15;
            if (i5 >= height || (i11 = i2 + i15) >= width || !this.image.get(i11, i5)) {
                break;
            }
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i15++;
        }
        if (i5 >= height || i2 + i15 >= width) {
            return false;
        }
        while (true) {
            i6 = i + i15;
            if (i6 >= height || (i9 = i2 + i15) >= width || this.image.get(i9, i6) || (i10 = crossCheckStateCount[3]) >= i3) {
                break;
            }
            crossCheckStateCount[3] = i10 + 1;
            i15++;
        }
        if (i6 >= height || i2 + i15 >= width || crossCheckStateCount[3] >= i3) {
            return false;
        }
        while (true) {
            int i16 = i + i15;
            if (i16 >= height || (i7 = i2 + i15) >= width || !this.image.get(i7, i16) || (i8 = crossCheckStateCount[4]) >= i3) {
                break;
            }
            crossCheckStateCount[4] = i8 + 1;
            i15++;
        }
        int i17 = crossCheckStateCount[4];
        return i17 < i3 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + i17) - i4) < i4 * 2 && foundPatternCross(crossCheckStateCount);
    }

    private float crossCheckHorizontal(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        BitMatrix bitMatrix = this.image;
        int width = bitMatrix.getWidth();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i8 = i;
        while (i8 >= 0 && bitMatrix.get(i8, i2)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i8--;
        }
        if (i8 < 0) {
            return Float.NaN;
        }
        while (i8 >= 0 && !bitMatrix.get(i8, i2)) {
            int i9 = crossCheckStateCount[1];
            if (i9 > i3) {
                break;
            }
            crossCheckStateCount[1] = i9 + 1;
            i8--;
        }
        if (i8 < 0 || crossCheckStateCount[1] > i3) {
            return Float.NaN;
        }
        while (i8 >= 0 && bitMatrix.get(i8, i2) && (i7 = crossCheckStateCount[0]) <= i3) {
            crossCheckStateCount[0] = i7 + 1;
            i8--;
        }
        if (crossCheckStateCount[0] > i3) {
            return Float.NaN;
        }
        int i10 = i + 1;
        while (i10 < width && bitMatrix.get(i10, i2)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i10++;
        }
        if (i10 == width) {
            return Float.NaN;
        }
        while (i10 < width && !bitMatrix.get(i10, i2) && (i6 = crossCheckStateCount[3]) < i3) {
            crossCheckStateCount[3] = i6 + 1;
            i10++;
        }
        if (i10 == width || crossCheckStateCount[3] >= i3) {
            return Float.NaN;
        }
        while (i10 < width && bitMatrix.get(i10, i2) && (i5 = crossCheckStateCount[4]) < i3) {
            crossCheckStateCount[4] = i5 + 1;
            i10++;
        }
        int i11 = crossCheckStateCount[4];
        if (i11 < i3 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + i11) - i4) * 5 < i4 && foundPatternCross(crossCheckStateCount)) {
            return centerFromEnd(crossCheckStateCount, i10);
        }
        return Float.NaN;
    }

    private float crossCheckVertical(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i8 = i;
        while (i8 >= 0 && bitMatrix.get(i2, i8)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i8--;
        }
        if (i8 < 0) {
            return Float.NaN;
        }
        while (i8 >= 0 && !bitMatrix.get(i2, i8)) {
            int i9 = crossCheckStateCount[1];
            if (i9 > i3) {
                break;
            }
            crossCheckStateCount[1] = i9 + 1;
            i8--;
        }
        if (i8 < 0 || crossCheckStateCount[1] > i3) {
            return Float.NaN;
        }
        while (i8 >= 0 && bitMatrix.get(i2, i8) && (i7 = crossCheckStateCount[0]) <= i3) {
            crossCheckStateCount[0] = i7 + 1;
            i8--;
        }
        if (crossCheckStateCount[0] > i3) {
            return Float.NaN;
        }
        int i10 = i + 1;
        while (i10 < height && bitMatrix.get(i2, i10)) {
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i10++;
        }
        if (i10 == height) {
            return Float.NaN;
        }
        while (i10 < height && !bitMatrix.get(i2, i10) && (i6 = crossCheckStateCount[3]) < i3) {
            crossCheckStateCount[3] = i6 + 1;
            i10++;
        }
        if (i10 == height || crossCheckStateCount[3] >= i3) {
            return Float.NaN;
        }
        while (i10 < height && bitMatrix.get(i2, i10) && (i5 = crossCheckStateCount[4]) < i3) {
            crossCheckStateCount[4] = i5 + 1;
            i10++;
        }
        int i11 = crossCheckStateCount[4];
        if (i11 < i3 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + i11) - i4) * 5 < i4 * 2 && foundPatternCross(crossCheckStateCount)) {
            return centerFromEnd(crossCheckStateCount, i10);
        }
        return Float.NaN;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (FinderPattern finderPattern2 : this.possibleCenters) {
            if (finderPattern2.getCount() >= 2) {
                if (finderPattern != null) {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(finderPattern.getX() - finderPattern2.getX()) - Math.abs(finderPattern.getY() - finderPattern2.getY()))) / 2;
                }
                finderPattern = finderPattern2;
            }
        }
        return 0;
    }

    protected static boolean foundPatternCross(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = i / 7.0f;
        float f2 = f / 2.0f;
        return Math.abs(f - ((float) iArr[0])) < f2 && Math.abs(f - ((float) iArr[1])) < f2 && Math.abs((f * 3.0f) - ((float) iArr[2])) < 3.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    private int[] getCrossCheckStateCount() {
        int[] iArr = this.crossCheckStateCount;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        return iArr;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int size = this.possibleCenters.size();
        float fAbs = 0.0f;
        int i = 0;
        float estimatedModuleSize = 0.0f;
        for (FinderPattern finderPattern : this.possibleCenters) {
            if (finderPattern.getCount() >= 2) {
                i++;
                estimatedModuleSize += finderPattern.getEstimatedModuleSize();
            }
        }
        if (i < 3) {
            return false;
        }
        float f = estimatedModuleSize / size;
        Iterator<FinderPattern> it = this.possibleCenters.iterator();
        while (it.hasNext()) {
            fAbs += Math.abs(it.next().getEstimatedModuleSize() - f);
        }
        return fAbs <= estimatedModuleSize * 0.05f;
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        int size = this.possibleCenters.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        float estimatedModuleSize = 0.0f;
        if (size > 3) {
            Iterator<FinderPattern> it = this.possibleCenters.iterator();
            float f = 0.0f;
            float f2 = 0.0f;
            while (it.hasNext()) {
                float estimatedModuleSize2 = it.next().getEstimatedModuleSize();
                f += estimatedModuleSize2;
                f2 += estimatedModuleSize2 * estimatedModuleSize2;
            }
            float f3 = f / size;
            float fSqrt = (float) Math.sqrt((f2 / r0) - (f3 * f3));
            Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(f3));
            float fMax = Math.max(0.2f * f3, fSqrt);
            int i = 0;
            while (i < this.possibleCenters.size() && this.possibleCenters.size() > 3) {
                if (Math.abs(this.possibleCenters.get(i).getEstimatedModuleSize() - f3) > fMax) {
                    this.possibleCenters.remove(i);
                    i--;
                }
                i++;
            }
        }
        if (this.possibleCenters.size() > 3) {
            Iterator<FinderPattern> it2 = this.possibleCenters.iterator();
            while (it2.hasNext()) {
                estimatedModuleSize += it2.next().getEstimatedModuleSize();
            }
            Collections.sort(this.possibleCenters, new CenterComparator(estimatedModuleSize / this.possibleCenters.size()));
            List<FinderPattern> list = this.possibleCenters;
            list.subList(3, list.size()).clear();
        }
        return new FinderPattern[]{this.possibleCenters.get(0), this.possibleCenters.get(1), this.possibleCenters.get(2)};
    }

    final FinderPatternInfo find(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        boolean z2 = map != null && map.containsKey(DecodeHintType.PURE_BARCODE);
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i = (height * 3) / 228;
        if (i < 3 || z) {
            i = 3;
        }
        int[] iArr = new int[5];
        int i2 = i - 1;
        boolean zHaveMultiplyConfirmedCenters = false;
        while (i2 < height && !zHaveMultiplyConfirmedCenters) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i3 = 0;
            int i4 = 0;
            while (i3 < width) {
                if (this.image.get(i3, i2)) {
                    if ((i4 & 1) == 1) {
                        i4++;
                    }
                    iArr[i4] = iArr[i4] + 1;
                } else if ((i4 & 1) != 0) {
                    iArr[i4] = iArr[i4] + 1;
                } else if (i4 != 4) {
                    i4++;
                    iArr[i4] = iArr[i4] + 1;
                } else if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i2, i3, z2)) {
                    if (this.hasSkipped) {
                        zHaveMultiplyConfirmedCenters = haveMultiplyConfirmedCenters();
                    } else {
                        int iFindRowSkip = findRowSkip();
                        int i5 = iArr[2];
                        if (iFindRowSkip > i5) {
                            i2 += (iFindRowSkip - i5) - 2;
                            i3 = width - 1;
                        }
                    }
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    i4 = 0;
                    i = 2;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i4 = 3;
                }
                i3++;
            }
            if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i2, width, z2)) {
                i = iArr[0];
                if (this.hasSkipped) {
                    zHaveMultiplyConfirmedCenters = haveMultiplyConfirmedCenters();
                }
            }
            i2 += i;
        }
        FinderPattern[] finderPatternArrSelectBestPatterns = selectBestPatterns();
        ResultPoint.orderBestPatterns(finderPatternArrSelectBestPatterns);
        return new FinderPatternInfo(finderPatternArrSelectBestPatterns);
    }

    protected final BitMatrix getImage() {
        return this.image;
    }

    protected final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    protected final boolean handlePossibleCenter(int[] iArr, int i, int i2, boolean z) {
        int i3 = 0;
        int i4 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int iCenterFromEnd = (int) centerFromEnd(iArr, i2);
        float fCrossCheckVertical = crossCheckVertical(i, iCenterFromEnd, iArr[2], i4);
        if (!Float.isNaN(fCrossCheckVertical)) {
            int i5 = (int) fCrossCheckVertical;
            float fCrossCheckHorizontal = crossCheckHorizontal(iCenterFromEnd, i5, iArr[2], i4);
            if (!Float.isNaN(fCrossCheckHorizontal) && (!z || crossCheckDiagonal(i5, (int) fCrossCheckHorizontal, iArr[2], i4))) {
                float f = i4 / 7.0f;
                while (true) {
                    if (i3 < this.possibleCenters.size()) {
                        FinderPattern finderPattern = this.possibleCenters.get(i3);
                        if (finderPattern.aboutEquals(f, fCrossCheckVertical, fCrossCheckHorizontal)) {
                            this.possibleCenters.set(i3, finderPattern.combineEstimate(fCrossCheckVertical, fCrossCheckHorizontal, f));
                            break;
                        }
                        i3++;
                    } else {
                        FinderPattern finderPattern2 = new FinderPattern(fCrossCheckHorizontal, fCrossCheckVertical, f);
                        this.possibleCenters.add(finderPattern2);
                        ResultPointCallback resultPointCallback = this.resultPointCallback;
                        if (resultPointCallback != null) {
                            resultPointCallback.foundPossibleResultPoint(finderPattern2);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback;
    }
}
