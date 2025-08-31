package com.facebook.webpsupport;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.TypedValue;
import com.alibaba.fastjson.asm.Opcodes;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.nativecode.StaticWebpNativeLoader;
import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class WebpBitmapFactoryImpl implements WebpBitmapFactory {
    private static final int HEADER_SIZE = 20;
    public static final boolean IN_BITMAP_SUPPORTED = true;
    private static final int IN_TEMP_BUFFER_SIZE = 8192;
    private static BitmapCreator mBitmapCreator;
    private static WebpBitmapFactory.WebpErrorLogger mWebpErrorLogger;

    @Nullable
    private static native Bitmap nativeDecodeByteArray(byte[] data, int offset, int length, @Nullable BitmapFactory.Options opts, float scale, byte[] inTempStorage);

    @Nullable
    private static native Bitmap nativeDecodeStream(InputStream is, @Nullable BitmapFactory.Options options, float scale, byte[] inTempStorage);

    private static native long nativeSeek(FileDescriptor fd, long offset, boolean absolute);

    @Override // com.facebook.common.webp.WebpBitmapFactory
    public void setBitmapCreator(final BitmapCreator bitmapCreator) {
        mBitmapCreator = bitmapCreator;
    }

    private static InputStream wrapToMarkSupportedStream(InputStream inputStream) {
        return !inputStream.markSupported() ? new BufferedInputStream(inputStream, 20) : inputStream;
    }

    @Nullable
    private static byte[] getWebpHeader(InputStream inputStream, @Nullable BitmapFactory.Options opts) throws IOException {
        byte[] bArr;
        inputStream.mark(20);
        if (opts != null && opts.inTempStorage != null && opts.inTempStorage.length >= 20) {
            bArr = opts.inTempStorage;
        } else {
            bArr = new byte[20];
        }
        try {
            inputStream.read(bArr, 0, 20);
            inputStream.reset();
            return bArr;
        } catch (IOException unused) {
            return null;
        }
    }

    private static void setDensityFromOptions(@Nullable Bitmap outputBitmap, @Nullable BitmapFactory.Options opts) {
        if (outputBitmap == null || opts == null) {
            return;
        }
        int i = opts.inDensity;
        if (i != 0) {
            outputBitmap.setDensity(i);
            int i2 = opts.inTargetDensity;
            if (i2 == 0 || i == i2 || i == opts.inScreenDensity || !opts.inScaled) {
                return;
            }
            outputBitmap.setDensity(i2);
            return;
        }
        if (!IN_BITMAP_SUPPORTED || opts.inBitmap == null) {
            return;
        }
        outputBitmap.setDensity(Opcodes.IF_ICMPNE);
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    public void setWebpErrorLogger(WebpBitmapFactory.WebpErrorLogger webpErrorLogger) {
        mWebpErrorLogger = webpErrorLogger;
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    @Nullable
    public Bitmap decodeFileDescriptor(FileDescriptor fd, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) {
        return hookDecodeFileDescriptor(fd, outPadding, opts);
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    @Nullable
    public Bitmap decodeStream(InputStream inputStream, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) {
        return hookDecodeStream(inputStream, outPadding, opts);
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    @Nullable
    public Bitmap decodeFile(String pathName, @Nullable BitmapFactory.Options opts) {
        return hookDecodeFile(pathName, opts);
    }

    @Override // com.facebook.common.webp.WebpBitmapFactory
    @Nullable
    public Bitmap decodeByteArray(byte[] array, int offset, int length, @Nullable BitmapFactory.Options opts) {
        return hookDecodeByteArray(array, offset, length, opts);
    }

    @Nullable
    public static Bitmap hookDecodeByteArray(byte[] array, int offset, int length, @Nullable BitmapFactory.Options opts) {
        Bitmap bitmapOriginalDecodeByteArray;
        StaticWebpNativeLoader.ensure();
        if (WebpSupportStatus.sIsWebpSupportRequired && WebpSupportStatus.isWebpHeader(array, offset, length)) {
            bitmapOriginalDecodeByteArray = nativeDecodeByteArray(array, offset, length, opts, getScaleFromOptions(opts), getInTempStorageFromOptions(opts));
            if (bitmapOriginalDecodeByteArray == null) {
                sendWebpErrorLog("webp_direct_decode_array");
            }
            setWebpBitmapOptions(bitmapOriginalDecodeByteArray, opts);
        } else {
            bitmapOriginalDecodeByteArray = originalDecodeByteArray(array, offset, length, opts);
            if (bitmapOriginalDecodeByteArray == null) {
                sendWebpErrorLog("webp_direct_decode_array_failed_on_no_webp");
            }
        }
        return bitmapOriginalDecodeByteArray;
    }

    @Nullable
    private static Bitmap originalDecodeByteArray(byte[] array, int offset, int length, @Nullable BitmapFactory.Options opts) {
        return BitmapFactory.decodeByteArray(array, offset, length, opts);
    }

    @Nullable
    public static Bitmap hookDecodeByteArray(byte[] array, int offset, int length) {
        return hookDecodeByteArray(array, offset, length, null);
    }

    @Nullable
    private static Bitmap originalDecodeByteArray(byte[] array, int offset, int length) {
        return BitmapFactory.decodeByteArray(array, offset, length);
    }

    @Nullable
    public static Bitmap hookDecodeStream(InputStream inputStream, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) throws IOException {
        Bitmap bitmapOriginalDecodeStream;
        StaticWebpNativeLoader.ensure();
        InputStream inputStreamWrapToMarkSupportedStream = wrapToMarkSupportedStream(inputStream);
        byte[] webpHeader = getWebpHeader(inputStreamWrapToMarkSupportedStream, opts);
        if (WebpSupportStatus.sIsWebpSupportRequired && webpHeader != null && WebpSupportStatus.isWebpHeader(webpHeader, 0, 20)) {
            bitmapOriginalDecodeStream = nativeDecodeStream(inputStreamWrapToMarkSupportedStream, opts, getScaleFromOptions(opts), getInTempStorageFromOptions(opts));
            if (bitmapOriginalDecodeStream == null) {
                sendWebpErrorLog("webp_direct_decode_stream");
            }
            setWebpBitmapOptions(bitmapOriginalDecodeStream, opts);
            setPaddingDefaultValues(outPadding);
        } else {
            bitmapOriginalDecodeStream = originalDecodeStream(inputStreamWrapToMarkSupportedStream, outPadding, opts);
            if (bitmapOriginalDecodeStream == null) {
                sendWebpErrorLog("webp_direct_decode_stream_failed_on_no_webp");
            }
        }
        return bitmapOriginalDecodeStream;
    }

    @Nullable
    private static Bitmap originalDecodeStream(InputStream inputStream, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) {
        return BitmapFactory.decodeStream(inputStream, outPadding, opts);
    }

    @Nullable
    public static Bitmap hookDecodeStream(InputStream inputStream) {
        return hookDecodeStream(inputStream, null, null);
    }

    @Nullable
    private static Bitmap originalDecodeStream(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }

    @Nullable
    public static Bitmap hookDecodeFile(String pathName, @Nullable BitmapFactory.Options opts) throws IOException {
        Bitmap bitmapHookDecodeStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(pathName);
            try {
                bitmapHookDecodeStream = hookDecodeStream(fileInputStream, null, opts);
                fileInputStream.close();
            } finally {
            }
        } catch (Exception unused) {
        }
        return bitmapHookDecodeStream;
    }

    @Nullable
    public static Bitmap hookDecodeFile(String pathName) {
        return hookDecodeFile(pathName, null);
    }

    @Nullable
    public static Bitmap hookDecodeResourceStream(@Nullable Resources res, @Nullable TypedValue value, InputStream is, @Nullable Rect pad, @Nullable BitmapFactory.Options opts) {
        if (opts == null) {
            opts = new BitmapFactory.Options();
        }
        if (opts.inDensity == 0 && value != null) {
            int i = value.density;
            if (i == 0) {
                opts.inDensity = Opcodes.IF_ICMPNE;
            } else if (i != 65535) {
                opts.inDensity = i;
            }
        }
        if (opts.inTargetDensity == 0 && res != null) {
            opts.inTargetDensity = res.getDisplayMetrics().densityDpi;
        }
        return hookDecodeStream(is, pad, opts);
    }

    @Nullable
    private static Bitmap originalDecodeResourceStream(Resources res, TypedValue value, InputStream is, Rect pad, BitmapFactory.Options opts) {
        return BitmapFactory.decodeResourceStream(res, value, is, pad, opts);
    }

    @Nullable
    public static Bitmap hookDecodeResource(Resources res, int id, @Nullable BitmapFactory.Options opts) throws Resources.NotFoundException, IOException {
        TypedValue typedValue = new TypedValue();
        Bitmap bitmapHookDecodeResourceStream = null;
        try {
            InputStream inputStreamOpenRawResource = res.openRawResource(id, typedValue);
            try {
                bitmapHookDecodeResourceStream = hookDecodeResourceStream(res, typedValue, inputStreamOpenRawResource, null, opts);
                if (inputStreamOpenRawResource != null) {
                    inputStreamOpenRawResource.close();
                }
            } finally {
            }
        } catch (Exception unused) {
        }
        if (!IN_BITMAP_SUPPORTED || bitmapHookDecodeResourceStream != null || opts == null || opts.inBitmap == null) {
            return bitmapHookDecodeResourceStream;
        }
        throw new IllegalArgumentException("Problem decoding into existing bitmap");
    }

    @Nullable
    private static Bitmap originalDecodeResource(Resources res, int id, BitmapFactory.Options opts) {
        return BitmapFactory.decodeResource(res, id, opts);
    }

    @Nullable
    public static Bitmap hookDecodeResource(Resources res, int id) {
        return hookDecodeResource(res, id, null);
    }

    @Nullable
    private static Bitmap originalDecodeResource(Resources res, int id) {
        return BitmapFactory.decodeResource(res, id);
    }

    private static boolean setOutDimensions(@Nullable BitmapFactory.Options options, int imageWidth, int imageHeight) {
        if (options == null || !options.inJustDecodeBounds) {
            return false;
        }
        options.outWidth = imageWidth;
        options.outHeight = imageHeight;
        return true;
    }

    private static void setPaddingDefaultValues(@Nullable Rect padding) {
        if (padding != null) {
            padding.top = -1;
            padding.left = -1;
            padding.bottom = -1;
            padding.right = -1;
        }
    }

    private static void setBitmapSize(@Nullable BitmapFactory.Options options, int width, int height) {
        if (options != null) {
            options.outWidth = width;
            options.outHeight = height;
        }
    }

    @Nullable
    private static Bitmap originalDecodeFile(String pathName, @Nullable BitmapFactory.Options opts) {
        return BitmapFactory.decodeFile(pathName, opts);
    }

    @Nullable
    private static Bitmap originalDecodeFile(String pathName) {
        return BitmapFactory.decodeFile(pathName);
    }

    @Nullable
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fd, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) throws IOException {
        Bitmap bitmapOriginalDecodeFileDescriptor;
        StaticWebpNativeLoader.ensure();
        long jNativeSeek = nativeSeek(fd, 0L, false);
        if (jNativeSeek != -1) {
            InputStream inputStreamWrapToMarkSupportedStream = wrapToMarkSupportedStream(new FileInputStream(fd));
            try {
                byte[] webpHeader = getWebpHeader(inputStreamWrapToMarkSupportedStream, opts);
                if (WebpSupportStatus.sIsWebpSupportRequired && webpHeader != null && WebpSupportStatus.isWebpHeader(webpHeader, 0, 20)) {
                    bitmapOriginalDecodeFileDescriptor = nativeDecodeStream(inputStreamWrapToMarkSupportedStream, opts, getScaleFromOptions(opts), getInTempStorageFromOptions(opts));
                    if (bitmapOriginalDecodeFileDescriptor == null) {
                        sendWebpErrorLog("webp_direct_decode_fd");
                    }
                    setPaddingDefaultValues(outPadding);
                    setWebpBitmapOptions(bitmapOriginalDecodeFileDescriptor, opts);
                } else {
                    nativeSeek(fd, jNativeSeek, true);
                    bitmapOriginalDecodeFileDescriptor = originalDecodeFileDescriptor(fd, outPadding, opts);
                    if (bitmapOriginalDecodeFileDescriptor == null) {
                        sendWebpErrorLog("webp_direct_decode_fd_failed_on_no_webp");
                    }
                }
                try {
                    return bitmapOriginalDecodeFileDescriptor;
                } catch (Throwable unused) {
                    return bitmapOriginalDecodeFileDescriptor;
                }
            } finally {
                try {
                    inputStreamWrapToMarkSupportedStream.close();
                } catch (Throwable unused2) {
                }
            }
        }
        Bitmap bitmapHookDecodeStream = hookDecodeStream(new FileInputStream(fd), outPadding, opts);
        setPaddingDefaultValues(outPadding);
        return bitmapHookDecodeStream;
    }

    @Nullable
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fd, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts) {
        return BitmapFactory.decodeFileDescriptor(fd, outPadding, opts);
    }

    @Nullable
    public static Bitmap hookDecodeFileDescriptor(FileDescriptor fd) {
        return hookDecodeFileDescriptor(fd, null, null);
    }

    @Nullable
    private static Bitmap originalDecodeFileDescriptor(FileDescriptor fd) {
        return BitmapFactory.decodeFileDescriptor(fd);
    }

    private static void setWebpBitmapOptions(@Nullable Bitmap bitmap, @Nullable BitmapFactory.Options opts) {
        setDensityFromOptions(bitmap, opts);
        if (opts != null) {
            opts.outMimeType = "image/webp";
        }
    }

    private static boolean shouldPremultiply(@Nullable BitmapFactory.Options options) {
        if (options != null) {
            return options.inPremultiplied;
        }
        return true;
    }

    @Nullable
    private static Bitmap createBitmap(int width, int height, @Nullable BitmapFactory.Options options) {
        if (IN_BITMAP_SUPPORTED && options != null && options.inBitmap != null && options.inBitmap.isMutable()) {
            return options.inBitmap;
        }
        return mBitmapCreator.createNakedBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

    private static byte[] getInTempStorageFromOptions(@Nullable final BitmapFactory.Options options) {
        if (options != null && options.inTempStorage != null) {
            return options.inTempStorage;
        }
        return new byte[8192];
    }

    private static float getScaleFromOptions(@Nullable BitmapFactory.Options options) {
        if (options == null) {
            return 1.0f;
        }
        int i = options.inSampleSize;
        float f = i > 1 ? 1.0f / i : 1.0f;
        if (!options.inScaled) {
            return f;
        }
        int i2 = options.inDensity;
        int i3 = options.inTargetDensity;
        return (i2 == 0 || i3 == 0 || i2 == options.inScreenDensity) ? f : i3 / i2;
    }

    private static void sendWebpErrorLog(String message) {
        WebpBitmapFactory.WebpErrorLogger webpErrorLogger = mWebpErrorLogger;
        if (webpErrorLogger != null) {
            webpErrorLogger.onWebpErrorLog(message, "decoding_failure");
        }
    }
}
