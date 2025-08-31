package io.dcloud.feature.barcode2.view;

import android.graphics.Rect;
import io.dcloud.common.DHInterface.IReflectAble;

/* loaded from: classes3.dex */
public class DetectorViewConfig implements IReflectAble {
    public static int CORNER_HEIGHT = 40;
    public static int CORNER_WIDTH = 8;
    public static final int F_CORNER_COLOR = -65536;
    public static final int F_LASER_COLOR = -65536;
    public static int LASER_WIDTH = 8;
    private static final int MAX_FRAME_HEIGHT = 360;
    private static final int MAX_FRAME_WIDTH = 640;
    private static final int MIN_FRAME_HEIGHT = 240;
    private static final int MIN_FRAME_WIDTH = 240;
    public static int cornerColor = -65536;
    public static int detectorRectOffestLeft = 0;
    public static int detectorRectOffestTop = 0;
    private static DetectorViewConfig instance = null;
    public static int laserColor = -65536;
    public static int maskColor = 1610612736;
    public static int resultPointColor = -1056964864;
    public Rect surfaceViewRect = null;
    public Rect gatherRect = new Rect();
    private Rect detectorRect = null;
    private boolean retry = false;

    private DetectorViewConfig() {
    }

    public static void clearData() {
        instance = null;
    }

    public static DetectorViewConfig getInstance() {
        if (instance == null) {
            instance = new DetectorViewConfig();
        }
        return instance;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0028 A[PHI: r3
  0x0028: PHI (r3v7 int) = (r3v2 int), (r3v3 int) binds: [B:15:0x0026, B:18:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Rect getDetectorRect() {
        /*
            r5 = this;
            boolean r0 = r5.retry
            if (r0 != 0) goto L8
            android.graphics.Rect r0 = r5.detectorRect
            if (r0 != 0) goto L45
        L8:
            android.graphics.Rect r0 = r5.gatherRect
            int r0 = r0.width()
            android.graphics.Rect r1 = r5.gatherRect
            int r1 = r1.height()
            if (r0 >= r1) goto L18
            r2 = r0
            goto L19
        L18:
            r2 = r1
        L19:
            int r2 = r2 * 6
            int r2 = r2 / 10
            if (r2 >= 0) goto L21
            r3 = 1
            goto L22
        L21:
            r3 = 0
        L22:
            r5.retry = r3
            r3 = 240(0xf0, float:3.36E-43)
            if (r2 >= r3) goto L2a
        L28:
            r2 = r3
            goto L2f
        L2a:
            r3 = 640(0x280, float:8.97E-43)
            if (r2 <= r3) goto L2f
            goto L28
        L2f:
            int r3 = r2 * 10
            int r3 = r3 / 100
            io.dcloud.feature.barcode2.view.DetectorViewConfig.CORNER_HEIGHT = r3
            int r0 = r0 - r2
            int r0 = r0 / 2
            int r1 = r1 - r2
            int r1 = r1 / 2
            android.graphics.Rect r3 = new android.graphics.Rect
            int r4 = r0 + r2
            int r2 = r2 + r1
            r3.<init>(r0, r1, r4, r2)
            r5.detectorRect = r3
        L45:
            android.graphics.Rect r0 = r5.detectorRect
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.barcode2.view.DetectorViewConfig.getDetectorRect():android.graphics.Rect");
    }

    public void initSurfaceViewRect(int i, int i2, int i3, int i4) {
        this.surfaceViewRect = new Rect(i, i2, i3 + i, i4 + i2);
    }
}
