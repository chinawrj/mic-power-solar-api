package uts.sdk.modules.DCloudUniNetwork;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: index.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001H&J/\u0010\u0007\u001a\u00020\b2%\u0010\u0005\u001a!\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\tj\u0002`\u000eH&J/\u0010\u000f\u001a\u00020\b2%\u0010\u0005\u001a!\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\tj\u0002`\u0011H&¨\u0006\u0012"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/RequestTask;", "", "abort", "", "offChunkReceived", "listener", "offHeadersReceived", "onChunkReceived", "", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnChunkReceivedListenerResult;", "Lkotlin/ParameterName;", "name", "result", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnChunkReceivedCallback;", "onHeadersReceived", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnHeadersReceivedListenerResult;", "Luts/sdk/modules/DCloudUniNetwork/RequestTaskOnHeadersReceivedCallback;", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface RequestTask {
    void abort();

    void offChunkReceived(Object listener);

    void offHeadersReceived(Object listener);

    Number onChunkReceived(Function1<? super RequestTaskOnChunkReceivedListenerResult, Unit> listener);

    Number onHeadersReceived(Function1<? super RequestTaskOnHeadersReceivedListenerResult, Unit> listener);
}
