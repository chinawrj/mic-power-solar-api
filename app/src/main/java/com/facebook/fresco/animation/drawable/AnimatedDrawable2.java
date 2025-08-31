package com.facebook.fresco.animation.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.logging.FLog;
import com.facebook.drawable.base.DrawableWithCaches;
import com.facebook.drawee.drawable.DrawableProperties;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.frame.DropFramesFrameScheduler;
import com.facebook.fresco.animation.frame.FrameScheduler;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedDrawable2 extends Drawable implements Animatable, DrawableWithCaches {
    private static final int DEFAULT_FRAME_SCHEDULING_DELAY_MS = 8;
    private static final int DEFAULT_FRAME_SCHEDULING_OFFSET_MS = 0;

    @Nullable
    private AnimationBackend mAnimationBackend;
    private volatile AnimationListener mAnimationListener;

    @Nullable
    private volatile DrawListener mDrawListener;

    @Nullable
    private DrawableProperties mDrawableProperties;
    private int mDroppedFrames;
    private long mExpectedRenderTimeMs;

    @Nullable
    private FrameScheduler mFrameScheduler;
    private long mFrameSchedulingDelayMs;
    private long mFrameSchedulingOffsetMs;
    private final Runnable mInvalidateRunnable;
    private volatile boolean mIsRunning;
    private int mLastDrawnFrameNumber;
    private long mLastFrameAnimationTimeMs;
    private int mPausedLastDrawnFrameNumber;
    private long mPausedLastFrameAnimationTimeMsDifference;
    private long mPausedStartTimeMsDifference;
    private long mStartTimeMs;
    private static final Class<?> TAG = AnimatedDrawable2.class;
    private static final AnimationListener NO_OP_LISTENER = new BaseAnimationListener();

    public interface DrawListener {
        void onDraw(AnimatedDrawable2 animatedDrawable, FrameScheduler frameScheduler, int frameNumberToDraw, boolean frameDrawn, boolean isAnimationRunning, long animationStartTimeMs, long animationTimeMs, long lastFrameAnimationTimeMs, long actualRenderTimeStartMs, long actualRenderTimeEndMs, long startRenderTimeForNextFrameMs, long scheduledRenderTimeForNextFrameMs);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public AnimatedDrawable2() {
        this(null);
    }

    public AnimatedDrawable2(@Nullable AnimationBackend animationBackend) {
        this.mFrameSchedulingDelayMs = 8L;
        this.mFrameSchedulingOffsetMs = 0L;
        this.mAnimationListener = NO_OP_LISTENER;
        this.mDrawListener = null;
        this.mInvalidateRunnable = new Runnable() { // from class: com.facebook.fresco.animation.drawable.AnimatedDrawable2.1
            @Override // java.lang.Runnable
            public void run() {
                AnimatedDrawable2 animatedDrawable2 = AnimatedDrawable2.this;
                animatedDrawable2.unscheduleSelf(animatedDrawable2.mInvalidateRunnable);
                AnimatedDrawable2.this.invalidateSelf();
            }
        };
        this.mAnimationBackend = animationBackend;
        this.mFrameScheduler = createSchedulerForBackendAndDelayMethod(animationBackend);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend == null) {
            return super.getIntrinsicWidth();
        }
        return animationBackend.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend == null) {
            return super.getIntrinsicHeight();
        }
        return animationBackend.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        AnimationBackend animationBackend;
        if (this.mIsRunning || (animationBackend = this.mAnimationBackend) == null || animationBackend.getFrameCount() <= 1) {
            return;
        }
        this.mIsRunning = true;
        long jNow = now();
        long j = jNow - this.mPausedStartTimeMsDifference;
        this.mStartTimeMs = j;
        this.mExpectedRenderTimeMs = j;
        this.mLastFrameAnimationTimeMs = jNow - this.mPausedLastFrameAnimationTimeMsDifference;
        this.mLastDrawnFrameNumber = this.mPausedLastDrawnFrameNumber;
        invalidateSelf();
        this.mAnimationListener.onAnimationStart(this);
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (this.mIsRunning) {
            long jNow = now();
            this.mPausedStartTimeMsDifference = jNow - this.mStartTimeMs;
            this.mPausedLastFrameAnimationTimeMsDifference = jNow - this.mLastFrameAnimationTimeMs;
            this.mPausedLastDrawnFrameNumber = this.mLastDrawnFrameNumber;
            this.mIsRunning = false;
            this.mStartTimeMs = 0L;
            this.mExpectedRenderTimeMs = 0L;
            this.mLastFrameAnimationTimeMs = -1L;
            this.mLastDrawnFrameNumber = -1;
            unscheduleSelf(this.mInvalidateRunnable);
            this.mAnimationListener.onAnimationStop(this);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mIsRunning;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend != null) {
            animationBackend.setBounds(bounds);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        long j;
        long j2;
        AnimatedDrawable2 animatedDrawable2;
        long j3;
        if (this.mAnimationBackend == null || this.mFrameScheduler == null) {
            return;
        }
        long jNow = now();
        long jMax = this.mIsRunning ? (jNow - this.mStartTimeMs) + this.mFrameSchedulingOffsetMs : Math.max(this.mLastFrameAnimationTimeMs, 0L);
        int frameNumberToRender = this.mFrameScheduler.getFrameNumberToRender(jMax, this.mLastFrameAnimationTimeMs);
        if (frameNumberToRender == -1) {
            frameNumberToRender = this.mAnimationBackend.getFrameCount() - 1;
            this.mAnimationListener.onAnimationStop(this);
            this.mIsRunning = false;
        } else if (frameNumberToRender == 0 && this.mLastDrawnFrameNumber != -1 && jNow >= this.mExpectedRenderTimeMs) {
            this.mAnimationListener.onAnimationRepeat(this);
        }
        int i = frameNumberToRender;
        boolean zDrawFrame = this.mAnimationBackend.drawFrame(this, canvas, i);
        if (zDrawFrame) {
            this.mAnimationListener.onAnimationFrame(this, i);
            this.mLastDrawnFrameNumber = i;
        }
        if (!zDrawFrame) {
            onFrameDropped();
        }
        long jNow2 = now();
        if (this.mIsRunning) {
            long targetRenderTimeForNextFrameMs = this.mFrameScheduler.getTargetRenderTimeForNextFrameMs(jNow2 - this.mStartTimeMs);
            if (targetRenderTimeForNextFrameMs != -1) {
                long j4 = this.mFrameSchedulingDelayMs + targetRenderTimeForNextFrameMs;
                scheduleNextFrame(j4);
                j2 = j4;
            } else {
                this.mAnimationListener.onAnimationStop(this);
                this.mIsRunning = false;
                j2 = -1;
            }
            j = targetRenderTimeForNextFrameMs;
        } else {
            j = -1;
            j2 = -1;
        }
        DrawListener drawListener = this.mDrawListener;
        if (drawListener != null) {
            drawListener.onDraw(this, this.mFrameScheduler, i, zDrawFrame, this.mIsRunning, this.mStartTimeMs, jMax, this.mLastFrameAnimationTimeMs, jNow, jNow2, j, j2);
            animatedDrawable2 = this;
            j3 = jMax;
        } else {
            animatedDrawable2 = this;
            j3 = jMax;
        }
        animatedDrawable2.mLastFrameAnimationTimeMs = j3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        if (this.mDrawableProperties == null) {
            this.mDrawableProperties = new DrawableProperties();
        }
        this.mDrawableProperties.setAlpha(alpha);
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend != null) {
            animationBackend.setAlpha(alpha);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        if (this.mDrawableProperties == null) {
            this.mDrawableProperties = new DrawableProperties();
        }
        this.mDrawableProperties.setColorFilter(colorFilter);
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend != null) {
            animationBackend.setColorFilter(colorFilter);
        }
    }

    public void setAnimationBackend(@Nullable AnimationBackend animationBackend) {
        this.mAnimationBackend = animationBackend;
        if (animationBackend != null) {
            this.mFrameScheduler = new DropFramesFrameScheduler(animationBackend);
            this.mAnimationBackend.setBounds(getBounds());
            DrawableProperties drawableProperties = this.mDrawableProperties;
            if (drawableProperties != null) {
                drawableProperties.applyTo(this);
            }
        }
        this.mFrameScheduler = createSchedulerForBackendAndDelayMethod(this.mAnimationBackend);
        stop();
    }

    @Nullable
    public AnimationBackend getAnimationBackend() {
        return this.mAnimationBackend;
    }

    public long getDroppedFrames() {
        return this.mDroppedFrames;
    }

    public long getStartTimeMs() {
        return this.mStartTimeMs;
    }

    public boolean isInfiniteAnimation() {
        FrameScheduler frameScheduler = this.mFrameScheduler;
        return frameScheduler != null && frameScheduler.isInfiniteAnimation();
    }

    public void jumpToFrame(int targetFrameNumber) {
        FrameScheduler frameScheduler;
        if (this.mAnimationBackend == null || (frameScheduler = this.mFrameScheduler) == null) {
            return;
        }
        this.mLastFrameAnimationTimeMs = frameScheduler.getTargetRenderTimeMs(targetFrameNumber);
        long jNow = now() - this.mLastFrameAnimationTimeMs;
        this.mStartTimeMs = jNow;
        this.mExpectedRenderTimeMs = jNow;
        invalidateSelf();
    }

    public long getLoopDurationMs() {
        if (this.mAnimationBackend == null) {
            return 0L;
        }
        FrameScheduler frameScheduler = this.mFrameScheduler;
        if (frameScheduler != null) {
            return frameScheduler.getLoopDurationMs();
        }
        int frameDurationMs = 0;
        for (int i = 0; i < this.mAnimationBackend.getFrameCount(); i++) {
            frameDurationMs += this.mAnimationBackend.getFrameDurationMs(i);
        }
        return frameDurationMs;
    }

    public int getFrameCount() {
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend == null) {
            return 0;
        }
        return animationBackend.getFrameCount();
    }

    public int getLoopCount() {
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend == null) {
            return 0;
        }
        return animationBackend.getLoopCount();
    }

    public void setFrameSchedulingDelayMs(long frameSchedulingDelayMs) {
        this.mFrameSchedulingDelayMs = frameSchedulingDelayMs;
    }

    public void setFrameSchedulingOffsetMs(long frameSchedulingOffsetMs) {
        this.mFrameSchedulingOffsetMs = frameSchedulingOffsetMs;
    }

    public void setAnimationListener(@Nullable AnimationListener animationListener) {
        if (animationListener == null) {
            animationListener = NO_OP_LISTENER;
        }
        this.mAnimationListener = animationListener;
    }

    public void setDrawListener(@Nullable DrawListener drawListener) {
        this.mDrawListener = drawListener;
    }

    private void scheduleNextFrame(long targetAnimationTimeMs) {
        long j = this.mStartTimeMs + targetAnimationTimeMs;
        this.mExpectedRenderTimeMs = j;
        scheduleSelf(this.mInvalidateRunnable, j);
    }

    private void onFrameDropped() {
        this.mDroppedFrames++;
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "Dropped a frame. Count: %s", Integer.valueOf(this.mDroppedFrames));
        }
    }

    private long now() {
        return SystemClock.uptimeMillis();
    }

    @Nullable
    private static FrameScheduler createSchedulerForBackendAndDelayMethod(@Nullable AnimationBackend animationBackend) {
        if (animationBackend == null) {
            return null;
        }
        return new DropFramesFrameScheduler(animationBackend);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int level) {
        if (this.mIsRunning) {
            return false;
        }
        long j = level;
        if (this.mLastFrameAnimationTimeMs == j) {
            return false;
        }
        this.mLastFrameAnimationTimeMs = j;
        invalidateSelf();
        return true;
    }

    @Override // com.facebook.drawable.base.DrawableWithCaches
    public void dropCaches() {
        AnimationBackend animationBackend = this.mAnimationBackend;
        if (animationBackend != null) {
            animationBackend.clear();
        }
    }
}
