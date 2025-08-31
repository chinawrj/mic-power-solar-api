package uts.sdk.modules.DCloudUniCreateRequestPermissionListener;

import io.dcloud.uts.UTSBridge;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: index.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0006\u0010\u0006\u001a\u00020\u0007\"\u001b\u0010\u0000\u001a\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005*\u0016\u0010\b\"\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0001*L\u0010\t\"#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\n2#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\n*L\u0010\u0011\"#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\n2#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\n*L\u0010\u0012\"#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\n2#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\n¨\u0006\u0013"}, d2 = {"createRequestPermissionListener", "Lkotlin/Function0;", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListener;", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/CreateRequestPermissionListener;", "getCreateRequestPermissionListener", "()Lkotlin/jvm/functions/Function0;", "createRequestPermissionListenerByJs", "", "CreateRequestPermissionListener", "RequestPermissionListenerCompleteCallback", "Lkotlin/Function1;", "Lio/dcloud/uts/UTSArray;", "", "Lkotlin/ParameterName;", "name", "permissions", "", "RequestPermissionListenerConfirmCallback", "RequestPermissionListenerRequestCallback", "uni-createRequestPermissionListener_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IndexKt {
    private static final Function0<RequestPermissionListener> createRequestPermissionListener = new Function0<RequestPermissionListener>() { // from class: uts.sdk.modules.DCloudUniCreateRequestPermissionListener.IndexKt$createRequestPermissionListener$1
        @Override // kotlin.jvm.functions.Function0
        public final RequestPermissionListener invoke() {
            return new AndroidPermissionRequestManager();
        }
    };

    public static final Function0<RequestPermissionListener> getCreateRequestPermissionListener() {
        return createRequestPermissionListener;
    }

    public static final int createRequestPermissionListenerByJs() {
        return UTSBridge.INSTANCE.registerJavaScriptClassInstance(new RequestPermissionListenerByJsProxy(createRequestPermissionListener.invoke()));
    }
}
