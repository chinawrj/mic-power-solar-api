package io.dcloud.feature.uniapp.utils.bitmap;

/* loaded from: classes3.dex */
public interface BitmapLoadCallback<T> {
    void onFailure(String str, Throwable th);

    void onSuccess(String str, T t);
}
