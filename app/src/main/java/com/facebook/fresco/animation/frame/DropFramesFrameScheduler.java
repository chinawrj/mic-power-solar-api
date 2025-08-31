package com.facebook.fresco.animation.frame;

import com.facebook.fresco.animation.backend.AnimationInformation;

/* loaded from: classes.dex */
public class DropFramesFrameScheduler implements FrameScheduler {
    private static final int UNSET = -1;
    private final AnimationInformation mAnimationInformation;
    private long mLoopDurationMs = -1;

    public DropFramesFrameScheduler(AnimationInformation animationInformation) {
        this.mAnimationInformation = animationInformation;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public int getFrameNumberToRender(long animationTimeMs, long lastFrameTimeMs) {
        long loopDurationMs = getLoopDurationMs();
        if (loopDurationMs == 0) {
            return getFrameNumberWithinLoop(0L);
        }
        if (isInfiniteAnimation() || animationTimeMs / loopDurationMs < this.mAnimationInformation.getLoopCount()) {
            return getFrameNumberWithinLoop(animationTimeMs % loopDurationMs);
        }
        return -1;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getLoopDurationMs() {
        long j = this.mLoopDurationMs;
        if (j != -1) {
            return j;
        }
        this.mLoopDurationMs = 0L;
        int frameCount = this.mAnimationInformation.getFrameCount();
        for (int i = 0; i < frameCount; i++) {
            this.mLoopDurationMs += this.mAnimationInformation.getFrameDurationMs(i);
        }
        return this.mLoopDurationMs;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getTargetRenderTimeMs(int frameNumber) {
        long frameDurationMs = 0;
        for (int i = 0; i < frameNumber; i++) {
            frameDurationMs += this.mAnimationInformation.getFrameDurationMs(frameNumber);
        }
        return frameDurationMs;
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public long getTargetRenderTimeForNextFrameMs(long animationTimeMs) {
        long loopDurationMs = getLoopDurationMs();
        long frameDurationMs = 0;
        if (loopDurationMs == 0) {
            return -1L;
        }
        if (!isInfiniteAnimation() && animationTimeMs / getLoopDurationMs() >= this.mAnimationInformation.getLoopCount()) {
            return -1L;
        }
        long j = animationTimeMs % loopDurationMs;
        int frameCount = this.mAnimationInformation.getFrameCount();
        for (int i = 0; i < frameCount && frameDurationMs <= j; i++) {
            frameDurationMs += this.mAnimationInformation.getFrameDurationMs(i);
        }
        return animationTimeMs + (frameDurationMs - j);
    }

    @Override // com.facebook.fresco.animation.frame.FrameScheduler
    public boolean isInfiniteAnimation() {
        return this.mAnimationInformation.getLoopCount() == 0;
    }

    int getFrameNumberWithinLoop(long timeInCurrentLoopMs) {
        int i = 0;
        long frameDurationMs = 0;
        while (true) {
            frameDurationMs += this.mAnimationInformation.getFrameDurationMs(i);
            int i2 = i + 1;
            if (timeInCurrentLoopMs < frameDurationMs) {
                return i;
            }
            i = i2;
        }
    }
}
