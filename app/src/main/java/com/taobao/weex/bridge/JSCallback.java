package com.taobao.weex.bridge;

/* loaded from: classes.dex */
public interface JSCallback {
    void invoke(Object obj);

    void invokeAndKeepAlive(Object obj);
}
