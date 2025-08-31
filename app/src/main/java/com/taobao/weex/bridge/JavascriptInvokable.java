package com.taobao.weex.bridge;

/* loaded from: classes.dex */
public interface JavascriptInvokable {
    Invoker getMethodInvoker(String str);

    String[] getMethods();
}
