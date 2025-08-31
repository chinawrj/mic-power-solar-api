package uts.sdk.modules.DCloudUniGetAppBaseInfo;

import com.taobao.weex.common.Constants;
import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSArray;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\t"}, d2 = {"Luts/sdk/modules/DCloudUniGetAppBaseInfo/GetAppBaseInfoOptions;", "Lio/dcloud/uts/UTSObject;", Constants.Name.FILTER, "Lio/dcloud/uts/UTSArray;", "", "(Lio/dcloud/uts/UTSArray;)V", "getFilter", "()Lio/dcloud/uts/UTSArray;", "setFilter", "uni-getAppBaseInfo_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class GetAppBaseInfoOptions extends UTSObject {

    @JsonNotNull
    private UTSArray<String> filter;

    public UTSArray<String> getFilter() {
        return this.filter;
    }

    public void setFilter(UTSArray<String> uTSArray) {
        Intrinsics.checkNotNullParameter(uTSArray, "<set-?>");
        this.filter = uTSArray;
    }

    public GetAppBaseInfoOptions(UTSArray<String> filter) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        this.filter = filter;
    }
}
