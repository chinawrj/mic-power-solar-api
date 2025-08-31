package uts.sdk.modules.DCloudUniPrivacy;

import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001e\u0010\u0002\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Luts/sdk/modules/DCloudUniPrivacy/PrivacyChangeResult;", "Lio/dcloud/uts/UTSObject;", "needAuthorization", "", "(Z)V", "getNeedAuthorization", "()Z", "setNeedAuthorization", "uni-privacy_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class PrivacyChangeResult extends UTSObject {

    @JsonNotNull
    private boolean needAuthorization;

    public PrivacyChangeResult() {
        this(false, 1, null);
    }

    public /* synthetic */ PrivacyChangeResult(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public boolean getNeedAuthorization() {
        return this.needAuthorization;
    }

    public void setNeedAuthorization(boolean z) {
        this.needAuthorization = z;
    }

    public PrivacyChangeResult(boolean z) {
        this.needAuthorization = z;
    }
}
