package com.facebook.animated.webpdrawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.animated.webp.WebPFrame;
import com.facebook.animated.webp.WebPImage;
import com.facebook.fresco.animation.backend.AnimationBackend;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class WebpAnimationBackend implements AnimationBackend {
    private Rect mBounds;
    private final Rect mRenderDstRect = new Rect();
    private final Rect mRenderSrcRect = new Rect();

    @Nullable
    private Bitmap mTempBitmap;
    private final WebPImage mWebPImage;

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public int getSizeInBytes() {
        return 0;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void setAlpha(int alpha) {
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public static WebpAnimationBackend create(String filePath) throws Throwable {
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedInputStream.mark(Integer.MAX_VALUE);
            byte[] bArr = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(bArr);
            WebPImage webPImageCreateFromByteArray = WebPImage.createFromByteArray(bArr, null);
            bufferedInputStream.reset();
            WebpAnimationBackend webpAnimationBackend = new WebpAnimationBackend(webPImageCreateFromByteArray);
            closeSilently(bufferedInputStream);
            return webpAnimationBackend;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            closeSilently(bufferedInputStream2);
            throw th;
        }
    }

    private WebpAnimationBackend(WebPImage webPImage) {
        this.mWebPImage = webPImage;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public boolean drawFrame(Drawable parent, Canvas canvas, int frameNumber) {
        WebPFrame frame = this.mWebPImage.getFrame(frameNumber);
        double dWidth = this.mBounds.width() / parent.getIntrinsicWidth();
        double dHeight = this.mBounds.height() / parent.getIntrinsicHeight();
        int iRound = (int) Math.round(frame.getWidth() * dWidth);
        int iRound2 = (int) Math.round(frame.getHeight() * dHeight);
        int xOffset = (int) (frame.getXOffset() * dWidth);
        int yOffset = (int) (frame.getYOffset() * dHeight);
        synchronized (this) {
            int iWidth = this.mBounds.width();
            int iHeight = this.mBounds.height();
            prepareTempBitmapForThisSize(iWidth, iHeight);
            Bitmap bitmap = this.mTempBitmap;
            if (bitmap == null) {
                return false;
            }
            frame.renderFrame(iRound, iRound2, bitmap);
            this.mRenderSrcRect.set(0, 0, iWidth, iHeight);
            this.mRenderDstRect.set(xOffset, yOffset, iWidth + xOffset, iHeight + yOffset);
            canvas.drawBitmap(this.mTempBitmap, this.mRenderSrcRect, this.mRenderDstRect, (Paint) null);
            return true;
        }
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public synchronized void setBounds(Rect bounds) {
        this.mBounds = bounds;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public int getIntrinsicWidth() {
        return this.mWebPImage.getWidth();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public int getIntrinsicHeight() {
        return this.mWebPImage.getHeight();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackend
    public void clear() {
        this.mWebPImage.dispose();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getFrameCount() {
        return this.mWebPImage.getFrameCount();
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getFrameDurationMs(int frameNumber) {
        return this.mWebPImage.getFrameDurations()[frameNumber];
    }

    @Override // com.facebook.fresco.animation.backend.AnimationInformation
    public int getLoopCount() {
        return this.mWebPImage.getLoopCount();
    }

    private synchronized void prepareTempBitmapForThisSize(int width, int height) {
        Bitmap bitmap = this.mTempBitmap;
        if (bitmap != null && (bitmap.getWidth() < width || this.mTempBitmap.getHeight() < height)) {
            clearTempBitmap();
        }
        if (this.mTempBitmap == null) {
            this.mTempBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }
        this.mTempBitmap.eraseColor(0);
    }

    private synchronized void clearTempBitmap() {
        Bitmap bitmap = this.mTempBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mTempBitmap = null;
        }
    }

    private static void closeSilently(@Nullable Closeable closeable) throws IOException {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }
}
