package com.facebook.imagepipeline.nativecode;

import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public final class NativeImageTranscoderFactory {
    private NativeImageTranscoderFactory() {
    }

    public static ImageTranscoderFactory getNativeImageTranscoderFactory(final int maxBitmapSize, final boolean useDownSamplingRatio, final boolean ensureTranscoderLibraryLoaded) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("com.facebook.imagepipeline.nativecode.NativeJpegTranscoderFactory");
            Class<?> cls2 = Boolean.TYPE;
            return (ImageTranscoderFactory) cls.getConstructor(Integer.TYPE, cls2, cls2).newInstance(Integer.valueOf(maxBitmapSize), Boolean.valueOf(useDownSamplingRatio), Boolean.valueOf(ensureTranscoderLibraryLoaded));
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.", e);
        }
    }
}
