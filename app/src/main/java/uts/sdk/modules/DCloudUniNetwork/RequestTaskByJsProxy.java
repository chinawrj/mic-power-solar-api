package uts.sdk.modules.DCloudUniNetwork;

import io.dcloud.uts.UTSCallback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0016J\u0012\u0010\r\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0010H\u0016R\u001a\u0010\u0005\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004¨\u0006\u0012"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/RequestTaskByJsProxy;", "", "ins", "Luts/sdk/modules/DCloudUniNetwork/RequestTask;", "(Luts/sdk/modules/DCloudUniNetwork/RequestTask;)V", "__instance", "get__instance", "()Luts/sdk/modules/DCloudUniNetwork/RequestTask;", "set__instance", "abortByJs", "", "offChunkReceivedByJs", "listener", "offHeadersReceivedByJs", "onChunkReceivedByJs", "", "Lio/dcloud/uts/UTSCallback;", "onHeadersReceivedByJs", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class RequestTaskByJsProxy {
    private RequestTask __instance;

    public RequestTask get__instance() {
        return this.__instance;
    }

    public void set__instance(RequestTask requestTask) {
        Intrinsics.checkNotNullParameter(requestTask, "<set-?>");
        this.__instance = requestTask;
    }

    public RequestTaskByJsProxy(RequestTask ins) {
        Intrinsics.checkNotNullParameter(ins, "ins");
        set__instance(ins);
    }

    public void abortByJs() {
        get__instance().abort();
    }

    public Number onChunkReceivedByJs(final UTSCallback listener) {
        Object fnJS;
        Intrinsics.checkNotNullParameter(listener, "listener");
        RequestTask requestTask = get__instance();
        if (listener.getFnJS() != null) {
            fnJS = listener.getFnJS();
        } else {
            listener.setFnJS(new Function1<RequestTaskOnChunkReceivedListenerResult, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.RequestTaskByJsProxy.onChunkReceivedByJs.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(RequestTaskOnChunkReceivedListenerResult requestTaskOnChunkReceivedListenerResult) throws SecurityException {
                    invoke2(requestTaskOnChunkReceivedListenerResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(RequestTaskOnChunkReceivedListenerResult result) throws SecurityException {
                    Intrinsics.checkNotNullParameter(result, "result");
                    listener.invoke(result);
                }
            });
            fnJS = listener.getFnJS();
        }
        Intrinsics.checkNotNull(fnJS, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'result')] uts.sdk.modules.DCloudUniNetwork.RequestTaskOnChunkReceivedListenerResult, kotlin.Unit>");
        return requestTask.onChunkReceived((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(fnJS, 1));
    }

    public void offChunkReceivedByJs(Object listener) {
        get__instance().offChunkReceived(listener);
    }

    public Number onHeadersReceivedByJs(final UTSCallback listener) {
        Object fnJS;
        Intrinsics.checkNotNullParameter(listener, "listener");
        RequestTask requestTask = get__instance();
        if (listener.getFnJS() != null) {
            fnJS = listener.getFnJS();
        } else {
            listener.setFnJS(new Function1<RequestTaskOnHeadersReceivedListenerResult, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.RequestTaskByJsProxy.onHeadersReceivedByJs.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(RequestTaskOnHeadersReceivedListenerResult requestTaskOnHeadersReceivedListenerResult) throws SecurityException {
                    invoke2(requestTaskOnHeadersReceivedListenerResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(RequestTaskOnHeadersReceivedListenerResult result) throws SecurityException {
                    Intrinsics.checkNotNullParameter(result, "result");
                    listener.invoke(result);
                }
            });
            fnJS = listener.getFnJS();
        }
        Intrinsics.checkNotNull(fnJS, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'result')] uts.sdk.modules.DCloudUniNetwork.RequestTaskOnHeadersReceivedListenerResult, kotlin.Unit>");
        return requestTask.onHeadersReceived((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(fnJS, 1));
    }

    public void offHeadersReceivedByJs(Object listener) {
        get__instance().offHeadersReceived(listener);
    }
}
