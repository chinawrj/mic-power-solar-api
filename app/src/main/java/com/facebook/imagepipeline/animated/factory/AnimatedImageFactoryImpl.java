package com.facebook.imagepipeline.animated.factory;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider;
import com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedImageFactoryImpl implements AnimatedImageFactory {

    @Nullable
    static AnimatedImageDecoder sGifAnimatedImageDecoder = loadIfPresent("com.facebook.animated.gif.GifImage");

    @Nullable
    static AnimatedImageDecoder sWebpAnimatedImageDecoder = loadIfPresent("com.facebook.animated.webp.WebPImage");
    private final AnimatedDrawableBackendProvider mAnimatedDrawableBackendProvider;
    private final PlatformBitmapFactory mBitmapFactory;

    @Nullable
    private static AnimatedImageDecoder loadIfPresent(final String className) {
        try {
            return (AnimatedImageDecoder) Class.forName(className).newInstance();
        } catch (Throwable unused) {
            return null;
        }
    }

    public AnimatedImageFactoryImpl(AnimatedDrawableBackendProvider animatedDrawableBackendProvider, PlatformBitmapFactory bitmapFactory) {
        this.mAnimatedDrawableBackendProvider = animatedDrawableBackendProvider;
        this.mBitmapFactory = bitmapFactory;
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedImageFactory
    public CloseableImage decodeGif(final EncodedImage encodedImage, final ImageDecodeOptions options, final Bitmap.Config bitmapConfig) {
        AnimatedImage animatedImageDecodeFromNativeMemory;
        if (sGifAnimatedImageDecoder == null) {
            throw new UnsupportedOperationException("To encode animated gif please add the dependency to the animated-gif module");
        }
        CloseableReference<PooledByteBuffer> byteBufferRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            PooledByteBuffer pooledByteBuffer = byteBufferRef.get();
            if (pooledByteBuffer.getByteBuffer() != null) {
                animatedImageDecodeFromNativeMemory = sGifAnimatedImageDecoder.decodeFromByteBuffer(pooledByteBuffer.getByteBuffer(), options);
            } else {
                animatedImageDecodeFromNativeMemory = sGifAnimatedImageDecoder.decodeFromNativeMemory(pooledByteBuffer.getNativePtr(), pooledByteBuffer.size(), options);
            }
            return getCloseableImage(options, animatedImageDecodeFromNativeMemory, bitmapConfig);
        } finally {
            CloseableReference.closeSafely(byteBufferRef);
        }
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedImageFactory
    public CloseableImage decodeWebP(final EncodedImage encodedImage, final ImageDecodeOptions options, final Bitmap.Config bitmapConfig) {
        AnimatedImage animatedImageDecodeFromNativeMemory;
        if (sWebpAnimatedImageDecoder == null) {
            throw new UnsupportedOperationException("To encode animated webp please add the dependency to the animated-webp module");
        }
        CloseableReference<PooledByteBuffer> byteBufferRef = encodedImage.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            PooledByteBuffer pooledByteBuffer = byteBufferRef.get();
            if (pooledByteBuffer.getByteBuffer() != null) {
                animatedImageDecodeFromNativeMemory = sWebpAnimatedImageDecoder.decodeFromByteBuffer(pooledByteBuffer.getByteBuffer(), options);
            } else {
                animatedImageDecodeFromNativeMemory = sWebpAnimatedImageDecoder.decodeFromNativeMemory(pooledByteBuffer.getNativePtr(), pooledByteBuffer.size(), options);
            }
            return getCloseableImage(options, animatedImageDecodeFromNativeMemory, bitmapConfig);
        } finally {
            CloseableReference.closeSafely(byteBufferRef);
        }
    }

    private CloseableImage getCloseableImage(ImageDecodeOptions options, AnimatedImage image, Bitmap.Config bitmapConfig) throws Throwable {
        List<CloseableReference<Bitmap>> listDecodeAllFrames;
        CloseableReference<Bitmap> closeableReferenceCreatePreviewBitmap = null;
        try {
            int frameCount = options.useLastFrameForPreview ? image.getFrameCount() - 1 : 0;
            if (options.forceStaticImage) {
                CloseableStaticBitmap closeableStaticBitmap = new CloseableStaticBitmap(createPreviewBitmap(image, bitmapConfig, frameCount), ImmutableQualityInfo.FULL_QUALITY, 0);
                CloseableReference.closeSafely((CloseableReference<?>) null);
                CloseableReference.closeSafely((Iterable<? extends CloseableReference<?>>) null);
                return closeableStaticBitmap;
            }
            if (options.decodeAllFrames) {
                listDecodeAllFrames = decodeAllFrames(image, bitmapConfig);
                try {
                    closeableReferenceCreatePreviewBitmap = CloseableReference.cloneOrNull(listDecodeAllFrames.get(frameCount));
                } catch (Throwable th) {
                    th = th;
                    CloseableReference.closeSafely(closeableReferenceCreatePreviewBitmap);
                    CloseableReference.closeSafely(listDecodeAllFrames);
                    throw th;
                }
            } else {
                listDecodeAllFrames = null;
            }
            if (options.decodePreviewFrame && closeableReferenceCreatePreviewBitmap == null) {
                closeableReferenceCreatePreviewBitmap = createPreviewBitmap(image, bitmapConfig, frameCount);
            }
            CloseableAnimatedImage closeableAnimatedImage = new CloseableAnimatedImage(AnimatedImageResult.newBuilder(image).setPreviewBitmap(closeableReferenceCreatePreviewBitmap).setFrameForPreview(frameCount).setDecodedFrames(listDecodeAllFrames).setBitmapTransformation(options.bitmapTransformation).build());
            CloseableReference.closeSafely(closeableReferenceCreatePreviewBitmap);
            CloseableReference.closeSafely(listDecodeAllFrames);
            return closeableAnimatedImage;
        } catch (Throwable th2) {
            th = th2;
            listDecodeAllFrames = null;
        }
    }

    private CloseableReference<Bitmap> createPreviewBitmap(AnimatedImage image, Bitmap.Config bitmapConfig, int frameForPreview) {
        CloseableReference<Bitmap> closeableReferenceCreateBitmap = createBitmap(image.getWidth(), image.getHeight(), bitmapConfig);
        new AnimatedImageCompositor(this.mAnimatedDrawableBackendProvider.get(AnimatedImageResult.forAnimatedImage(image), null), new AnimatedImageCompositor.Callback() { // from class: com.facebook.imagepipeline.animated.factory.AnimatedImageFactoryImpl.1
            @Override // com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.Callback
            @Nullable
            public CloseableReference<Bitmap> getCachedBitmap(int frameNumber) {
                return null;
            }

            @Override // com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.Callback
            public void onIntermediateResult(int frameNumber, Bitmap bitmap) {
            }
        }).renderFrame(frameForPreview, closeableReferenceCreateBitmap.get());
        return closeableReferenceCreateBitmap;
    }

    private List<CloseableReference<Bitmap>> decodeAllFrames(AnimatedImage image, Bitmap.Config bitmapConfig) {
        AnimatedDrawableBackend animatedDrawableBackend = this.mAnimatedDrawableBackendProvider.get(AnimatedImageResult.forAnimatedImage(image), null);
        final ArrayList arrayList = new ArrayList(animatedDrawableBackend.getFrameCount());
        AnimatedImageCompositor animatedImageCompositor = new AnimatedImageCompositor(animatedDrawableBackend, new AnimatedImageCompositor.Callback() { // from class: com.facebook.imagepipeline.animated.factory.AnimatedImageFactoryImpl.2
            @Override // com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.Callback
            public void onIntermediateResult(int frameNumber, Bitmap bitmap) {
            }

            @Override // com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.Callback
            @Nullable
            public CloseableReference<Bitmap> getCachedBitmap(int frameNumber) {
                return CloseableReference.cloneOrNull((CloseableReference) arrayList.get(frameNumber));
            }
        });
        for (int i = 0; i < animatedDrawableBackend.getFrameCount(); i++) {
            CloseableReference<Bitmap> closeableReferenceCreateBitmap = createBitmap(animatedDrawableBackend.getWidth(), animatedDrawableBackend.getHeight(), bitmapConfig);
            animatedImageCompositor.renderFrame(i, closeableReferenceCreateBitmap.get());
            arrayList.add(closeableReferenceCreateBitmap);
        }
        return arrayList;
    }

    private CloseableReference<Bitmap> createBitmap(int width, int height, Bitmap.Config bitmapConfig) {
        CloseableReference<Bitmap> closeableReferenceCreateBitmapInternal = this.mBitmapFactory.createBitmapInternal(width, height, bitmapConfig);
        closeableReferenceCreateBitmapInternal.get().eraseColor(0);
        closeableReferenceCreateBitmapInternal.get().setHasAlpha(true);
        return closeableReferenceCreateBitmapInternal;
    }
}
