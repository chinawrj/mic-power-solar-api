package io.dcloud.uts.gson;

import java.lang.reflect.Type;

/* loaded from: classes3.dex */
public interface JsonSerializer<T> {
    JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext);
}
