package com.facebook.fresco.animation.bitmap.cache;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.imageutils.BitmapUtil;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class KeepLastFrameCache implements BitmapFrameCache {
    private static final int FRAME_NUMBER_UNSET = -1;

    @Nullable
    private BitmapFrameCache.FrameCacheListener mFrameCacheListener;

    @Nullable
    private CloseableReference<Bitmap> mLastBitmapReference;
    private int mLastFrameNumber = -1;

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void onFramePrepared(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType) {
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getCachedFrame(int frameNumber) {
        if (this.mLastFrameNumber != frameNumber) {
            return null;
        }
        return CloseableReference.cloneOrNull(this.mLastBitmapReference);
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getFallbackFrame(int frameNumber) {
        return CloseableReference.cloneOrNull(this.mLastBitmapReference);
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getBitmapToReuseForFrame(int frameNumber, int width, int height) {
        try {
        } finally {
            closeAndResetLastBitmapReference();
        }
        return CloseableReference.cloneOrNull(this.mLastBitmapReference);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000f  */
    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean contains(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            int r0 = r1.mLastFrameNumber     // Catch: java.lang.Throwable -> L12
            if (r2 != r0) goto Lf
            com.facebook.common.references.CloseableReference<android.graphics.Bitmap> r2 = r1.mLastBitmapReference     // Catch: java.lang.Throwable -> L12
            boolean r2 = com.facebook.common.references.CloseableReference.isValid(r2)     // Catch: java.lang.Throwable -> L12
            if (r2 == 0) goto Lf
            r2 = 1
            goto L10
        Lf:
            r2 = 0
        L10:
            monitor-exit(r1)
            return r2
        L12:
            r2 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L12
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.animation.bitmap.cache.KeepLastFrameCache.contains(int):boolean");
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized int getSizeInBytes() {
        CloseableReference<Bitmap> closeableReference;
        closeableReference = this.mLastBitmapReference;
        return closeableReference == null ? 0 : BitmapUtil.getSizeInBytes(closeableReference.get());
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized void clear() {
        closeAndResetLastBitmapReference();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036 A[Catch: all -> 0x003d, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:10:0x001b, B:12:0x0024, B:14:0x0029, B:15:0x002c, B:17:0x0036, B:18:0x0039), top: B:24:0x0003 }] */
    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void onFrameRendered(int r3, com.facebook.common.references.CloseableReference<android.graphics.Bitmap> r4, int r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r4 == 0) goto L1b
            com.facebook.common.references.CloseableReference<android.graphics.Bitmap> r5 = r2.mLastBitmapReference     // Catch: java.lang.Throwable -> L3d
            if (r5 == 0) goto L1b
            java.lang.Object r5 = r4.get()     // Catch: java.lang.Throwable -> L3d
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5     // Catch: java.lang.Throwable -> L3d
            com.facebook.common.references.CloseableReference<android.graphics.Bitmap> r0 = r2.mLastBitmapReference     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r0 = r0.get()     // Catch: java.lang.Throwable -> L3d
            boolean r5 = r5.equals(r0)     // Catch: java.lang.Throwable -> L3d
            if (r5 == 0) goto L1b
            monitor-exit(r2)
            return
        L1b:
            com.facebook.common.references.CloseableReference<android.graphics.Bitmap> r5 = r2.mLastBitmapReference     // Catch: java.lang.Throwable -> L3d
            com.facebook.common.references.CloseableReference.closeSafely(r5)     // Catch: java.lang.Throwable -> L3d
            com.facebook.fresco.animation.bitmap.BitmapFrameCache$FrameCacheListener r5 = r2.mFrameCacheListener     // Catch: java.lang.Throwable -> L3d
            if (r5 == 0) goto L2c
            int r0 = r2.mLastFrameNumber     // Catch: java.lang.Throwable -> L3d
            r1 = -1
            if (r0 == r1) goto L2c
            r5.onFrameEvicted(r2, r0)     // Catch: java.lang.Throwable -> L3d
        L2c:
            com.facebook.common.references.CloseableReference r4 = com.facebook.common.references.CloseableReference.cloneOrNull(r4)     // Catch: java.lang.Throwable -> L3d
            r2.mLastBitmapReference = r4     // Catch: java.lang.Throwable -> L3d
            com.facebook.fresco.animation.bitmap.BitmapFrameCache$FrameCacheListener r4 = r2.mFrameCacheListener     // Catch: java.lang.Throwable -> L3d
            if (r4 == 0) goto L39
            r4.onFrameCached(r2, r3)     // Catch: java.lang.Throwable -> L3d
        L39:
            r2.mLastFrameNumber = r3     // Catch: java.lang.Throwable -> L3d
            monitor-exit(r2)
            return
        L3d:
            r3 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3d
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.animation.bitmap.cache.KeepLastFrameCache.onFrameRendered(int, com.facebook.common.references.CloseableReference, int):void");
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void setFrameCacheListener(BitmapFrameCache.FrameCacheListener frameCacheListener) {
        this.mFrameCacheListener = frameCacheListener;
    }

    private synchronized void closeAndResetLastBitmapReference() {
        int i;
        BitmapFrameCache.FrameCacheListener frameCacheListener = this.mFrameCacheListener;
        if (frameCacheListener != null && (i = this.mLastFrameNumber) != -1) {
            frameCacheListener.onFrameEvicted(this, i);
        }
        CloseableReference.closeSafely(this.mLastBitmapReference);
        this.mLastBitmapReference = null;
        this.mLastFrameNumber = -1;
    }
}
