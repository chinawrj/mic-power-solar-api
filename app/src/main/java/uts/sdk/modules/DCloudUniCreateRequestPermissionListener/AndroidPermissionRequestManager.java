package uts.sdk.modules.DCloudUniCreateRequestPermissionListener;

import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSArray;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J5\u0010\u0018\u001a\u00020\n2+\u0010\u0019\u001a'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0004j\u0002`\u000bH\u0016J5\u0010\u001a\u001a\u00020\n2+\u0010\u0019\u001a'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0004j\u0002`\u0011H\u0016J5\u0010\u001b\u001a\u00020\n2+\u0010\u0019\u001a'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0004j\u0002`\u0015H\u0016J\b\u0010\u001c\u001a\u00020\nH\u0016RC\u0010\u0003\u001a+\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fRC\u0010\u0010\u001a+\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fRC\u0010\u0014\u001a+\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000f¨\u0006\u001d"}, d2 = {"Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/AndroidPermissionRequestManager;", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListener;", "()V", "completeCallback", "Lkotlin/Function1;", "Lio/dcloud/uts/UTSArray;", "", "Lkotlin/ParameterName;", "name", "permissions", "", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListenerCompleteCallback;", "getCompleteCallback", "()Lkotlin/jvm/functions/Function1;", "setCompleteCallback", "(Lkotlin/jvm/functions/Function1;)V", "confirmCallback", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListenerConfirmCallback;", "getConfirmCallback", "setConfirmCallback", "requestCallback", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListenerRequestCallback;", "getRequestCallback", "setRequestCallback", "onComplete", WXBridgeManager.METHOD_CALLBACK, "onConfirm", "onRequest", Constants.Value.STOP, "uni-createRequestPermissionListener_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class AndroidPermissionRequestManager implements RequestPermissionListener {
    private Function1<? super UTSArray<String>, Unit> completeCallback;
    private Function1<? super UTSArray<String>, Unit> confirmCallback;
    private Function1<? super UTSArray<String>, Unit> requestCallback;

    public Function1<UTSArray<String>, Unit> getRequestCallback() {
        return this.requestCallback;
    }

    public void setRequestCallback(Function1<? super UTSArray<String>, Unit> function1) {
        this.requestCallback = function1;
    }

    public Function1<UTSArray<String>, Unit> getConfirmCallback() {
        return this.confirmCallback;
    }

    public void setConfirmCallback(Function1<? super UTSArray<String>, Unit> function1) {
        this.confirmCallback = function1;
    }

    public Function1<UTSArray<String>, Unit> getCompleteCallback() {
        return this.completeCallback;
    }

    public void setCompleteCallback(Function1<? super UTSArray<String>, Unit> function1) {
        this.completeCallback = function1;
    }

    @Override // uts.sdk.modules.DCloudUniCreateRequestPermissionListener.RequestPermissionListener
    public void onRequest(Function1<? super UTSArray<String>, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (getRequestCallback() == null) {
            setRequestCallback(callback);
        } else {
            UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
            Function1<UTSArray<String>, Unit> requestCallback = getRequestCallback();
            Intrinsics.checkNotNull(requestCallback);
            uTSAndroid.offPermissionRequest(requestCallback);
            setRequestCallback(callback);
        }
        UTSAndroid uTSAndroid2 = UTSAndroid.INSTANCE;
        Function1<UTSArray<String>, Unit> requestCallback2 = getRequestCallback();
        Intrinsics.checkNotNull(requestCallback2);
        uTSAndroid2.onPermissionRequest(requestCallback2);
    }

    @Override // uts.sdk.modules.DCloudUniCreateRequestPermissionListener.RequestPermissionListener
    public void onConfirm(Function1<? super UTSArray<String>, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (getConfirmCallback() == null) {
            setConfirmCallback(callback);
        } else {
            UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
            Function1<UTSArray<String>, Unit> confirmCallback = getConfirmCallback();
            Intrinsics.checkNotNull(confirmCallback);
            uTSAndroid.offPermissionConfirm(confirmCallback);
            setConfirmCallback(callback);
        }
        UTSAndroid uTSAndroid2 = UTSAndroid.INSTANCE;
        Function1<UTSArray<String>, Unit> confirmCallback2 = getConfirmCallback();
        Intrinsics.checkNotNull(confirmCallback2);
        uTSAndroid2.onPermissionConfirm(confirmCallback2);
    }

    @Override // uts.sdk.modules.DCloudUniCreateRequestPermissionListener.RequestPermissionListener
    public void onComplete(Function1<? super UTSArray<String>, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (getCompleteCallback() == null) {
            setCompleteCallback(callback);
        } else {
            UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
            Function1<UTSArray<String>, Unit> completeCallback = getCompleteCallback();
            Intrinsics.checkNotNull(completeCallback);
            uTSAndroid.offPermissionComplete(completeCallback);
            setCompleteCallback(callback);
        }
        UTSAndroid uTSAndroid2 = UTSAndroid.INSTANCE;
        Function1<UTSArray<String>, Unit> completeCallback2 = getCompleteCallback();
        Intrinsics.checkNotNull(completeCallback2);
        uTSAndroid2.onPermissionComplete(completeCallback2);
    }

    @Override // uts.sdk.modules.DCloudUniCreateRequestPermissionListener.RequestPermissionListener
    public void stop() {
        if (getCompleteCallback() != null) {
            UTSAndroid uTSAndroid = UTSAndroid.INSTANCE;
            Function1<UTSArray<String>, Unit> completeCallback = getCompleteCallback();
            Intrinsics.checkNotNull(completeCallback);
            uTSAndroid.offPermissionComplete(completeCallback);
            setCompleteCallback(null);
        }
        if (getConfirmCallback() != null) {
            UTSAndroid uTSAndroid2 = UTSAndroid.INSTANCE;
            Function1<UTSArray<String>, Unit> confirmCallback = getConfirmCallback();
            Intrinsics.checkNotNull(confirmCallback);
            uTSAndroid2.offPermissionConfirm(confirmCallback);
            setConfirmCallback(null);
        }
        if (getRequestCallback() != null) {
            UTSAndroid uTSAndroid3 = UTSAndroid.INSTANCE;
            Function1<UTSArray<String>, Unit> requestCallback = getRequestCallback();
            Intrinsics.checkNotNull(requestCallback);
            uTSAndroid3.offPermissionRequest(requestCallback);
            setRequestCallback(null);
        }
    }
}
