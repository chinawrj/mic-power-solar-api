package io.dcloud.uts;

import io.dcloud.common.util.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UTSPromise.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0003\u001a\u00028\u0000X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u0004¨\u0006\u000f"}, d2 = {"Lio/dcloud/uts/UTSPromiseFulfilledResultImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/dcloud/uts/UTSPromiseFulfilledResult;", "value", "(Ljava/lang/Object;)V", "status", "", "getStatus", "()Ljava/lang/String;", "setStatus", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/Object;", "setValue", "Ljava/lang/Object;", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UTSPromiseFulfilledResultImpl<T> implements UTSPromiseFulfilledResult<T> {
    private String status = "fulfilled";
    private T value;

    @Override // io.dcloud.uts.UTSPromiseSettledResult
    public String getStatus() {
        return this.status;
    }

    @Override // io.dcloud.uts.UTSPromiseSettledResult
    public void setStatus(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.status = str;
    }

    @Override // io.dcloud.uts.UTSPromiseFulfilledResult
    public T getValue() {
        return this.value;
    }

    @Override // io.dcloud.uts.UTSPromiseFulfilledResult
    public void setValue(T t) {
        this.value = t;
    }

    public UTSPromiseFulfilledResultImpl(T t) {
        setValue(t);
    }
}
