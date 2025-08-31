package com.taobao.weex.utils.batch;

/* loaded from: classes3.dex */
public interface BactchExecutor {
    void post(Runnable runnable);

    void setInterceptor(Interceptor interceptor);
}
