package com.taobao.weex.bridge;

import java.lang.reflect.Type;

/* loaded from: classes.dex */
public interface Invoker {
    Type[] getParameterTypes();

    Object invoke(Object obj, Object... objArr);

    boolean isRunOnUIThread();
}
