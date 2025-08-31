package io.dcloud.uts.gson;

import java.lang.reflect.Type;

/* loaded from: classes3.dex */
public interface InstanceCreator<T> {
    T createInstance(Type type);
}
