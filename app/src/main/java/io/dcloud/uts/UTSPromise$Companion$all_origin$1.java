package io.dcloud.uts;

import io.dcloud.common.util.ExifInterface;
import io.dcloud.uts.UTSPromise;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: UTSPromise.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00022!\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00010\u00042#\u0010\b\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0004H\n¢\u0006\u0002\b\u000b"}, d2 = {"<no name provided>", "", ExifInterface.GPS_DIRECTION_TRUE, "resolve", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "reject", "", "reason", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
final class UTSPromise$Companion$all_origin$1<T> extends Lambda implements Function2<Function1<? super T, ? extends Unit>, Function1<? super java.lang.Object, ? extends Unit>, Unit> {
    final /* synthetic */ UTSArray<?> $arr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UTSPromise$Companion$all_origin$1(UTSArray<?> uTSArray) {
        super(2);
        this.$arr = uTSArray;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj, Function1<? super java.lang.Object, ? extends Unit> function1) throws SecurityException {
        invoke((Function1) obj, (Function1<java.lang.Object, Unit>) function1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> void invoke$res(final Function1<java.lang.Object, Unit> function1, final UTSArray<java.lang.Object> uTSArray, final Ref.ObjectRef<Number> objectRef, final Function1<? super T, Unit> function12, final Number number, java.lang.Object obj) throws SecurityException {
        if (obj != null) {
            try {
                if (obj instanceof UTSPromise) {
                    ((UTSPromise) obj)._then(new Function1<java.lang.Object, Unit>() { // from class: io.dcloud.uts.UTSPromise$Companion$all_origin$1$res$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj2) throws SecurityException {
                            invoke2(obj2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(java.lang.Object obj2) throws SecurityException {
                            UTSPromise$Companion$all_origin$1.invoke$res(function1, uTSArray, objectRef, function12, number, obj2);
                        }
                    }, function1);
                    return;
                }
            } catch (Throwable th) {
                UTSPromiseKt.callFunction(function1, UTSArrayKt.utsArrayOf(th));
                return;
            }
        }
        Intrinsics.checkNotNull(number, "null cannot be cast to non-null type kotlin.Int");
        uTSArray.set(((Integer) number).intValue(), (int) obj);
        objectRef.element = (T) NumberKt.dec(objectRef.element);
        if (Intrinsics.areEqual((java.lang.Object) objectRef.element, (java.lang.Object) 0)) {
            UTSPromiseKt.callFunction(function12, UTSArrayKt.utsArrayOf(uTSArray));
        }
    }

    public final void invoke(Function1<? super T, Unit> resolve, Function1<java.lang.Object, Unit> reject) throws SecurityException {
        Intrinsics.checkNotNullParameter(resolve, "resolve");
        Intrinsics.checkNotNullParameter(reject, "reject");
        UTSArray uTSArray = new UTSArray();
        if (Intrinsics.areEqual((java.lang.Object) this.$arr.getLength(), (java.lang.Object) 0)) {
            UTSPromiseKt.callFunction(resolve, UTSArrayKt.utsArrayOf(uTSArray));
            return;
        }
        Iterator<?> it = this.$arr.iterator();
        while (it.hasNext()) {
            uTSArray.push(it.next());
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (T) uTSArray.getLength();
        UTSPromise.Companion companion = UTSPromise.INSTANCE;
        for (Integer numValueOf = (Number) 0; NumberKt.compareTo(numValueOf, uTSArray.getLength()) < 0; numValueOf = Integer.valueOf(numValueOf.intValue() + 1)) {
            invoke$res(reject, uTSArray, objectRef, resolve, numValueOf, uTSArray.get(numValueOf.intValue()));
        }
    }
}
