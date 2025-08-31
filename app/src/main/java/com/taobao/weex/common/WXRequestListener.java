package com.taobao.weex.common;

/* loaded from: classes.dex */
public interface WXRequestListener {
    void onError(int i, Object obj, WXResponse wXResponse);

    void onSuccess(int i, Object obj, WXResponse wXResponse);
}
