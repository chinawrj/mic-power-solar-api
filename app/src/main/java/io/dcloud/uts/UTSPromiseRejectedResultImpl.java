package io.dcloud.uts;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UTSPromise.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001a\u0010\b\u001a\u00020\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/dcloud/uts/UTSPromiseRejectedResultImpl;", "Lio/dcloud/uts/UTSPromiseRejectedResult;", "reason", "", "(Ljava/lang/Object;)V", "getReason", "()Ljava/lang/Object;", "setReason", "status", "", "getStatus", "()Ljava/lang/String;", "setStatus", "(Ljava/lang/String;)V", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UTSPromiseRejectedResultImpl implements UTSPromiseRejectedResult {
    private java.lang.Object reason;
    private String status = "rejected";

    @Override // io.dcloud.uts.UTSPromiseSettledResult
    public String getStatus() {
        return this.status;
    }

    @Override // io.dcloud.uts.UTSPromiseSettledResult
    public void setStatus(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.status = str;
    }

    @Override // io.dcloud.uts.UTSPromiseRejectedResult
    public java.lang.Object getReason() {
        return this.reason;
    }

    @Override // io.dcloud.uts.UTSPromiseRejectedResult
    public void setReason(java.lang.Object obj) {
        this.reason = obj;
    }

    public UTSPromiseRejectedResultImpl(java.lang.Object obj) {
        setReason(obj);
    }
}
