package com.facebook.fresco.animation.bitmap.preparation;

import com.facebook.common.logging.FLog;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;

/* loaded from: classes.dex */
public class FixedNumberBitmapFramePreparationStrategy implements BitmapFramePreparationStrategy {
    private static final int DEFAULT_FRAMES_TO_PREPARE = 3;
    private static final Class<?> TAG = FixedNumberBitmapFramePreparationStrategy.class;
    private final int mFramesToPrepare;

    public FixedNumberBitmapFramePreparationStrategy() {
        this(3);
    }

    public FixedNumberBitmapFramePreparationStrategy(int framesToPrepare) {
        this.mFramesToPrepare = framesToPrepare;
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy
    public void prepareFrames(BitmapFramePreparer bitmapFramePreparer, BitmapFrameCache bitmapFrameCache, AnimationBackend animationBackend, int lastDrawnFrameNumber) {
        for (int i = 1; i <= this.mFramesToPrepare; i++) {
            int frameCount = (lastDrawnFrameNumber + i) % animationBackend.getFrameCount();
            if (FLog.isLoggable(2)) {
                FLog.v(TAG, "Preparing frame %d, last drawn: %d", Integer.valueOf(frameCount), Integer.valueOf(lastDrawnFrameNumber));
            }
            if (!bitmapFramePreparer.prepareFrame(bitmapFrameCache, animationBackend, frameCount)) {
                return;
            }
        }
    }
}
