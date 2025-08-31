package com.facebook.animated.webp;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.factory.AnimatedImageDecoder;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.nativecode.StaticWebpNativeLoader;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class WebPImage implements AnimatedImage, AnimatedImageDecoder {

    @Nullable
    private Bitmap.Config mDecodeBitmapConfig = null;
    private long mNativeContext;

    private static native WebPImage nativeCreateFromDirectByteBuffer(ByteBuffer buffer);

    private static native WebPImage nativeCreateFromNativeMemory(long nativePtr, int sizeInBytes);

    private native void nativeDispose();

    private native void nativeFinalize();

    private native int nativeGetDuration();

    private native WebPFrame nativeGetFrame(int frameNumber);

    private native int nativeGetFrameCount();

    private native int[] nativeGetFrameDurations();

    private native int nativeGetHeight();

    private native int nativeGetLoopCount();

    private native int nativeGetSizeInBytes();

    private native int nativeGetWidth();

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public boolean doesRenderSupportScaling() {
        return true;
    }

    public WebPImage() {
    }

    WebPImage(long nativeContext) {
        this.mNativeContext = nativeContext;
    }

    protected void finalize() {
        nativeFinalize();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public void dispose() {
        nativeDispose();
    }

    public static WebPImage createFromByteArray(byte[] source, @Nullable ImageDecodeOptions options) {
        StaticWebpNativeLoader.ensure();
        Preconditions.checkNotNull(source);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(source.length);
        byteBufferAllocateDirect.put(source);
        byteBufferAllocateDirect.rewind();
        WebPImage webPImageNativeCreateFromDirectByteBuffer = nativeCreateFromDirectByteBuffer(byteBufferAllocateDirect);
        if (options != null) {
            webPImageNativeCreateFromDirectByteBuffer.mDecodeBitmapConfig = options.animatedBitmapConfig;
        }
        return webPImageNativeCreateFromDirectByteBuffer;
    }

    public static WebPImage createFromByteBuffer(ByteBuffer byteBuffer, @Nullable ImageDecodeOptions options) {
        StaticWebpNativeLoader.ensure();
        byteBuffer.rewind();
        WebPImage webPImageNativeCreateFromDirectByteBuffer = nativeCreateFromDirectByteBuffer(byteBuffer);
        if (options != null) {
            webPImageNativeCreateFromDirectByteBuffer.mDecodeBitmapConfig = options.animatedBitmapConfig;
        }
        return webPImageNativeCreateFromDirectByteBuffer;
    }

    public static WebPImage createFromNativeMemory(long nativePtr, int sizeInBytes, @Nullable ImageDecodeOptions options) {
        StaticWebpNativeLoader.ensure();
        Preconditions.checkArgument(Boolean.valueOf(nativePtr != 0));
        WebPImage webPImageNativeCreateFromNativeMemory = nativeCreateFromNativeMemory(nativePtr, sizeInBytes);
        if (options != null) {
            webPImageNativeCreateFromNativeMemory.mDecodeBitmapConfig = options.animatedBitmapConfig;
        }
        return webPImageNativeCreateFromNativeMemory;
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedImageDecoder
    public AnimatedImage decodeFromNativeMemory(long nativePtr, int sizeInBytes, ImageDecodeOptions options) {
        return createFromNativeMemory(nativePtr, sizeInBytes, options);
    }

    @Override // com.facebook.imagepipeline.animated.factory.AnimatedImageDecoder
    public AnimatedImage decodeFromByteBuffer(ByteBuffer byteBuffer, ImageDecodeOptions options) {
        return createFromByteBuffer(byteBuffer, options);
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getWidth() {
        return nativeGetWidth();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getHeight() {
        return nativeGetHeight();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getFrameCount() {
        return nativeGetFrameCount();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getDuration() {
        return nativeGetDuration();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int[] getFrameDurations() {
        return nativeGetFrameDurations();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getLoopCount() {
        return nativeGetLoopCount();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public WebPFrame getFrame(int frameNumber) {
        return nativeGetFrame(frameNumber);
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public int getSizeInBytes() {
        return nativeGetSizeInBytes();
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    public AnimatedDrawableFrameInfo getFrameInfo(int frameNumber) {
        WebPFrame frame = getFrame(frameNumber);
        try {
            return new AnimatedDrawableFrameInfo(frameNumber, frame.getXOffset(), frame.getYOffset(), frame.getWidth(), frame.getHeight(), frame.isBlendWithPreviousFrame() ? AnimatedDrawableFrameInfo.BlendOperation.BLEND_WITH_PREVIOUS : AnimatedDrawableFrameInfo.BlendOperation.NO_BLEND, frame.shouldDisposeToBackgroundColor() ? AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_BACKGROUND : AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_DO_NOT);
        } finally {
            frame.dispose();
        }
    }

    @Override // com.facebook.imagepipeline.animated.base.AnimatedImage
    @Nullable
    public Bitmap.Config getAnimatedBitmapConfig() {
        return this.mDecodeBitmapConfig;
    }
}
