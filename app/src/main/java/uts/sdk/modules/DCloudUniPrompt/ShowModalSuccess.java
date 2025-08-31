package uts.sdk.modules.DCloudUniPrompt;

import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.facebook.common.util.UriUtil;
import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u001e\u0010\u0004\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0002\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/ShowModalSuccess;", "Lio/dcloud/uts/UTSObject;", "confirm", "", BindingXConstants.STATE_CANCEL, UriUtil.LOCAL_CONTENT_SCHEME, "", "(ZZLjava/lang/String;)V", "getCancel", "()Z", "setCancel", "(Z)V", "getConfirm", "setConfirm", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ShowModalSuccess extends UTSObject {

    @JsonNotNull
    private boolean cancel;

    @JsonNotNull
    private boolean confirm;
    private String content;

    public ShowModalSuccess() {
        this(false, false, null, 7, null);
    }

    public /* synthetic */ ShowModalSuccess(boolean z, boolean z2, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? null : str);
    }

    public boolean getConfirm() {
        return this.confirm;
    }

    public void setConfirm(boolean z) {
        this.confirm = z;
    }

    public boolean getCancel() {
        return this.cancel;
    }

    public void setCancel(boolean z) {
        this.cancel = z;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public ShowModalSuccess(boolean z, boolean z2, String str) {
        this.confirm = z;
        this.cancel = z2;
        this.content = str;
    }
}
