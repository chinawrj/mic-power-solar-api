package uts.sdk.modules.DCloudUniNetwork;

import androidx.core.app.NotificationCompat;
import io.dcloud.common.constant.AbsoluteConst;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;

/* compiled from: index.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J/\u0010\t\u001a\u00020\b2%\u0010\n\u001a!\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\b0\u000bj\u0002`\u0010H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/NetworkUploadTaskImpl;", "Luts/sdk/modules/DCloudUniNetwork/UploadTask;", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "listener", "Luts/sdk/modules/DCloudUniNetwork/NetworkUploadFileListener;", "(Lokhttp3/Call;Luts/sdk/modules/DCloudUniNetwork/NetworkUploadFileListener;)V", "abort", "", "onProgressUpdate", AbsoluteConst.JSON_KEY_OPTION, "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniNetwork/OnProgressUpdateResult;", "Lkotlin/ParameterName;", "name", "result", "Luts/sdk/modules/DCloudUniNetwork/UploadFileProgressUpdateCallback;", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class NetworkUploadTaskImpl implements UploadTask {
    private Call call;
    private NetworkUploadFileListener listener;

    public NetworkUploadTaskImpl(Call call, NetworkUploadFileListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.call = call;
        this.listener = listener;
    }

    @Override // uts.sdk.modules.DCloudUniNetwork.UploadTask
    public void abort() {
        Call call = this.call;
        if (call == null || call == null) {
            return;
        }
        call.cancel();
    }

    @Override // uts.sdk.modules.DCloudUniNetwork.UploadTask
    public void onProgressUpdate(Function1<? super OnProgressUpdateResult, Unit> option) {
        Intrinsics.checkNotNullParameter(option, "option");
        NetworkUploadFileListener networkUploadFileListener = this.listener;
        if (networkUploadFileListener != null) {
            networkUploadFileListener.getProgressListeners().add(option);
        }
    }
}
