package io.dcloud.uts;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UTSPromise.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0016\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0002\u001a\u00020\u0001X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\r¨\u0006\u0011"}, d2 = {"Lio/dcloud/uts/UTSPromiseAggregateError;", "", "errors", "message", "", "(Ljava/lang/Object;Ljava/lang/String;)V", "getErrors", "()Ljava/lang/Object;", "setErrors", "(Ljava/lang/Object;)V", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "name", "getName", "setName", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UTSPromiseAggregateError {
    private java.lang.Object errors;
    private String message;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public java.lang.Object getErrors() {
        return this.errors;
    }

    public void setErrors(java.lang.Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.errors = obj;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.message = str;
    }

    public UTSPromiseAggregateError(java.lang.Object errors, String message) {
        Intrinsics.checkNotNullParameter(errors, "errors");
        Intrinsics.checkNotNullParameter(message, "message");
        this.name = "AggregateError";
        setErrors(errors);
        setMessage(message);
    }

    public /* synthetic */ UTSPromiseAggregateError(java.lang.Object obj, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? "" : str);
    }
}
