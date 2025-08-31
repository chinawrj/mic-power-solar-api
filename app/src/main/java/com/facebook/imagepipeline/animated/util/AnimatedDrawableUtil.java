package com.facebook.imagepipeline.animated.util;

import android.graphics.Bitmap;
import java.util.Arrays;

/* loaded from: classes.dex */
public class AnimatedDrawableUtil {
    private static final int FRAME_DURATION_MS_FOR_MIN = 100;
    private static final int MIN_FRAME_DURATION_MS = 11;

    public static boolean isOutsideRange(int startFrame, int endFrame, int frameNumber) {
        if (startFrame == -1 || endFrame == -1) {
            return true;
        }
        if (startFrame <= endFrame) {
            if (frameNumber < startFrame || frameNumber > endFrame) {
                return true;
            }
        } else if (frameNumber < startFrame && frameNumber > endFrame) {
            return true;
        }
        return false;
    }

    public void fixFrameDurations(int[] frameDurationMs) {
        for (int i = 0; i < frameDurationMs.length; i++) {
            if (frameDurationMs[i] < 11) {
                frameDurationMs[i] = 100;
            }
        }
    }

    public int getTotalDurationFromFrameDurations(int[] frameDurationMs) {
        int i = 0;
        for (int i2 : frameDurationMs) {
            i += i2;
        }
        return i;
    }

    public int[] getFrameTimeStampsFromDurations(int[] frameDurationsMs) {
        int[] iArr = new int[frameDurationsMs.length];
        int i = 0;
        for (int i2 = 0; i2 < frameDurationsMs.length; i2++) {
            iArr[i2] = i;
            i += frameDurationsMs[i2];
        }
        return iArr;
    }

    public int getFrameForTimestampMs(int[] frameTimestampsMs, int timestampMs) {
        int iBinarySearch = Arrays.binarySearch(frameTimestampsMs, timestampMs);
        return iBinarySearch < 0 ? (-iBinarySearch) - 2 : iBinarySearch;
    }

    public int getSizeOfBitmap(Bitmap bitmap) {
        return bitmap.getAllocationByteCount();
    }
}
