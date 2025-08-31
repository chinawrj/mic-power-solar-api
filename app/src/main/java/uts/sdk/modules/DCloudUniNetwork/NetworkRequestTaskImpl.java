package uts.sdk.modules.DCloudUniNetwork;

import androidx.core.app.NotificationCompat;
import io.dcloud.uts.Map;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.UTSAndroid;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import okhttp3.Call;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001a\u001a\u00020\u000eH\u0016J\u0012\u0010\u001b\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0012\u0010\u001e\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J/\u0010\u001f\u001a\u00020\u00062%\u0010 \u001a!\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000fH\u0016J/\u0010!\u001a\u00020\u00062%\u0010 \u001a!\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u0017H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000RE\u0010\u0007\u001a-\u0012\u0004\u0012\u00020\u0006\u0012#\u0012!\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u000f0\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000RE\u0010\u0015\u001a-\u0012\u0004\u0012\u00020\u0006\u0012#\u0012!\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tj\u0002`\u00170\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013¨\u0006\""}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/NetworkRequestTaskImpl;", "Luts/sdk/modules/DCloudUniNetwork/RequestTask;", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "(Lokhttp3/Call;)V", "chunkReceivedListenerId", "", "chunkReceivedListeners", "Lio/dcloud/uts/Map;", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnChunkReceivedListenerResult;", "Lkotlin/ParameterName;", "name", "result", "", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnChunkReceivedCallback;", "getChunkReceivedListeners", "()Lio/dcloud/uts/Map;", "setChunkReceivedListeners", "(Lio/dcloud/uts/Map;)V", "headersReceivedListenerId", "headersReceivedListeners", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnHeadersReceivedListenerResult;", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnHeadersReceivedCallback;", "getHeadersReceivedListeners", "setHeadersReceivedListeners", "abort", "offChunkReceived", "id", "", "offHeadersReceived", "onChunkReceived", "listener", "onHeadersReceived", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class NetworkRequestTaskImpl implements RequestTask {
    private Call call;
    private Number headersReceivedListenerId = (Number) 0;
    private Number chunkReceivedListenerId = (Number) 0;
    private Map<Number, Function1<RequestTaskOnHeadersReceivedListenerResult, Unit>> headersReceivedListeners = new Map<>();
    private Map<Number, Function1<RequestTaskOnChunkReceivedListenerResult, Unit>> chunkReceivedListeners = new Map<>();

    public Map<Number, Function1<RequestTaskOnHeadersReceivedListenerResult, Unit>> getHeadersReceivedListeners() {
        return this.headersReceivedListeners;
    }

    public void setHeadersReceivedListeners(Map<Number, Function1<RequestTaskOnHeadersReceivedListenerResult, Unit>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.headersReceivedListeners = map;
    }

    public Map<Number, Function1<RequestTaskOnChunkReceivedListenerResult, Unit>> getChunkReceivedListeners() {
        return this.chunkReceivedListeners;
    }

    public void setChunkReceivedListeners(Map<Number, Function1<RequestTaskOnChunkReceivedListenerResult, Unit>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.chunkReceivedListeners = map;
    }

    public NetworkRequestTaskImpl(Call call) {
        this.call = call;
    }

    @Override // uts.sdk.modules.DCloudUniNetwork.RequestTask
    public void abort() {
        Call call = this.call;
        if (call == null || call == null) {
            return;
        }
        call.cancel();
    }

    @Override // uts.sdk.modules.DCloudUniNetwork.RequestTask
    public Number onHeadersReceived(Function1<? super RequestTaskOnHeadersReceivedListenerResult, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.headersReceivedListenerId = NumberKt.inc(this.headersReceivedListenerId);
        getHeadersReceivedListeners().put(this.headersReceivedListenerId, listener);
        return this.headersReceivedListenerId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.lang.Integer] */
    @Override // uts.sdk.modules.DCloudUniNetwork.RequestTask
    public void offHeadersReceived(final Object id) {
        if (id != null) {
            if (Intrinsics.areEqual(UTSAndroid.INSTANCE.typeof(id), "function")) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = -1;
                getHeadersReceivedListeners().forEach(new Function2<Function1<? super RequestTaskOnHeadersReceivedListenerResult, ? extends Unit>, Number, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.NetworkRequestTaskImpl.offHeadersReceived.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Function1<? super RequestTaskOnHeadersReceivedListenerResult, ? extends Unit> function1, Number number) {
                        invoke2((Function1<? super RequestTaskOnHeadersReceivedListenerResult, Unit>) function1, number);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Function1<? super RequestTaskOnHeadersReceivedListenerResult, Unit> value, Number key1) {
                        Intrinsics.checkNotNullParameter(value, "value");
                        Intrinsics.checkNotNullParameter(key1, "key1");
                        Object obj = id;
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'result')] uts.sdk.modules.DCloudUniNetwork.RequestTaskOnHeadersReceivedListenerResult, kotlin.Unit>{ uts.sdk.modules.DCloudUniNetwork.IndexKt.RequestTaskOnHeadersReceivedCallback }");
                        if (Intrinsics.areEqual((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1), value)) {
                            objectRef.element = key1;
                        }
                    }
                });
                if (NumberKt.numberEquals(objectRef.element, -1)) {
                    return;
                }
                getHeadersReceivedListeners().delete(objectRef.element);
                return;
            }
            getHeadersReceivedListeners().delete((Number) id);
            return;
        }
        setHeadersReceivedListeners(new Map<>());
    }

    @Override // uts.sdk.modules.DCloudUniNetwork.RequestTask
    public Number onChunkReceived(Function1<? super RequestTaskOnChunkReceivedListenerResult, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.chunkReceivedListenerId = NumberKt.inc(this.chunkReceivedListenerId);
        getChunkReceivedListeners().put(this.chunkReceivedListenerId, listener);
        return this.chunkReceivedListenerId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.Integer] */
    @Override // uts.sdk.modules.DCloudUniNetwork.RequestTask
    public void offChunkReceived(final Object id) {
        if (id != null) {
            if (Intrinsics.areEqual(UTSAndroid.INSTANCE.typeof(id), "function")) {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = -1;
                getChunkReceivedListeners().forEach(new Function2<Function1<? super RequestTaskOnChunkReceivedListenerResult, ? extends Unit>, Number, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.NetworkRequestTaskImpl.offChunkReceived.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Function1<? super RequestTaskOnChunkReceivedListenerResult, ? extends Unit> function1, Number number) {
                        invoke2((Function1<? super RequestTaskOnChunkReceivedListenerResult, Unit>) function1, number);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Function1<? super RequestTaskOnChunkReceivedListenerResult, Unit> value, Number key1) {
                        Intrinsics.checkNotNullParameter(value, "value");
                        Intrinsics.checkNotNullParameter(key1, "key1");
                        Object obj = id;
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'result')] uts.sdk.modules.DCloudUniNetwork.RequestTaskOnChunkReceivedListenerResult, kotlin.Unit>{ uts.sdk.modules.DCloudUniNetwork.IndexKt.RequestTaskOnChunkReceivedCallback }");
                        if (Intrinsics.areEqual((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1), value)) {
                            objectRef.element = key1;
                        }
                    }
                });
                getChunkReceivedListeners().delete(objectRef.element);
                return;
            }
            getChunkReceivedListeners().delete((Number) id);
            return;
        }
        setChunkReceivedListeners(new Map<>());
    }
}
