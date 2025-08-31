package io.dcloud.uts;

import io.dcloud.uts.json.IJsonStringify;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UTSError.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0016\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u0005¢\u0006\u0002\u0010\nJ\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0005H\u0016R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0000X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0006R\u001a\u0010\u0013\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0006¨\u0006\u0019"}, d2 = {"Lio/dcloud/uts/UTSError;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Lio/dcloud/uts/json/IJsonStringify;", "message", "", "(Ljava/lang/String;)V", "options", "Lio/dcloud/uts/UTSJSONObject;", "(Ljava/lang/String;Lio/dcloud/uts/UTSJSONObject;)V", "()V", "cause", "getCause", "()Lio/dcloud/uts/UTSError;", "setCause", "(Lio/dcloud/uts/UTSError;)V", "getMessage", "()Ljava/lang/String;", "setMessage", "name", "getName", "setName", "toJSON", "", "toString", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UTSError extends Exception implements IJsonStringify {
    private UTSError cause;
    private String message;
    private String name;

    public UTSError() {
        this.name = "Error";
        this.message = "";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UTSError(String message) {
        this();
        Intrinsics.checkNotNullParameter(message, "message");
        setMessage(message);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UTSError(String message, UTSJSONObject options) {
        this();
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(options, "options");
        setMessage(message);
        java.lang.Object obj = options.get("cause");
        if (obj == null || !(obj instanceof UTSError)) {
            return;
        }
        setCause((UTSError) obj);
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    @Override // java.lang.Throwable
    public UTSError getCause() {
        return this.cause;
    }

    public void setCause(UTSError uTSError) {
        this.cause = uTSError;
    }

    public java.lang.Object toJSON() {
        UTSJSONObject uTSJSONObject = new UTSJSONObject();
        uTSJSONObject.set("name", this.name);
        uTSJSONObject.set("message", getMessage());
        if (getCause() != null) {
            if (getCause() instanceof IJsonStringify) {
                UTSError cause = getCause();
                Intrinsics.checkNotNull(cause, "null cannot be cast to non-null type io.dcloud.uts.json.IJsonStringify");
                uTSJSONObject.set("cause", cause.toJSON());
            } else {
                uTSJSONObject.set("cause", NumberKt.toString_number_nullable$default(getCause(), (Number) null, 1, (java.lang.Object) null));
            }
        }
        return uTSJSONObject;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "UTSError(name='" + this.name + "', message='" + getMessage() + "', cause='" + getCause() + "')";
    }
}
