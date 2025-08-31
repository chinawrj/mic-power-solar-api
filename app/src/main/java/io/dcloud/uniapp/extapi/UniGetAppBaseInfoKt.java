package io.dcloud.uniapp.extapi;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import uts.sdk.modules.DCloudUniGetAppBaseInfo.GetAppBaseInfoOptions;
import uts.sdk.modules.DCloudUniGetAppBaseInfo.GetAppBaseInfoResult;
import uts.sdk.modules.DCloudUniGetAppBaseInfo.IndexKt;

/* compiled from: uniGetAppBaseInfo.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"2\u0010\u0000\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t*\n\u0010\n\"\u00020\u00022\u00020\u0002*\n\u0010\u000b\"\u00020\u00062\u00020\u0006¨\u0006\f"}, d2 = {"getAppBaseInfo", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniGetAppBaseInfo/GetAppBaseInfoOptions;", "Lkotlin/ParameterName;", "name", "options", "Luts/sdk/modules/DCloudUniGetAppBaseInfo/GetAppBaseInfoResult;", "Luts/sdk/modules/DCloudUniGetAppBaseInfo/GetAppBaseInfo;", "getGetAppBaseInfo", "()Lkotlin/jvm/functions/Function1;", "GetAppBaseInfoOptions", "GetAppBaseInfoResult", "uni-getAppBaseInfo_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UniGetAppBaseInfoKt {
    private static final Function1<GetAppBaseInfoOptions, GetAppBaseInfoResult> getAppBaseInfo = IndexKt.getGetAppBaseInfo();

    public static final Function1<GetAppBaseInfoOptions, GetAppBaseInfoResult> getGetAppBaseInfo() {
        return getAppBaseInfo;
    }
}
