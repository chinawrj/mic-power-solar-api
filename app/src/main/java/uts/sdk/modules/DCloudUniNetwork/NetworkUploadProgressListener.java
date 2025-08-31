package uts.sdk.modules.DCloudUniNetwork;

import io.dcloud.uts.NumberKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/NetworkUploadProgressListener;", "Luts/sdk/modules/DCloudUniNetwork/UploadProgressListener;", "listener", "Luts/sdk/modules/DCloudUniNetwork/NetworkUploadFileListener;", "(Luts/sdk/modules/DCloudUniNetwork/NetworkUploadFileListener;)V", "onProgress", "", "bytesWritten", "", "contentLength", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class NetworkUploadProgressListener implements UploadProgressListener {
    private NetworkUploadFileListener listener;

    public NetworkUploadProgressListener(NetworkUploadFileListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // uts.sdk.modules.DCloudUniNetwork.UploadProgressListener
    public void onProgress(Number bytesWritten, Number contentLength) {
        Intrinsics.checkNotNullParameter(bytesWritten, "bytesWritten");
        Intrinsics.checkNotNullParameter(contentLength, "contentLength");
        OnProgressUpdateResult onProgressUpdateResult = new OnProgressUpdateResult(Integer.valueOf(NumberKt.times(NumberKt.div(Float.valueOf(bytesWritten.floatValue()), contentLength), (Number) 100).intValue()), bytesWritten, contentLength);
        NetworkUploadFileListener networkUploadFileListener = this.listener;
        if (networkUploadFileListener != null) {
            networkUploadFileListener.onProgress(onProgressUpdateResult);
        }
    }
}
