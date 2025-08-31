package uts.sdk.modules.DCloudUniNetwork;

import com.taobao.weex.adapter.IWXUserTrackAdapter;
import io.dcloud.uniapp.UniError;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0013\b\u0016\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0002\u0010\u0006R\u001e\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\n"}, d2 = {"Luts/sdk/modules/DCloudUniNetwork/DownloadFileFailImpl;", "Lio/dcloud/uniapp/UniError;", "Luts/sdk/modules/DCloudUniNetwork/DownloadFileFail;", IWXUserTrackAdapter.MONITOR_ERROR_CODE, "", "Luts/sdk/modules/DCloudUniNetwork/RequestErrorCode;", "(Ljava/lang/Number;)V", "getErrCode", "()Ljava/lang/Number;", "setErrCode", "uni-network_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class DownloadFileFailImpl extends UniError implements DownloadFileFail {
    private Number errCode;

    @Override // io.dcloud.uniapp.UniError, io.dcloud.uniapp.IUniError
    public Number getErrCode() {
        return this.errCode;
    }

    @Override // io.dcloud.uniapp.UniError, io.dcloud.uniapp.IUniError
    public void setErrCode(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.errCode = number;
    }

    public DownloadFileFailImpl(Number errCode) {
        Intrinsics.checkNotNullParameter(errCode, "errCode");
        setErrSubject("uni-downloadFile");
        setErrCode(errCode);
        String str = IndexKt.getNetWorkUniErrors().get(errCode);
        setErrMsg(str == null ? "" : str);
    }
}
