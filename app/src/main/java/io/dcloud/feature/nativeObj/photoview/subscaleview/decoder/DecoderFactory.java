package io.dcloud.feature.nativeObj.photoview.subscaleview.decoder;

import java.lang.reflect.InvocationTargetException;

/* loaded from: classes3.dex */
public interface DecoderFactory<T> {
    T make() throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException;
}
