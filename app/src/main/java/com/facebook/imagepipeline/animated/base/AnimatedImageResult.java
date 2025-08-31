package com.facebook.imagepipeline.animated.base;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedImageResult {

    @Nullable
    private BitmapTransformation mBitmapTransformation;

    @Nullable
    private List<CloseableReference<Bitmap>> mDecodedFrames;
    private final int mFrameForPreview;
    private final AnimatedImage mImage;

    @Nullable
    private CloseableReference<Bitmap> mPreviewBitmap;

    AnimatedImageResult(AnimatedImageResultBuilder builder) {
        this.mImage = (AnimatedImage) Preconditions.checkNotNull(builder.getImage());
        this.mFrameForPreview = builder.getFrameForPreview();
        this.mPreviewBitmap = builder.getPreviewBitmap();
        this.mDecodedFrames = builder.getDecodedFrames();
        this.mBitmapTransformation = builder.getBitmapTransformation();
    }

    private AnimatedImageResult(AnimatedImage image) {
        this.mImage = (AnimatedImage) Preconditions.checkNotNull(image);
        this.mFrameForPreview = 0;
    }

    public static AnimatedImageResult forAnimatedImage(AnimatedImage image) {
        return new AnimatedImageResult(image);
    }

    public static AnimatedImageResultBuilder newBuilder(AnimatedImage image) {
        return new AnimatedImageResultBuilder(image);
    }

    public AnimatedImage getImage() {
        return this.mImage;
    }

    public int getFrameForPreview() {
        return this.mFrameForPreview;
    }

    @Nullable
    public synchronized CloseableReference<Bitmap> getDecodedFrame(int index) {
        List<CloseableReference<Bitmap>> list = this.mDecodedFrames;
        if (list == null) {
            return null;
        }
        return CloseableReference.cloneOrNull(list.get(index));
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean hasDecodedFrame(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.List<com.facebook.common.references.CloseableReference<android.graphics.Bitmap>> r0 = r1.mDecodedFrames     // Catch: java.lang.Throwable -> L10
            if (r0 == 0) goto Ld
            java.lang.Object r2 = r0.get(r2)     // Catch: java.lang.Throwable -> L10
            if (r2 == 0) goto Ld
            r2 = 1
            goto Le
        Ld:
            r2 = 0
        Le:
            monitor-exit(r1)
            return r2
        L10:
            r2 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L10
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.animated.base.AnimatedImageResult.hasDecodedFrame(int):boolean");
    }

    @Nullable
    public BitmapTransformation getBitmapTransformation() {
        return this.mBitmapTransformation;
    }

    @Nullable
    public synchronized CloseableReference<Bitmap> getPreviewBitmap() {
        return CloseableReference.cloneOrNull(this.mPreviewBitmap);
    }

    public synchronized void dispose() {
        CloseableReference.closeSafely(this.mPreviewBitmap);
        this.mPreviewBitmap = null;
        CloseableReference.closeSafely(this.mDecodedFrames);
        this.mDecodedFrames = null;
    }
}
