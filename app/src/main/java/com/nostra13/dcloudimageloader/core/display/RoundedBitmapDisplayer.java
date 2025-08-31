package com.nostra13.dcloudimageloader.core.display;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView;
import com.nostra13.dcloudimageloader.core.assist.LoadedFrom;
import com.nostra13.dcloudimageloader.core.imageaware.ImageAware;
import com.nostra13.dcloudimageloader.core.imageaware.ImageViewAware;
import com.nostra13.dcloudimageloader.utils.L;

/* loaded from: classes.dex */
public class RoundedBitmapDisplayer implements BitmapDisplayer {
    private final int roundPixels;

    public RoundedBitmapDisplayer(int i) {
        this.roundPixels = i;
    }

    @Override // com.nostra13.dcloudimageloader.core.display.BitmapDisplayer
    public Bitmap display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        if (!(imageAware instanceof ImageViewAware)) {
            throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
        }
        Bitmap bitmapRoundCorners = roundCorners(bitmap, (ImageViewAware) imageAware, this.roundPixels);
        imageAware.setImageBitmap(bitmapRoundCorners);
        return bitmapRoundCorners;
    }

    public static Bitmap roundCorners(Bitmap bitmap, ImageViewAware imageViewAware, int i) {
        int i2;
        int iMin;
        Rect rect;
        int i3;
        int i4;
        Rect rect2;
        Rect rect3;
        int i5;
        int i6;
        Rect rect4;
        ImageView wrappedView = imageViewAware.getWrappedView();
        if (wrappedView == null) {
            L.w("View is collected probably. Can't round bitmap corners without view properties.", new Object[0]);
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = imageViewAware.getWidth();
        int height2 = imageViewAware.getHeight();
        if (width2 <= 0) {
            width2 = width;
        }
        if (height2 <= 0) {
            height2 = height;
        }
        ImageView.ScaleType scaleType = wrappedView.getScaleType();
        if (scaleType == null) {
            return bitmap;
        }
        int iOrdinal = scaleType.ordinal();
        try {
            if (iOrdinal == 1) {
                float f = width;
                float f2 = height;
                if (width2 / height2 > f / f2) {
                    iMin = Math.min(height2, height);
                    i2 = (int) (f / (f2 / iMin));
                } else {
                    int iMin2 = Math.min(width2, width);
                    int i7 = (int) (f2 / (f / iMin2));
                    i2 = iMin2;
                    iMin = i7;
                }
                int i8 = (width2 - i2) / 2;
                int i9 = (height2 - iMin) / 2;
                Rect rect5 = new Rect(0, 0, width, height);
                rect = new Rect(i8, i9, i2 + i8, iMin + i9);
                i3 = width2;
                i4 = height2;
                rect2 = rect5;
            } else {
                if (iOrdinal == 5) {
                    float f3 = width2;
                    float f4 = height2;
                    float f5 = width;
                    float f6 = height;
                    if (f3 / f4 > f5 / f6) {
                        int i10 = (int) (f4 * (f5 / f3));
                        i5 = (height - i10) / 2;
                        height = i10;
                        i6 = 0;
                    } else {
                        int i11 = (int) (f3 * (f6 / f4));
                        int i12 = (width - i11) / 2;
                        i5 = 0;
                        width = i11;
                        i6 = i12;
                    }
                    i3 = width;
                    i4 = height;
                    rect2 = new Rect(i6, i5, i6 + width, i5 + height);
                    rect3 = new Rect(0, 0, width, height);
                    return getRoundedCornerBitmap(bitmap, i, rect2, rect3, i3, i4);
                }
                if (iOrdinal == 6) {
                    rect4 = new Rect(0, 0, width, height);
                    rect = new Rect(0, 0, width2, height2);
                } else if (iOrdinal != 7 && iOrdinal != 8) {
                    float f7 = width2;
                    float f8 = height2;
                    float f9 = width;
                    float f10 = height;
                    if (f7 / f8 > f9 / f10) {
                        width2 = (int) (f9 / (f10 / f8));
                    } else {
                        height2 = (int) (f10 / (f9 / f7));
                    }
                    rect4 = new Rect(0, 0, width, height);
                    rect = new Rect(0, 0, width2, height2);
                } else {
                    int iMin3 = Math.min(width2, width);
                    int iMin4 = Math.min(height2, height);
                    int i13 = (width - iMin3) / 2;
                    int i14 = (height - iMin4) / 2;
                    Rect rect6 = new Rect(i13, i14, i13 + iMin3, i14 + iMin4);
                    rect = new Rect(0, 0, iMin3, iMin4);
                    i3 = iMin3;
                    i4 = iMin4;
                    rect2 = rect6;
                }
                i3 = width2;
                i4 = height2;
                rect2 = rect4;
            }
            return getRoundedCornerBitmap(bitmap, i, rect2, rect3, i3, i4);
        } catch (OutOfMemoryError e) {
            L.e(e, "Can't create bitmap with rounded corners. Not enough memory.", new Object[0]);
            return bitmap;
        }
        rect3 = rect;
    }

    private static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int i, Rect rect, Rect rect2, int i2, int i3) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        RectF rectF = new RectF(rect2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-16777216);
        float f = i;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rectF, paint);
        return bitmapCreateBitmap;
    }
}
