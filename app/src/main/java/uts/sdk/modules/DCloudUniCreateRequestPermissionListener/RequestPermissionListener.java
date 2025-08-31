package uts.sdk.modules.DCloudUniCreateRequestPermissionListener;

import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import io.dcloud.uts.UTSArray;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: index.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\u00020\u00032+\u0010\u0004\u001a'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u000bH&J5\u0010\f\u001a\u00020\u00032+\u0010\u0004\u001a'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\rH&J5\u0010\u000e\u001a\u00020\u00032+\u0010\u0004\u001a'\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u000fH&J\b\u0010\u0010\u001a\u00020\u0003H&¨\u0006\u0011"}, d2 = {"Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListener;", "", "onComplete", "", WXBridgeManager.METHOD_CALLBACK, "Lkotlin/Function1;", "Lio/dcloud/uts/UTSArray;", "", "Lkotlin/ParameterName;", "name", "permissions", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListenerCompleteCallback;", "onConfirm", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListenerConfirmCallback;", "onRequest", "Luts/sdk/modules/DCloudUniCreateRequestPermissionListener/RequestPermissionListenerRequestCallback;", Constants.Value.STOP, "uni-createRequestPermissionListener_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface RequestPermissionListener {
    void onComplete(Function1<? super UTSArray<String>, Unit> callback);

    void onConfirm(Function1<? super UTSArray<String>, Unit> callback);

    void onRequest(Function1<? super UTSArray<String>, Unit> callback);

    void stop();
}
