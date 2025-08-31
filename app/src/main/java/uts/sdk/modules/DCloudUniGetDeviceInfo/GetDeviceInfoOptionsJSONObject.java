package uts.sdk.modules.DCloudUniGetDeviceInfo;

import com.taobao.weex.common.Constants;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSJSONObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Luts/sdk/modules/DCloudUniGetDeviceInfo/GetDeviceInfoOptionsJSONObject;", "Lio/dcloud/uts/UTSJSONObject;", "()V", Constants.Name.FILTER, "Lio/dcloud/uts/UTSArray;", "", "getFilter", "()Lio/dcloud/uts/UTSArray;", "setFilter", "(Lio/dcloud/uts/UTSArray;)V", "uni-getDeviceInfo_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class GetDeviceInfoOptionsJSONObject extends UTSJSONObject {
    public UTSArray<String> filter;

    public UTSArray<String> getFilter() {
        UTSArray<String> uTSArray = this.filter;
        if (uTSArray != null) {
            return uTSArray;
        }
        Intrinsics.throwUninitializedPropertyAccessException(Constants.Name.FILTER);
        return null;
    }

    public void setFilter(UTSArray<String> uTSArray) {
        Intrinsics.checkNotNullParameter(uTSArray, "<set-?>");
        this.filter = uTSArray;
    }
}
