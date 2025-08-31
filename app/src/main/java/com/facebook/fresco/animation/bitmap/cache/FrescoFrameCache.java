package com.facebook.fresco.animation.bitmap.cache;

import android.graphics.Bitmap;
import android.util.SparseArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.imagepipeline.animated.impl.AnimatedFrameCache;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imageutils.BitmapUtil;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class FrescoFrameCache implements BitmapFrameCache {
    private static final Class<?> TAG = FrescoFrameCache.class;
    private final AnimatedFrameCache mAnimatedFrameCache;
    private final boolean mEnableBitmapReusing;

    @Nullable
    private CloseableReference<CloseableImage> mLastRenderedItem;
    private final SparseArray<CloseableReference<CloseableImage>> mPreparedPendingFrames = new SparseArray<>();

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public void setFrameCacheListener(BitmapFrameCache.FrameCacheListener frameCacheListener) {
    }

    public FrescoFrameCache(AnimatedFrameCache animatedFrameCache, boolean enableBitmapReusing) {
        this.mAnimatedFrameCache = animatedFrameCache;
        this.mEnableBitmapReusing = enableBitmapReusing;
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getCachedFrame(int frameNumber) {
        return convertToBitmapReferenceAndClose(this.mAnimatedFrameCache.get(frameNumber));
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getFallbackFrame(int frameNumber) {
        return convertToBitmapReferenceAndClose(CloseableReference.cloneOrNull(this.mLastRenderedItem));
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    @Nullable
    public synchronized CloseableReference<Bitmap> getBitmapToReuseForFrame(int frameNumber, int width, int height) {
        if (!this.mEnableBitmapReusing) {
            return null;
        }
        return convertToBitmapReferenceAndClose(this.mAnimatedFrameCache.getForReuse());
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized boolean contains(int frameNumber) {
        return this.mAnimatedFrameCache.contains(frameNumber);
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized int getSizeInBytes() {
        return getBitmapSizeBytes(this.mLastRenderedItem) + getPreparedPendingFramesSizeBytes();
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized void clear() {
        CloseableReference.closeSafely(this.mLastRenderedItem);
        this.mLastRenderedItem = null;
        for (int i = 0; i < this.mPreparedPendingFrames.size(); i++) {
            CloseableReference.closeSafely(this.mPreparedPendingFrames.valueAt(i));
        }
        this.mPreparedPendingFrames.clear();
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized void onFrameRendered(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType) {
        CloseableReference<CloseableImage> closeableReferenceCreateImageReference;
        Preconditions.checkNotNull(bitmapReference);
        removePreparedReference(frameNumber);
        try {
            closeableReferenceCreateImageReference = createImageReference(bitmapReference);
            if (closeableReferenceCreateImageReference != null) {
                try {
                    CloseableReference.closeSafely(this.mLastRenderedItem);
                    this.mLastRenderedItem = this.mAnimatedFrameCache.cache(frameNumber, closeableReferenceCreateImageReference);
                } catch (Throwable th) {
                    th = th;
                    CloseableReference.closeSafely(closeableReferenceCreateImageReference);
                    throw th;
                }
            }
            CloseableReference.closeSafely(closeableReferenceCreateImageReference);
        } catch (Throwable th2) {
            th = th2;
            closeableReferenceCreateImageReference = null;
        }
    }

    @Override // com.facebook.fresco.animation.bitmap.BitmapFrameCache
    public synchronized void onFramePrepared(int frameNumber, CloseableReference<Bitmap> bitmapReference, int frameType) {
        CloseableReference<CloseableImage> closeableReferenceCreateImageReference;
        Preconditions.checkNotNull(bitmapReference);
        try {
            closeableReferenceCreateImageReference = createImageReference(bitmapReference);
            if (closeableReferenceCreateImageReference != null) {
                try {
                    CloseableReference<CloseableImage> closeableReferenceCache = this.mAnimatedFrameCache.cache(frameNumber, closeableReferenceCreateImageReference);
                    if (CloseableReference.isValid(closeableReferenceCache)) {
                        CloseableReference.closeSafely(this.mPreparedPendingFrames.get(frameNumber));
                        this.mPreparedPendingFrames.put(frameNumber, closeableReferenceCache);
                        FLog.v(TAG, "cachePreparedFrame(%d) cached. Pending frames: %s", Integer.valueOf(frameNumber), this.mPreparedPendingFrames);
                    }
                    CloseableReference.closeSafely(closeableReferenceCreateImageReference);
                    return;
                } catch (Throwable th) {
                    th = th;
                    CloseableReference.closeSafely(closeableReferenceCreateImageReference);
                    throw th;
                }
            }
            CloseableReference.closeSafely(closeableReferenceCreateImageReference);
        } catch (Throwable th2) {
            th = th2;
            closeableReferenceCreateImageReference = null;
        }
    }

    private synchronized int getPreparedPendingFramesSizeBytes() {
        int bitmapSizeBytes;
        bitmapSizeBytes = 0;
        for (int i = 0; i < this.mPreparedPendingFrames.size(); i++) {
            bitmapSizeBytes += getBitmapSizeBytes(this.mPreparedPendingFrames.valueAt(i));
        }
        return bitmapSizeBytes;
    }

    private synchronized void removePreparedReference(int frameNumber) {
        CloseableReference<CloseableImage> closeableReference = this.mPreparedPendingFrames.get(frameNumber);
        if (closeableReference != null) {
            this.mPreparedPendingFrames.delete(frameNumber);
            CloseableReference.closeSafely(closeableReference);
            FLog.v(TAG, "removePreparedReference(%d) removed. Pending frames: %s", Integer.valueOf(frameNumber), this.mPreparedPendingFrames);
        }
    }

    @Nullable
    static CloseableReference<Bitmap> convertToBitmapReferenceAndClose(@Nullable final CloseableReference<CloseableImage> closeableImage) {
        CloseableStaticBitmap closeableStaticBitmap;
        try {
            if (CloseableReference.isValid(closeableImage) && (closeableImage.get() instanceof CloseableStaticBitmap) && (closeableStaticBitmap = (CloseableStaticBitmap) closeableImage.get()) != null) {
                return closeableStaticBitmap.cloneUnderlyingBitmapReference();
            }
            CloseableReference.closeSafely(closeableImage);
            return null;
        } finally {
            CloseableReference.closeSafely(closeableImage);
        }
    }

    private static int getBitmapSizeBytes(@Nullable CloseableReference<CloseableImage> imageReference) {
        if (CloseableReference.isValid(imageReference)) {
            return getBitmapSizeBytes(imageReference.get());
        }
        return 0;
    }

    private static int getBitmapSizeBytes(@Nullable CloseableImage image) {
        if (image instanceof CloseableBitmap) {
            return BitmapUtil.getSizeInBytes(((CloseableBitmap) image).getUnderlyingBitmap());
        }
        return 0;
    }

    @Nullable
    private static CloseableReference<CloseableImage> createImageReference(CloseableReference<Bitmap> bitmapReference) {
        return CloseableReference.of(new CloseableStaticBitmap(bitmapReference, ImmutableQualityInfo.FULL_QUALITY, 0));
    }
}
