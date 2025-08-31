package io.dcloud.uniapp;

import io.dcloud.uts.UTSError;
import io.dcloud.uts.UTSJSONObject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UniError.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0005¢\u0006\u0002\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0004¨\u0006\u0013"}, d2 = {"Lio/dcloud/uniapp/SourceError;", "Lio/dcloud/uts/UTSError;", "message", "", "(Ljava/lang/String;)V", "options", "Lio/dcloud/uts/UTSJSONObject;", "(Ljava/lang/String;Lio/dcloud/uts/UTSJSONObject;)V", "()V", "code", "", "getCode", "()Ljava/lang/Number;", "setCode", "(Ljava/lang/Number;)V", "subject", "getSubject", "()Ljava/lang/String;", "setSubject", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class SourceError extends UTSError {
    private Number code;
    private String subject;

    public SourceError() {
        this.code = (Number) 0;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SourceError(String message) {
        this();
        Intrinsics.checkNotNullParameter(message, "message");
        setMessage(message);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SourceError(String message, UTSJSONObject options) {
        this();
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(options, "options");
        setMessage(message);
        Object obj = options.get("cause");
        if (obj == null || !(obj instanceof UTSError)) {
            return;
        }
        setCause((UTSError) obj);
    }

    public final String getSubject() {
        return this.subject;
    }

    public final void setSubject(String str) {
        this.subject = str;
    }

    public final Number getCode() {
        return this.code;
    }

    public final void setCode(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this.code = number;
    }
}
