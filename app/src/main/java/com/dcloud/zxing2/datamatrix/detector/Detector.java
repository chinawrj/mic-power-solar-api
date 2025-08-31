package com.dcloud.zxing2.datamatrix.detector;

import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ResultPoint;
import com.dcloud.zxing2.common.BitMatrix;
import com.dcloud.zxing2.common.DetectorResult;
import com.dcloud.zxing2.common.GridSampler;
import com.dcloud.zxing2.common.detector.MathUtils;
import com.dcloud.zxing2.common.detector.WhiteRectangleDetector;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class Detector {
    private final BitMatrix image;
    private final WhiteRectangleDetector rectangleDetector;

    private static final class ResultPointsAndTransitions {
        private final ResultPoint from;
        private final ResultPoint to;
        private final int transitions;

        ResultPoint getFrom() {
            return this.from;
        }

        ResultPoint getTo() {
            return this.to;
        }

        public int getTransitions() {
            return this.transitions;
        }

        public String toString() {
            return this.from + "/" + this.to + '/' + this.transitions;
        }

        private ResultPointsAndTransitions(ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
            this.from = resultPoint;
            this.to = resultPoint2;
            this.transitions = i;
        }
    }

    private static final class ResultPointsAndTransitionsComparator implements Comparator<ResultPointsAndTransitions>, Serializable {
        private ResultPointsAndTransitionsComparator() {
        }

        @Override // java.util.Comparator
        public int compare(ResultPointsAndTransitions resultPointsAndTransitions, ResultPointsAndTransitions resultPointsAndTransitions2) {
            return resultPointsAndTransitions.getTransitions() - resultPointsAndTransitions2.getTransitions();
        }
    }

    public Detector(BitMatrix bitMatrix) throws NotFoundException {
        this.image = bitMatrix;
        this.rectangleDetector = new WhiteRectangleDetector(bitMatrix);
    }

    private ResultPoint correctTopRight(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i) {
        float f = i;
        float fDistance = distance(resultPoint, resultPoint2) / f;
        float fDistance2 = distance(resultPoint3, resultPoint4);
        ResultPoint resultPoint5 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint3.getX()) / fDistance2) * fDistance), resultPoint4.getY() + (fDistance * ((resultPoint4.getY() - resultPoint3.getY()) / fDistance2)));
        float fDistance3 = distance(resultPoint, resultPoint3) / f;
        float fDistance4 = distance(resultPoint2, resultPoint4);
        ResultPoint resultPoint6 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint2.getX()) / fDistance4) * fDistance3), resultPoint4.getY() + (fDistance3 * ((resultPoint4.getY() - resultPoint2.getY()) / fDistance4)));
        if (isValid(resultPoint5)) {
            return (isValid(resultPoint6) && Math.abs(transitionsBetween(resultPoint3, resultPoint5).getTransitions() - transitionsBetween(resultPoint2, resultPoint5).getTransitions()) > Math.abs(transitionsBetween(resultPoint3, resultPoint6).getTransitions() - transitionsBetween(resultPoint2, resultPoint6).getTransitions())) ? resultPoint6 : resultPoint5;
        }
        if (isValid(resultPoint6)) {
            return resultPoint6;
        }
        return null;
    }

    private ResultPoint correctTopRightRectangular(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) {
        float fDistance = distance(resultPoint, resultPoint2) / i;
        float fDistance2 = distance(resultPoint3, resultPoint4);
        ResultPoint resultPoint5 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint3.getX()) / fDistance2) * fDistance), resultPoint4.getY() + (fDistance * ((resultPoint4.getY() - resultPoint3.getY()) / fDistance2)));
        float fDistance3 = distance(resultPoint, resultPoint3) / i2;
        float fDistance4 = distance(resultPoint2, resultPoint4);
        ResultPoint resultPoint6 = new ResultPoint(resultPoint4.getX() + (((resultPoint4.getX() - resultPoint2.getX()) / fDistance4) * fDistance3), resultPoint4.getY() + (fDistance3 * ((resultPoint4.getY() - resultPoint2.getY()) / fDistance4)));
        if (isValid(resultPoint5)) {
            return (isValid(resultPoint6) && Math.abs(i - transitionsBetween(resultPoint3, resultPoint5).getTransitions()) + Math.abs(i2 - transitionsBetween(resultPoint2, resultPoint5).getTransitions()) > Math.abs(i - transitionsBetween(resultPoint3, resultPoint6).getTransitions()) + Math.abs(i2 - transitionsBetween(resultPoint2, resultPoint6).getTransitions())) ? resultPoint6 : resultPoint5;
        }
        if (isValid(resultPoint6)) {
            return resultPoint6;
        }
        return null;
    }

    private static int distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.round(ResultPoint.distance(resultPoint, resultPoint2));
    }

    private static void increment(Map<ResultPoint, Integer> map, ResultPoint resultPoint) {
        Integer num = map.get(resultPoint);
        map.put(resultPoint, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    private boolean isValid(ResultPoint resultPoint) {
        return resultPoint.getX() >= 0.0f && resultPoint.getX() < ((float) this.image.getWidth()) && resultPoint.getY() > 0.0f && resultPoint.getY() < ((float) this.image.getHeight());
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) throws NotFoundException {
        float f = i - 0.5f;
        float f2 = i2 - 0.5f;
        return GridSampler.getInstance().sampleGrid(bitMatrix, i, i2, 0.5f, 0.5f, f, 0.5f, f, f2, 0.5f, f2, resultPoint.getX(), resultPoint.getY(), resultPoint4.getX(), resultPoint4.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private ResultPointsAndTransitions transitionsBetween(ResultPoint resultPoint, ResultPoint resultPoint2) {
        int x = (int) resultPoint.getX();
        int y = (int) resultPoint.getY();
        int x2 = (int) resultPoint2.getX();
        int y2 = (int) resultPoint2.getY();
        int i = 0;
        boolean z = Math.abs(y2 - y) > Math.abs(x2 - x);
        if (!z) {
            y = x;
            x = y;
            y2 = x2;
            x2 = y2;
        }
        int iAbs = Math.abs(y2 - y);
        int iAbs2 = Math.abs(x2 - x);
        int i2 = (-iAbs) / 2;
        int i3 = x < x2 ? 1 : -1;
        int i4 = y >= y2 ? -1 : 1;
        boolean z2 = this.image.get(z ? x : y, z ? y : x);
        while (y != y2) {
            boolean z3 = this.image.get(z ? x : y, z ? y : x);
            if (z3 != z2) {
                i++;
                z2 = z3;
            }
            i2 += iAbs2;
            if (i2 > 0) {
                if (x == x2) {
                    break;
                }
                x += i3;
                i2 -= iAbs;
            }
            y += i4;
        }
        return new ResultPointsAndTransitions(resultPoint, resultPoint2, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v3, types: [com.dcloud.zxing2.ResultPoint] */
    /* JADX WARN: Type inference failed for: r16v3, types: [com.dcloud.zxing2.ResultPoint] */
    /* JADX WARN: Type inference failed for: r22v0, types: [com.dcloud.zxing2.ResultPoint] */
    /* JADX WARN: Type inference failed for: r23v0, types: [com.dcloud.zxing2.datamatrix.detector.Detector] */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.dcloud.zxing2.ResultPoint[]] */
    /* JADX WARN: Type inference failed for: r4v6, types: [com.dcloud.zxing2.ResultPoint[]] */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.dcloud.zxing2.ResultPoint] */
    public DetectorResult detect() throws NotFoundException {
        ResultPoint resultPoint;
        BitMatrix bitMatrixSampleGrid;
        ResultPoint[] resultPointArrDetect = this.rectangleDetector.detect();
        ResultPoint resultPoint2 = resultPointArrDetect[0];
        ResultPoint resultPoint3 = resultPointArrDetect[1];
        ResultPoint resultPoint4 = resultPointArrDetect[2];
        ResultPoint resultPoint5 = resultPointArrDetect[3];
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(transitionsBetween(resultPoint2, resultPoint3));
        arrayList.add(transitionsBetween(resultPoint2, resultPoint4));
        arrayList.add(transitionsBetween(resultPoint3, resultPoint5));
        arrayList.add(transitionsBetween(resultPoint4, resultPoint5));
        AnonymousClass1 anonymousClass1 = null;
        Collections.sort(arrayList, new ResultPointsAndTransitionsComparator());
        ResultPointsAndTransitions resultPointsAndTransitions = (ResultPointsAndTransitions) arrayList.get(0);
        ResultPointsAndTransitions resultPointsAndTransitions2 = (ResultPointsAndTransitions) arrayList.get(1);
        HashMap map = new HashMap();
        increment(map, resultPointsAndTransitions.getFrom());
        increment(map, resultPointsAndTransitions.getTo());
        increment(map, resultPointsAndTransitions2.getFrom());
        increment(map, resultPointsAndTransitions2.getTo());
        Object obj = null;
        Object obj2 = null;
        for (Map.Entry entry : map.entrySet()) {
            ?? r16 = (ResultPoint) entry.getKey();
            if (((Integer) entry.getValue()).intValue() == 2) {
                obj = r16;
            } else if (anonymousClass1 == null) {
                anonymousClass1 = r16;
            } else {
                obj2 = r16;
            }
        }
        if (anonymousClass1 == null || obj == null || obj2 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ?? r4 = {anonymousClass1, obj, obj2};
        ResultPoint.orderBestPatterns(r4);
        ?? r14 = r4[0];
        ?? r22 = r4[1];
        ?? r6 = r4[2];
        ResultPoint resultPoint6 = !map.containsKey(resultPoint2) ? resultPoint2 : !map.containsKey(resultPoint3) ? resultPoint3 : !map.containsKey(resultPoint4) ? resultPoint4 : resultPoint5;
        int transitions = transitionsBetween(r6, resultPoint6).getTransitions();
        int transitions2 = transitionsBetween(r14, resultPoint6).getTransitions();
        if ((transitions & 1) == 1) {
            transitions++;
        }
        int i = transitions + 2;
        if ((transitions2 & 1) == 1) {
            transitions2++;
        }
        int i2 = transitions2 + 2;
        if (i * 4 >= i2 * 7 || i2 * 4 >= i * 7) {
            resultPoint = r6;
            ResultPoint resultPointCorrectTopRightRectangular = correctTopRightRectangular(r22, r14, r6, resultPoint6, i, i2);
            if (resultPointCorrectTopRightRectangular != null) {
                resultPoint6 = resultPointCorrectTopRightRectangular;
            }
            int transitions3 = transitionsBetween(resultPoint, resultPoint6).getTransitions();
            int transitions4 = transitionsBetween(r14, resultPoint6).getTransitions();
            if ((transitions3 & 1) == 1) {
                transitions3++;
            }
            int i3 = transitions3;
            if ((transitions4 & 1) == 1) {
                transitions4++;
            }
            bitMatrixSampleGrid = sampleGrid(this.image, resultPoint, r22, r14, resultPoint6, i3, transitions4);
        } else {
            ResultPoint resultPointCorrectTopRight = correctTopRight(r22, r14, r6, resultPoint6, Math.min(i2, i));
            if (resultPointCorrectTopRight != null) {
                resultPoint6 = resultPointCorrectTopRight;
            }
            int iMax = Math.max(transitionsBetween(r6, resultPoint6).getTransitions(), transitionsBetween(r14, resultPoint6).getTransitions());
            int i4 = iMax + 1;
            int i5 = (i4 & 1) == 1 ? iMax + 2 : i4;
            bitMatrixSampleGrid = sampleGrid(this.image, r6, r22, r14, resultPoint6, i5, i5);
            resultPoint = r6;
        }
        return new DetectorResult(bitMatrixSampleGrid, new ResultPoint[]{resultPoint, r22, r14, resultPoint6});
    }
}
