package uts.sdk.modules.DCloudUniPrompt;

import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001e\u0010\u0002\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Luts/sdk/modules/DCloudUniPrompt/ShowActionSheetSuccess;", "Lio/dcloud/uts/UTSObject;", "tapIndex", "", "(Ljava/lang/Number;)V", "getTapIndex", "()Ljava/lang/Number;", "setTapIndex", "uni-prompt_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class ShowActionSheetSuccess extends UTSObject {

    @JsonNotNull
    private Number tapIndex;

    public Number getTapIndex() {
        return this.tapIndex;
    }

    public void setTapIndex(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.tapIndex = number;
    }

    public ShowActionSheetSuccess(Number tapIndex) {
        Intrinsics.checkNotNullParameter(tapIndex, "tapIndex");
        this.tapIndex = tapIndex;
    }
}
