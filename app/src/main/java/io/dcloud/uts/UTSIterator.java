package io.dcloud.uts;

import io.dcloud.common.util.ExifInterface;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: UTSIterator.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\rH\u0096\u0002J\u000e\u0010\u0003\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0014R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lio/dcloud/uts/UTSIterator;", ExifInterface.GPS_DIRECTION_TRUE, "", "next", "Lkotlin/Function0;", "Lio/dcloud/uts/UTSIteratorResult;", "(Lkotlin/jvm/functions/Function0;)V", "cacheNext", "getCacheNext", "()Lio/dcloud/uts/UTSIteratorResult;", "setCacheNext", "(Lio/dcloud/uts/UTSIteratorResult;)V", "hasNext", "", "getHasNext", "()Z", "setHasNext", "(Z)V", "getNext", "()Lkotlin/jvm/functions/Function0;", "()Ljava/lang/Object;", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class UTSIterator<T> implements Iterator<T>, KMappedMarker {
    private UTSIteratorResult<T> cacheNext;
    private boolean hasNext;
    private final Function0<UTSIteratorResult<T>> next;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public UTSIterator(Function0<UTSIteratorResult<T>> next) {
        Intrinsics.checkNotNullParameter(next, "next");
        this.next = next;
        Intrinsics.checkNotNull(next);
        UTSIteratorResult<T> uTSIteratorResultInvoke = next.invoke();
        this.cacheNext = uTSIteratorResultInvoke;
        Intrinsics.checkNotNull(uTSIteratorResultInvoke);
        this.hasNext = !uTSIteratorResultInvoke.getDone();
    }

    public final Function0<UTSIteratorResult<T>> getNext() {
        return this.next;
    }

    public final UTSIteratorResult<T> getCacheNext() {
        return this.cacheNext;
    }

    public final void setCacheNext(UTSIteratorResult<T> uTSIteratorResult) {
        this.cacheNext = uTSIteratorResult;
    }

    public final boolean getHasNext() {
        return this.hasNext;
    }

    public final void setHasNext(boolean z) {
        this.hasNext = z;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public T next() {
        UTSIteratorResult<T> uTSIteratorResult = this.cacheNext;
        if (uTSIteratorResult != null) {
            Intrinsics.checkNotNull(uTSIteratorResult);
            T value = uTSIteratorResult.getValue();
            Function0<UTSIteratorResult<T>> function0 = this.next;
            Intrinsics.checkNotNull(function0);
            UTSIteratorResult<T> uTSIteratorResultInvoke = function0.invoke();
            this.cacheNext = uTSIteratorResultInvoke;
            Intrinsics.checkNotNull(uTSIteratorResultInvoke);
            this.hasNext = !uTSIteratorResultInvoke.getDone();
            return value;
        }
        Function0<UTSIteratorResult<T>> function02 = this.next;
        Intrinsics.checkNotNull(function02);
        UTSIteratorResult<T> uTSIteratorResultInvoke2 = function02.invoke();
        if (uTSIteratorResultInvoke2.getDone()) {
            this.hasNext = false;
        }
        return uTSIteratorResultInvoke2.getValue();
    }
}
