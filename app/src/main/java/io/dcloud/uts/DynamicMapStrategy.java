package io.dcloud.uts;

import io.dcloud.uts.gson.ExclusionStrategy;
import io.dcloud.uts.gson.FieldAttributes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UTSJSONObject.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lio/dcloud/uts/DynamicMapStrategy;", "Lio/dcloud/uts/gson/ExclusionStrategy;", "()V", "shouldSkipClass", "", "clazz", "Ljava/lang/Class;", "shouldSkipField", "f", "Lio/dcloud/uts/gson/FieldAttributes;", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DynamicMapStrategy implements ExclusionStrategy {
    @Override // io.dcloud.uts.gson.ExclusionStrategy
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }

    @Override // io.dcloud.uts.gson.ExclusionStrategy
    public boolean shouldSkipField(FieldAttributes f) {
        Intrinsics.checkNotNullParameter(f, "f");
        return f.getAnnotation(GsonTransparent.class) != null;
    }
}
