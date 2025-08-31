package com.facebook.imagepipeline.animated.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimatedImageCompositor {
    private final AnimatedDrawableBackend mAnimatedDrawableBackend;
    private final Callback mCallback;
    private final Paint mTransparentFillPaint;

    public interface Callback {
        @Nullable
        CloseableReference<Bitmap> getCachedBitmap(int frameNumber);

        void onIntermediateResult(int frameNumber, Bitmap bitmap);
    }

    private enum FrameNeededResult {
        REQUIRED,
        NOT_REQUIRED,
        SKIP,
        ABORT
    }

    public AnimatedImageCompositor(AnimatedDrawableBackend animatedDrawableBackend, Callback callback) {
        this.mAnimatedDrawableBackend = animatedDrawableBackend;
        this.mCallback = callback;
        Paint paint = new Paint();
        this.mTransparentFillPaint = paint;
        paint.setColor(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
    }

    public void renderFrame(int frameNumber, Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(0, PorterDuff.Mode.SRC);
        for (int iPrepareCanvasWithClosestCachedFrame = !isKeyFrame(frameNumber) ? prepareCanvasWithClosestCachedFrame(frameNumber - 1, canvas) : frameNumber; iPrepareCanvasWithClosestCachedFrame < frameNumber; iPrepareCanvasWithClosestCachedFrame++) {
            AnimatedDrawableFrameInfo frameInfo = this.mAnimatedDrawableBackend.getFrameInfo(iPrepareCanvasWithClosestCachedFrame);
            AnimatedDrawableFrameInfo.DisposalMethod disposalMethod = frameInfo.disposalMethod;
            if (disposalMethod != AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_PREVIOUS) {
                if (frameInfo.blendOperation == AnimatedDrawableFrameInfo.BlendOperation.NO_BLEND) {
                    disposeToBackground(canvas, frameInfo);
                }
                this.mAnimatedDrawableBackend.renderFrame(iPrepareCanvasWithClosestCachedFrame, canvas);
                this.mCallback.onIntermediateResult(iPrepareCanvasWithClosestCachedFrame, bitmap);
                if (disposalMethod == AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_BACKGROUND) {
                    disposeToBackground(canvas, frameInfo);
                }
            }
        }
        AnimatedDrawableFrameInfo frameInfo2 = this.mAnimatedDrawableBackend.getFrameInfo(frameNumber);
        if (frameInfo2.blendOperation == AnimatedDrawableFrameInfo.BlendOperation.NO_BLEND) {
            disposeToBackground(canvas, frameInfo2);
        }
        this.mAnimatedDrawableBackend.renderFrame(frameNumber, canvas);
        maybeApplyTransformation(bitmap);
    }

    /* renamed from: com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imagepipeline$animated$impl$AnimatedImageCompositor$FrameNeededResult;

        static {
            int[] iArr = new int[FrameNeededResult.values().length];
            $SwitchMap$com$facebook$imagepipeline$animated$impl$AnimatedImageCompositor$FrameNeededResult = iArr;
            try {
                iArr[FrameNeededResult.REQUIRED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$animated$impl$AnimatedImageCompositor$FrameNeededResult[FrameNeededResult.NOT_REQUIRED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$animated$impl$AnimatedImageCompositor$FrameNeededResult[FrameNeededResult.ABORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$animated$impl$AnimatedImageCompositor$FrameNeededResult[FrameNeededResult.SKIP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private int prepareCanvasWithClosestCachedFrame(int previousFrameNumber, Canvas canvas) {
        while (previousFrameNumber >= 0) {
            int i = AnonymousClass1.$SwitchMap$com$facebook$imagepipeline$animated$impl$AnimatedImageCompositor$FrameNeededResult[isFrameNeededForRendering(previousFrameNumber).ordinal()];
            if (i == 1) {
                AnimatedDrawableFrameInfo frameInfo = this.mAnimatedDrawableBackend.getFrameInfo(previousFrameNumber);
                CloseableReference<Bitmap> cachedBitmap = this.mCallback.getCachedBitmap(previousFrameNumber);
                if (cachedBitmap != null) {
                    try {
                        canvas.drawBitmap(cachedBitmap.get(), 0.0f, 0.0f, (Paint) null);
                        if (frameInfo.disposalMethod == AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_BACKGROUND) {
                            disposeToBackground(canvas, frameInfo);
                        }
                        return previousFrameNumber + 1;
                    } finally {
                        cachedBitmap.close();
                    }
                }
                if (isKeyFrame(previousFrameNumber)) {
                    return previousFrameNumber;
                }
            } else {
                if (i == 2) {
                    return previousFrameNumber + 1;
                }
                if (i == 3) {
                    return previousFrameNumber;
                }
            }
            previousFrameNumber--;
        }
        return 0;
    }

    private void disposeToBackground(Canvas canvas, AnimatedDrawableFrameInfo frameInfo) {
        canvas.drawRect(frameInfo.xOffset, frameInfo.yOffset, frameInfo.xOffset + frameInfo.width, frameInfo.yOffset + frameInfo.height, this.mTransparentFillPaint);
    }

    private FrameNeededResult isFrameNeededForRendering(int index) {
        AnimatedDrawableFrameInfo frameInfo = this.mAnimatedDrawableBackend.getFrameInfo(index);
        AnimatedDrawableFrameInfo.DisposalMethod disposalMethod = frameInfo.disposalMethod;
        if (disposalMethod == AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_DO_NOT) {
            return FrameNeededResult.REQUIRED;
        }
        if (disposalMethod == AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_BACKGROUND) {
            if (isFullFrame(frameInfo)) {
                return FrameNeededResult.NOT_REQUIRED;
            }
            return FrameNeededResult.REQUIRED;
        }
        if (disposalMethod == AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_PREVIOUS) {
            return FrameNeededResult.SKIP;
        }
        return FrameNeededResult.ABORT;
    }

    private boolean isKeyFrame(int index) {
        if (index == 0) {
            return true;
        }
        AnimatedDrawableFrameInfo frameInfo = this.mAnimatedDrawableBackend.getFrameInfo(index);
        AnimatedDrawableFrameInfo frameInfo2 = this.mAnimatedDrawableBackend.getFrameInfo(index - 1);
        if (frameInfo.blendOperation == AnimatedDrawableFrameInfo.BlendOperation.NO_BLEND && isFullFrame(frameInfo)) {
            return true;
        }
        return frameInfo2.disposalMethod == AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_BACKGROUND && isFullFrame(frameInfo2);
    }

    private boolean isFullFrame(AnimatedDrawableFrameInfo frameInfo) {
        return frameInfo.xOffset == 0 && frameInfo.yOffset == 0 && frameInfo.width == this.mAnimatedDrawableBackend.getRenderedWidth() && frameInfo.height == this.mAnimatedDrawableBackend.getRenderedHeight();
    }

    private void maybeApplyTransformation(Bitmap bitmap) {
        BitmapTransformation bitmapTransformation;
        AnimatedImageResult animatedImageResult = this.mAnimatedDrawableBackend.getAnimatedImageResult();
        if (animatedImageResult == null || (bitmapTransformation = animatedImageResult.getBitmapTransformation()) == null) {
            return;
        }
        bitmapTransformation.transform(bitmap);
    }
}
