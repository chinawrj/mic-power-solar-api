package uts.sdk.modules.DCloudUniPrompt;

import com.taobao.weex.adapter.IWXUserTrackAdapter;
import io.dcloud.uniapp.UniError;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\b\u0016\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u001e\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/PromptErrorImpl;", "Lio/dcloud/uniapp/UniError;", "Luts/sdk/modules/DCloudUniPrompt/IPromptError;", IWXUserTrackAdapter.MONITOR_ERROR_CODE, "", "Luts/sdk/modules/DCloudUniPrompt/PromptErrorCode;", IWXUserTrackAdapter.MONITOR_ERROR_MSG, "", "(Ljava/lang/Number;Ljava/lang/String;)V", "getErrCode", "()Ljava/lang/Number;", "setErrCode", "(Ljava/lang/Number;)V", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class PromptErrorImpl extends UniError implements IPromptError {
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

    public PromptErrorImpl(Number errCode, String errMsg) {
        Intrinsics.checkNotNullParameter(errCode, "errCode");
        Intrinsics.checkNotNullParameter(errMsg, "errMsg");
        setErrSubject("uni-prompt");
        setErrCode(errCode);
        setErrMsg(errMsg);
    }
}
