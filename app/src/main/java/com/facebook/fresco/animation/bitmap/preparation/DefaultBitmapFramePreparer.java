package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class DefaultBitmapFramePreparer implements BitmapFramePreparer {
    private static final Class<?> TAG = DefaultBitmapFramePreparer.class;
    private final Bitmap.Config mBitmapConfig;
    private final BitmapFrameRenderer mBitmapFrameRenderer;
    private final ExecutorService mExecutorService;
    private final SparseArray<Runnable> mPendingFrameDecodeJobs = new SparseArray<>();
    private final PlatformBitmapFactory mPlatformBitmapFactory;

    public DefaultBitmapFramePreparer(PlatformBitmapFactory platformBitmapFactory, BitmapFrameRenderer bitmapFrameRenderer, Bitmap.Config bitmapConfig, ExecutorService executorService) {
        this.mPlatformBitmapFactory = platformBitmapFactory;
        this.mBitmapFrameRenderer = bitmapFrameRenderer;
        this.mBitmapConfig = bitmapConfig;
        this.mExecutorService = executorService;
    }

    @Override // com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparer
    public boolean prepareFrame(BitmapFrameCache bitmapFrameCache, AnimationBackend animationBackend, int frameNumber) {
        int uniqueId = getUniqueId(animationBackend, frameNumber);
        synchronized (this.mPendingFrameDecodeJobs) {
            if (this.mPendingFrameDecodeJobs.get(uniqueId) != null) {
                FLog.v(TAG, "Already scheduled decode job for frame %d", Integer.valueOf(frameNumber));
                return true;
            }
            if (bitmapFrameCache.contains(frameNumber)) {
                FLog.v(TAG, "Frame %d is cached already.", Integer.valueOf(frameNumber));
                return true;
            }
            FrameDecodeRunnable frameDecodeRunnable = new FrameDecodeRunnable(animationBackend, bitmapFrameCache, frameNumber, uniqueId);
            this.mPendingFrameDecodeJobs.put(uniqueId, frameDecodeRunnable);
            this.mExecutorService.execute(frameDecodeRunnable);
            return true;
        }
    }

    private static int getUniqueId(AnimationBackend backend, int frameNumber) {
        return (backend.hashCode() * 31) + frameNumber;
    }

    private class FrameDecodeRunnable implements Runnable {
        private final AnimationBackend mAnimationBackend;
        private final BitmapFrameCache mBitmapFrameCache;
        private final int mFrameNumber;
        private final int mHashCode;

        public FrameDecodeRunnable(AnimationBackend animationBackend, BitmapFrameCache bitmapFrameCache, int frameNumber, int hashCode) {
            this.mAnimationBackend = animationBackend;
            this.mBitmapFrameCache = bitmapFrameCache;
            this.mFrameNumber = frameNumber;
            this.mHashCode = hashCode;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.mBitmapFrameCache.contains(this.mFrameNumber)) {
                    FLog.v((Class<?>) DefaultBitmapFramePreparer.TAG, "Frame %d is cached already.", Integer.valueOf(this.mFrameNumber));
                    synchronized (DefaultBitmapFramePreparer.this.mPendingFrameDecodeJobs) {
                        DefaultBitmapFramePreparer.this.mPendingFrameDecodeJobs.remove(this.mHashCode);
                    }
                    return;
                }
                if (prepareFrameAndCache(this.mFrameNumber, 1)) {
                    FLog.v((Class<?>) DefaultBitmapFramePreparer.TAG, "Prepared frame frame %d.", Integer.valueOf(this.mFrameNumber));
                } else {
                    FLog.e((Class<?>) DefaultBitmapFramePreparer.TAG, "Could not prepare frame %d.", Integer.valueOf(this.mFrameNumber));
                }
                synchronized (DefaultBitmapFramePreparer.this.mPendingFrameDecodeJobs) {
                    DefaultBitmapFramePreparer.this.mPendingFrameDecodeJobs.remove(this.mHashCode);
                }
            } catch (Throwable th) {
                synchronized (DefaultBitmapFramePreparer.this.mPendingFrameDecodeJobs) {
                    DefaultBitmapFramePreparer.this.mPendingFrameDecodeJobs.remove(this.mHashCode);
                    throw th;
                }
            }
        }

        private boolean prepareFrameAndCache(int frameNumber, int frameType) {
            CloseableReference<Bitmap> bitmapToReuseForFrame;
            int i = 2;
            try {
                if (frameType == 1) {
                    bitmapToReuseForFrame = this.mBitmapFrameCache.getBitmapToReuseForFrame(frameNumber, this.mAnimationBackend.getIntrinsicWidth(), this.mAnimationBackend.getIntrinsicHeight());
                } else {
                    if (frameType != 2) {
                        return false;
                    }
                    bitmapToReuseForFrame = DefaultBitmapFramePreparer.this.mPlatformBitmapFactory.createBitmap(this.mAnimationBackend.getIntrinsicWidth(), this.mAnimationBackend.getIntrinsicHeight(), DefaultBitmapFramePreparer.this.mBitmapConfig);
                    i = -1;
                }
                boolean zRenderFrameAndCache = renderFrameAndCache(frameNumber, bitmapToReuseForFrame, frameType);
                CloseableReference.closeSafely(bitmapToReuseForFrame);
                return (zRenderFrameAndCache || i == -1) ? zRenderFrameAndCache : prepareFrameAndCache(frameNumber, i);
            } catch (RuntimeException e) {
                FLog.w((Class<?>) DefaultBitmapFramePreparer.TAG, "Failed to create frame bitmap", e);
                return false;
            } finally {
                CloseableReference.closeSafely((CloseableReference<?>) null);
            }
        }

        private boolean renderFrameAndCache(int frameNumber, @Nullable CloseableReference<Bitmap> bitmapReference, int frameType) {
            if (!CloseableReference.isValid(bitmapReference) || !DefaultBitmapFramePreparer.this.mBitmapFrameRenderer.renderFrame(frameNumber, bitmapReference.get())) {
                return false;
            }
            FLog.v((Class<?>) DefaultBitmapFramePreparer.TAG, "Frame %d ready.", Integer.valueOf(this.mFrameNumber));
            synchronized (DefaultBitmapFramePreparer.this.mPendingFrameDecodeJobs) {
                this.mBitmapFrameCache.onFramePrepared(this.mFrameNumber, bitmapReference, frameType);
            }
            return true;
        }
    }
}
