package com.facebook.fresco.animation.drawable;

import com.facebook.common.logging.FLog;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.fresco.animation.frame.FrameScheduler;

/* loaded from: classes.dex */
public class AnimatedDrawable2DebugDrawListener implements AnimatedDrawable2.DrawListener {
    private static final Class<?> TAG = AnimatedDrawable2DebugDrawListener.class;
    private int mDrawCalls;
    private int mDuplicateFrames;
    private int mLastFrameNumber = -1;
    private int mSkippedFrames;

    @Override // com.facebook.fresco.animation.drawable.AnimatedDrawable2.DrawListener
    public void onDraw(AnimatedDrawable2 animatedDrawable, FrameScheduler frameScheduler, int frameNumberToDraw, boolean frameDrawn, boolean isAnimationRunning, long animationStartTimeMs, long animationTimeMs, long lastFrameAnimationTimeMs, long actualRenderTimeStartMs, long actualRenderTimeEndMs, long startRenderTimeForNextFrameMs, long scheduledRenderTimeForNextFrameMs) {
        if (animatedDrawable.getAnimationBackend() == null) {
            return;
        }
        int frameCount = animatedDrawable.getAnimationBackend().getFrameCount();
        long j = animationTimeMs - lastFrameAnimationTimeMs;
        this.mDrawCalls++;
        int i = this.mLastFrameNumber;
        int i2 = (i + 1) % frameCount;
        if (i2 != frameNumberToDraw) {
            if (i == frameNumberToDraw) {
                this.mDuplicateFrames++;
            } else {
                int i3 = (frameNumberToDraw - i2) % frameCount;
                if (i3 < 0) {
                    i3 += frameCount;
                }
                this.mSkippedFrames += i3;
            }
        }
        this.mLastFrameNumber = frameNumberToDraw;
        FLog.d(TAG, "draw: frame: %2d, drawn: %b, delay: %3d ms, rendering: %3d ms, prev: %3d ms ago, duplicates: %3d, skipped: %3d, draw calls: %4d, anim time: %6d ms, next start: %6d ms, next scheduled: %6d ms", Integer.valueOf(frameNumberToDraw), Boolean.valueOf(frameDrawn), Long.valueOf((animationTimeMs % frameScheduler.getLoopDurationMs()) - frameScheduler.getTargetRenderTimeMs(frameNumberToDraw)), Long.valueOf(actualRenderTimeEndMs - actualRenderTimeStartMs), Long.valueOf(j), Integer.valueOf(this.mDuplicateFrames), Integer.valueOf(this.mSkippedFrames), Integer.valueOf(this.mDrawCalls), Long.valueOf(animationTimeMs), Long.valueOf(startRenderTimeForNextFrameMs), Long.valueOf(scheduledRenderTimeForNextFrameMs));
    }
}
