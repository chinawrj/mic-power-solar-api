package io.dcloud.uts;

import io.dcloud.common.util.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: UTSIterator.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0017\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lio/dcloud/uts/UTSIteratorResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "done", "", "value", "(ZLjava/lang/Object;)V", "getDone", "()Z", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSIteratorResult<T> {
    private final boolean done;
    private final T value;

    public UTSIteratorResult(boolean z, T t) {
        this.done = z;
        this.value = t;
    }

    public /* synthetic */ UTSIteratorResult(boolean z, java.lang.Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, obj);
    }

    public final boolean getDone() {
        return this.done;
    }

    public final T getValue() {
        return this.value;
    }
}
