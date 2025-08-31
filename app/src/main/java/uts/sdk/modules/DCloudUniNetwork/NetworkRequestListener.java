package uts.sdk.modules.DCloudUniNetwork;

import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.uts.UTSJSONObject;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J*\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\r0\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\tH\u0016J\b\u0010\u0010\u001a\u00020\u0004H\u0016¨\u0006\u0011"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/NetworkRequestListener;", "", "()V", "onComplete", "", AbsoluteConst.JSON_KEY_OPTION, "Lio/dcloud/uts/UTSJSONObject;", "onHeadersReceived", "statusCode", "", "headers", "", "", "", "onProgress", "progress", "onStart", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class NetworkRequestListener {
    public void onComplete(UTSJSONObject option) {
        Intrinsics.checkNotNullParameter(option, "option");
    }

    public void onHeadersReceived(Number statusCode, Map<String, List<String>> headers) {
        Intrinsics.checkNotNullParameter(statusCode, "statusCode");
        Intrinsics.checkNotNullParameter(headers, "headers");
    }

    public void onProgress(Number progress) {
        Intrinsics.checkNotNullParameter(progress, "progress");
    }

    public void onStart() {
    }
}
