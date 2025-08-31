package uts.sdk.modules.DCloudUniNetwork;

import com.taobao.weex.bridge.WXBridgeManager;
import io.dcloud.uts.UTSCallback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016R\u001a\u0010\u0005\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004¨\u0006\u000e"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/UploadTaskByJsProxy;", "", "ins", "Luts/sdk/modules/DCloudUniNetwork/UploadTask;", "(Luts/sdk/modules/DCloudUniNetwork/UploadTask;)V", "__instance", "get__instance", "()Luts/sdk/modules/DCloudUniNetwork/UploadTask;", "set__instance", "abortByJs", "", "onProgressUpdateByJs", WXBridgeManager.METHOD_CALLBACK, "Lio/dcloud/uts/UTSCallback;", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UploadTaskByJsProxy {
    private UploadTask __instance;

    public UploadTask get__instance() {
        return this.__instance;
    }

    public void set__instance(UploadTask uploadTask) {
        Intrinsics.checkNotNullParameter(uploadTask, "<set-?>");
        this.__instance = uploadTask;
    }

    public UploadTaskByJsProxy(UploadTask ins) {
        Intrinsics.checkNotNullParameter(ins, "ins");
        set__instance(ins);
    }

    public void abortByJs() {
        get__instance().abort();
    }

    public void onProgressUpdateByJs(final UTSCallback callback) {
        Object fnJS;
        Intrinsics.checkNotNullParameter(callback, "callback");
        UploadTask uploadTask = get__instance();
        if (callback.getFnJS() != null) {
            fnJS = callback.getFnJS();
        } else {
            callback.setFnJS(new Function1<OnProgressUpdateResult, Unit>() { // from class: uts.sdk.modules.DCloudUniNetwork.UploadTaskByJsProxy.onProgressUpdateByJs.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(OnProgressUpdateResult onProgressUpdateResult) throws SecurityException {
                    invoke2(onProgressUpdateResult);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(OnProgressUpdateResult result) throws SecurityException {
                    Intrinsics.checkNotNullParameter(result, "result");
                    callback.invoke(result);
                }
            });
            fnJS = callback.getFnJS();
        }
        Intrinsics.checkNotNull(fnJS, "null cannot be cast to non-null type kotlin.Function1<@[ParameterName(name = 'result')] uts.sdk.modules.DCloudUniNetwork.OnProgressUpdateResult, kotlin.Unit>");
        uploadTask.onProgressUpdate((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(fnJS, 1));
    }
}
