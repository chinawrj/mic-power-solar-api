package io.dcloud.uts;

import io.dcloud.common.util.ExifInterface;
import io.dcloud.uts.UTSPromise;
import java.util.Iterator;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* compiled from: UTSPromise.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<no name provided>", "", ExifInterface.GPS_DIRECTION_TRUE, "resolve", "Lkotlin/Function;", "reject", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
final class UTSPromise$Companion$allSettled_origin$1 extends Lambda implements Function2<Function<?>, Function<?>, Unit> {
    final /* synthetic */ UTSArray<?> $arr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UTSPromise$Companion$allSettled_origin$1(UTSArray<?> uTSArray) {
        super(2);
        this.$arr = uTSArray;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Function<?> function, Function<?> function2) throws SecurityException {
        invoke2(function, function2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> void invoke$res(final UTSArray<java.lang.Object> uTSArray, final Ref.ObjectRef<Number> objectRef, final Function<?> function, final Number number, java.lang.Object obj) throws SecurityException {
        if (obj != null && (obj instanceof UTSPromise)) {
            ((UTSPromise) obj)._then(new Function1<java.lang.Object, Unit>() { // from class: io.dcloud.uts.UTSPromise$Companion$allSettled_origin$1$res$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    UTSPromise$Companion$allSettled_origin$1.invoke$res(uTSArray, objectRef, function, number, obj2);
                }
            }, new Function1<java.lang.Object, Unit>() { // from class: io.dcloud.uts.UTSPromise$Companion$allSettled_origin$1$res$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj2) throws SecurityException {
                    invoke2(obj2);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Number] */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(java.lang.Object obj2) throws SecurityException {
                    UTSArray<java.lang.Object> uTSArray2 = uTSArray;
                    Number number2 = number;
                    Intrinsics.checkNotNull(number2, "null cannot be cast to non-null type kotlin.Int");
                    uTSArray2.set(((Integer) number2).intValue(), (int) new UTSPromiseRejectedResultImpl(obj2));
                    Ref.ObjectRef<Number> objectRef2 = objectRef;
                    objectRef2.element = NumberKt.dec(objectRef2.element);
                    if (Intrinsics.areEqual((java.lang.Object) objectRef.element, (java.lang.Object) 0)) {
                        UTSPromiseKt.callFunction(function, UTSArrayKt.utsArrayOf(uTSArray));
                    }
                }
            });
            return;
        }
        Intrinsics.checkNotNull(number, "null cannot be cast to non-null type kotlin.Int");
        uTSArray.set(((Integer) number).intValue(), (int) new UTSPromiseFulfilledResultImpl(obj));
        objectRef.element = (T) NumberKt.dec(objectRef.element);
        if (Intrinsics.areEqual((java.lang.Object) objectRef.element, (java.lang.Object) 0)) {
            UTSPromiseKt.callFunction(function, UTSArrayKt.utsArrayOf(uTSArray));
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Number] */
    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Function<?> resolve, Function<?> reject) throws SecurityException {
        Intrinsics.checkNotNullParameter(resolve, "resolve");
        Intrinsics.checkNotNullParameter(reject, "reject");
        UTSArray uTSArray = new UTSArray();
        Integer numValueOf = 0;
        if (Intrinsics.areEqual(this.$arr.getLength(), numValueOf)) {
            UTSPromiseKt.callFunction(resolve, UTSArrayKt.utsArrayOf(uTSArray));
            return;
        }
        Iterator<?> it = this.$arr.iterator();
        while (it.hasNext()) {
            uTSArray.push(it.next());
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = uTSArray.getLength();
        UTSPromise.Companion companion = UTSPromise.INSTANCE;
        while (true) {
            Integer num = numValueOf;
            if (NumberKt.compareTo(num, uTSArray.getLength()) >= 0) {
                return;
            }
            invoke$res(uTSArray, objectRef, resolve, num, uTSArray.get(num.intValue()));
            numValueOf = Integer.valueOf(num.intValue() + 1);
        }
    }
}
