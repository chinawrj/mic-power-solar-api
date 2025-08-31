package uts.sdk.modules.DCloudUniCreateRequestPermissionListener;

import com.taobao.weex.bridge.WXBridgeManager;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSCallback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016R\u001a\u0010\u0005\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004¨\u0006\u0010"}, d2 = {"Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListenerByJsProxy;", "", "ins", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListener;", "(Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListener;)V", "__instance", "get__instance", "()Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListener;", "set__instance", "onCompleteByJs", "", WXBridgeManager.METHOD_CALLBACK, "Lio/dcloud/uts/UTSCallback;", "onConfirmByJs", "onRequestByJs", "stopByJs", "uni-createRequestPermissionListener_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class RequestPermissionListenerByJsProxy {
    private RequestPermissionListener __instance;

    public RequestPermissionListener get__instance() {
        return this.__instance;
    }

    public void set__instance(RequestPermissionListener requestPermissionListener) {
        Intrinsics.checkNotNullParameter(requestPermissionListener, "<set-?>");
        this.__instance = requestPermissionListener;
    }

    public RequestPermissionListenerByJsProxy(RequestPermissionListener ins) {
        Intrinsics.checkNotNullParameter(ins, "ins");
        set__instance(ins);
    }

    public void onRequestByJs(final UTSCallback callback) {
        Object fnJS;
        Intrinsics.checkNotNullParameter(callback, "callback");
        RequestPermissionListener requestPermissionListener = get__instance();
        if (callback.getFnJS() != null) {
            fnJS = callback.getFnJS();
        } else {
            callback.setFnJS(new Function1<UTSArray<String>, Unit>() { // from class: uts.sdk.modules.DCloudUniCreateRequestPermissionListener.RequestPermissionListenerByJsProxy.onRequestByJs.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(UTSArray<String> uTSArray) throws SecurityException {
                    invoke2(uTSArray);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(UTSArray<String> permissions) throws SecurityException {
                    Intrinsics.checkNotNullParameter(permissions, "permissions");
                    callback.invoke(permissions);
                }
            });
            fnJS = callback.getFnJS();
        }
        Intrinsics.checkNotNull(fnJS, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'permissions')] io.dcloud.uts.UTSArray<kotlin.String>, kotlin.Unit>");
        requestPermissionListener.onRequest((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(fnJS, 1));
    }

    public void onConfirmByJs(final UTSCallback callback) {
        Object fnJS;
        Intrinsics.checkNotNullParameter(callback, "callback");
        RequestPermissionListener requestPermissionListener = get__instance();
        if (callback.getFnJS() != null) {
            fnJS = callback.getFnJS();
        } else {
            callback.setFnJS(new Function1<UTSArray<String>, Unit>() { // from class: uts.sdk.modules.DCloudUniCreateRequestPermissionListener.RequestPermissionListenerByJsProxy.onConfirmByJs.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(UTSArray<String> uTSArray) throws SecurityException {
                    invoke2(uTSArray);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(UTSArray<String> permissions) throws SecurityException {
                    Intrinsics.checkNotNullParameter(permissions, "permissions");
                    callback.invoke(permissions);
                }
            });
            fnJS = callback.getFnJS();
        }
        Intrinsics.checkNotNull(fnJS, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'permissions')] io.dcloud.uts.UTSArray<kotlin.String>, kotlin.Unit>");
        requestPermissionListener.onConfirm((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(fnJS, 1));
    }

    public void onCompleteByJs(final UTSCallback callback) {
        Object fnJS;
        Intrinsics.checkNotNullParameter(callback, "callback");
        RequestPermissionListener requestPermissionListener = get__instance();
        if (callback.getFnJS() != null) {
            fnJS = callback.getFnJS();
        } else {
            callback.setFnJS(new Function1<UTSArray<String>, Unit>() { // from class: uts.sdk.modules.DCloudUniCreateRequestPermissionListener.RequestPermissionListenerByJsProxy.onCompleteByJs.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(UTSArray<String> uTSArray) throws SecurityException {
                    invoke2(uTSArray);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(UTSArray<String> permissions) throws SecurityException {
                    Intrinsics.checkNotNullParameter(permissions, "permissions");
                    callback.invoke(permissions);
                }
            });
            fnJS = callback.getFnJS();
        }
        Intrinsics.checkNotNull(fnJS, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'permissions')] io.dcloud.uts.UTSArray<kotlin.String>, kotlin.Unit>");
        requestPermissionListener.onComplete((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(fnJS, 1));
    }

    public void stopByJs() {
        get__instance().stop();
    }
}
