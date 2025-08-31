package io.dcloud.uts;

import io.dcloud.feature.utsplugin.ProxyModule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UTSBridge.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001¨\u0006\u0006"}, d2 = {"Lio/dcloud/uts/UTSBridge;", "", "()V", "registerJavaScriptClassInstance", "", "nativeInstance", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSBridge {
    public static final UTSBridge INSTANCE = new UTSBridge();

    private UTSBridge() {
    }

    public final int registerJavaScriptClassInstance(java.lang.Object nativeInstance) {
        Intrinsics.checkNotNullParameter(nativeInstance, "nativeInstance");
        ProxyModule.Companion companion = ProxyModule.INSTANCE;
        companion.setInstanceDynamicId(companion.getInstanceDynamicId() + 1);
        ProxyModule.INSTANCE.getUtsInstances().put(Integer.valueOf(ProxyModule.INSTANCE.getInstanceDynamicId()), nativeInstance);
        return ProxyModule.INSTANCE.getInstanceDynamicId();
    }
}
