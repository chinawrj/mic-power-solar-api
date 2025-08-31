package io.dcloud.feature.nativeObj.photoview.subscaleview.decoder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import io.dcloud.feature.nativeObj.photoview.subscaleview.SubsamplingScaleImageView;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes3.dex */
public class SkiaImageRegionDecoder implements ImageRegionDecoder {
    private static final String ASSET_PREFIX = "file:///android_asset/";
    private static final String FILE_PREFIX = "file://";
    private static final String RESOURCE_PREFIX = "android.resource://";
    private final Bitmap.Config bitmapConfig;
    private BitmapRegionDecoder decoder;
    private final ReadWriteLock decoderLock;

    public SkiaImageRegionDecoder() {
        this(null);
    }

    @Override // io.dcloud.feature.nativeObj.photoview.subscaleview.decoder.ImageRegionDecoder
    public Bitmap decodeRegion(Rect rect, int i) {
        getDecodeLock().lock();
        try {
            BitmapRegionDecoder bitmapRegionDecoder = this.decoder;
            if (bitmapRegionDecoder == null || bitmapRegionDecoder.isRecycled()) {
                throw new IllegalStateException("Cannot decode region after decoder has been recycled");
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = i;
            options.inPreferredConfig = this.bitmapConfig;
            Bitmap bitmapDecodeRegion = this.decoder.decodeRegion(rect, options);
            if (bitmapDecodeRegion != null) {
                return bitmapDecodeRegion;
            }
            throw new RuntimeException("Skia image decoder returned null bitmap - image format may not be supported");
        } finally {
            getDecodeLock().unlock();
        }
    }

    @Override // io.dcloud.feature.nativeObj.photoview.subscaleview.decoder.ImageRegionDecoder
    public Point init(Context context, Uri uri) throws Exception {
        int identifier;
        String string = uri.toString();
        if (string.startsWith(RESOURCE_PREFIX)) {
            String authority = uri.getAuthority();
            Resources resources = context.getPackageName().equals(authority) ? context.getResources() : context.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            int size = pathSegments.size();
            if (size == 2 && pathSegments.get(0).equals("drawable")) {
                identifier = resources.getIdentifier(pathSegments.get(1), "drawable", authority);
            } else if (size == 1 && TextUtils.isDigitsOnly(pathSegments.get(0))) {
                try {
                    identifier = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                }
            } else {
                identifier = 0;
            }
            this.decoder = BitmapRegionDecoder.newInstance(context.getResources().openRawResource(identifier), false);
        } else if (string.startsWith("file:///android_asset/")) {
            this.decoder = BitmapRegionDecoder.newInstance(context.getAssets().open(string.substring(22), 1), false);
        } else if (string.startsWith("file://")) {
            this.decoder = BitmapRegionDecoder.newInstance(string.substring(7), false);
        } else {
            InputStream inputStreamOpenInputStream = null;
            try {
                inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                this.decoder = BitmapRegionDecoder.newInstance(inputStreamOpenInputStream, false);
                if (inputStreamOpenInputStream != null) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (Exception unused2) {
                    }
                }
            } catch (Throwable th) {
                if (inputStreamOpenInputStream != null) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (Exception unused3) {
                    }
                }
                throw th;
            }
        }
        return new Point(this.decoder.getWidth(), this.decoder.getHeight());
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000d  */
    @Override // io.dcloud.feature.nativeObj.photoview.subscaleview.decoder.ImageRegionDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean isReady() {
        /*
            r1 = this;
            monitor-enter(r1)
            android.graphics.BitmapRegionDecoder r0 = r1.decoder     // Catch: java.lang.Throwable -> L10
            if (r0 == 0) goto Ld
            boolean r0 = r0.isRecycled()     // Catch: java.lang.Throwable -> L10
            if (r0 != 0) goto Ld
            r0 = 1
            goto Le
        Ld:
            r0 = 0
        Le:
            monitor-exit(r1)
            return r0
        L10:
            r0 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L10
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.nativeObj.photoview.subscaleview.decoder.SkiaImageRegionDecoder.isReady():boolean");
    }

    @Override // io.dcloud.feature.nativeObj.photoview.subscaleview.decoder.ImageRegionDecoder
    public synchronized void recycle() {
        this.decoderLock.writeLock().lock();
        try {
            this.decoder.recycle();
            this.decoder = null;
        } finally {
            this.decoderLock.writeLock().unlock();
        }
    }

    public SkiaImageRegionDecoder(Bitmap.Config config) {
        this.decoderLock = new ReentrantReadWriteLock(true);
        Bitmap.Config preferredBitmapConfig = SubsamplingScaleImageView.getPreferredBitmapConfig();
        if (config != null) {
            this.bitmapConfig = config;
        } else if (preferredBitmapConfig != null) {
            this.bitmapConfig = preferredBitmapConfig;
        } else {
            this.bitmapConfig = Bitmap.Config.RGB_565;
        }
    }

    private Lock getDecodeLock() {
        return this.decoderLock.readLock();
    }
}
