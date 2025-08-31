package io.dcloud.uniapp.extapi;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import uts.sdk.modules.DCloudUniCreateRequestPermissionListener.IndexKt;
import uts.sdk.modules.DCloudUniCreateRequestPermissionListener.RequestPermissionListener;

/* compiled from: uniCreateRequestPermissionListener.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u001b\u0010\u0000\u001a\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005*\n\u0010\u0006\"\u00020\u00022\u00020\u0002¨\u0006\u0007"}, d2 = {"createRequestPermissionListener", "Lkotlin/Function0;", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListener;", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/CreateRequestPermissionListener;", "getCreateRequestPermissionListener", "()Lkotlin/jvm/functions/Function0;", "RequestPermissionListener", "uni-createRequestPermissionListener_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UniCreateRequestPermissionListenerKt {
    private static final Function0<RequestPermissionListener> createRequestPermissionListener = IndexKt.getCreateRequestPermissionListener();

    public static final Function0<RequestPermissionListener> getCreateRequestPermissionListener() {
        return createRequestPermissionListener;
    }
}
