package uts.sdk.modules.DCloudUniExit;

import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXImage;
import io.dcloud.uts.UTSCallback;
import io.dcloud.uts.UTSJSONObject;
import kotlin.Metadata;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Luts/sdk/modules/DCloudUniExit/ExitOptionsJSONObject;", "Lio/dcloud/uts/UTSJSONObject;", "()V", "complete", "Lio/dcloud/uts/UTSCallback;", "getComplete", "()Lio/dcloud/uts/UTSCallback;", "setComplete", "(Lio/dcloud/uts/UTSCallback;)V", Constants.Event.FAIL, "getFail", "setFail", WXImage.SUCCEED, "getSuccess", "setSuccess", "uni-exit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ExitOptionsJSONObject extends UTSJSONObject {
    private UTSCallback complete;
    private UTSCallback fail;
    private UTSCallback success;

    public UTSCallback getSuccess() {
        return this.success;
    }

    public void setSuccess(UTSCallback uTSCallback) {
        this.success = uTSCallback;
    }

    public UTSCallback getFail() {
        return this.fail;
    }

    public void setFail(UTSCallback uTSCallback) {
        this.fail = uTSCallback;
    }

    public UTSCallback getComplete() {
        return this.complete;
    }

    public void setComplete(UTSCallback uTSCallback) {
        this.complete = uTSCallback;
    }
}
