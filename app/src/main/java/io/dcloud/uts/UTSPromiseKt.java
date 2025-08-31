package io.dcloud.uts;

import io.dcloud.common.util.ExifInterface;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: UTSPromise.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a \u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004\u001a$\u0010\u0005\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u00072\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n\u001a\u0012\u0010\u000b\u001a\u00020\u00062\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n\u001a\u001a\u0010\f\u001a\u00020\u00062\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u001c\u0010\u0010\u001a\u00020\u00062\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001\u001a\u001c\u0010\u0012\u001a\u00020\u00062\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001¨\u0006\u0013"}, d2 = {"callFunction", "", "fn", "args", "Lio/dcloud/uts/UTSArray;", "doResolveUTSPromise", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/Function;", "self", "Lio/dcloud/uts/UTSPromise;", "finaleUTSPromise", "handleUTSPromise", "promise", "deferred", "Lio/dcloud/uts/UTSPromiseHandler;", "rejectUTSPromise", "newValue", "resolveUTSPromise", "utsplugin_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSPromiseKt {
    public static final java.lang.Object callFunction(final java.lang.Object fn, final UTSArray<java.lang.Object> args) throws SecurityException {
        Method method;
        Intrinsics.checkNotNullParameter(fn, "fn");
        Intrinsics.checkNotNullParameter(args, "args");
        if (fn instanceof Function) {
            Method[] declaredMethods = fn.getClass().getDeclaredMethods();
            Intrinsics.checkNotNullExpressionValue(declaredMethods, "fn.javaClass.declaredMethods");
            Method[] methodArr = declaredMethods;
            int length = methodArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    method = null;
                    break;
                }
                method = methodArr[i];
                Method method2 = method;
                if (Intrinsics.areEqual("invoke", method2.getName()) && !method2.isBridge()) {
                    break;
                }
                i++;
            }
            final Method method3 = method;
            if (method3 != null) {
                method3.setAccessible(true);
                return new Function0<java.lang.Object>() { // from class: io.dcloud.uts.UTSPromiseKt$callFunction$invoke$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final java.lang.Object invoke() throws Throwable {
                        UTSArray<java.lang.Object> uTSArraySlice = args.slice((Number) 0, Integer.valueOf(method3.getParameterTypes().length));
                        Method method4 = method3;
                        Intrinsics.checkNotNull(method4);
                        if (method4.isVarArgs()) {
                            int length2 = method3.getParameterTypes().length - 1;
                            Method method5 = method3;
                            Intrinsics.checkNotNull(method5);
                            Intrinsics.checkNotNull(method5);
                            Class<?> cls = method5.getParameterTypes()[length2];
                            Intrinsics.checkNotNull(cls);
                            Intrinsics.checkNotNull(cls);
                            Class<?> componentType = cls.getComponentType();
                            UTSArray uTSArraySlice$default = UTSArray.slice$default(args, Integer.valueOf(length2), null, 2, null);
                            Number length3 = uTSArraySlice$default.getLength();
                            Intrinsics.checkNotNull(length3, "null cannot be cast to non-null type kotlin.Int");
                            final java.lang.Object objNewInstance = Array.newInstance(componentType, ((Integer) length3).intValue());
                            uTSArraySlice$default.forEach(new Function2<java.lang.Object, Number, Unit>() { // from class: io.dcloud.uts.UTSPromiseKt$callFunction$invoke$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj, Number number) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
                                    invoke2(obj, number);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(java.lang.Object obj, Number index) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
                                    Intrinsics.checkNotNullParameter(index, "index");
                                    Array.set(objNewInstance, ((Integer) index).intValue(), obj);
                                }
                            });
                            if (NumberKt.compareTo(uTSArraySlice.getLength(), Integer.valueOf(length2)) > 0) {
                                uTSArraySlice.set(length2, (int) objNewInstance);
                            } else {
                                uTSArraySlice.push(objNewInstance);
                            }
                        }
                        try {
                            Method method6 = method3;
                            Intrinsics.checkNotNull(method6);
                            java.lang.Object obj = fn;
                            java.lang.Object[] array = uTSArraySlice.splice((Number) 0, Integer.valueOf(method3.getParameterTypes().length)).toArray(new java.lang.Object[0]);
                            return method6.invoke(obj, Arrays.copyOf(array, array.length));
                        } catch (Throwable th) {
                            if (th instanceof InvocationTargetException) {
                                InvocationTargetException invocationTargetException = th;
                                if (invocationTargetException.getCause() != null) {
                                    Throwable cause = invocationTargetException.getCause();
                                    Intrinsics.checkNotNull(cause);
                                    throw cause;
                                }
                            }
                            throw th;
                        }
                    }
                }.invoke();
            }
        }
        return null;
    }

    public static final <T> void doResolveUTSPromise(Function<?> fn, final UTSPromise<?> self) {
        Intrinsics.checkNotNullParameter(fn, "fn");
        Intrinsics.checkNotNullParameter(self, "self");
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        try {
            callFunction(fn, UTSArrayKt.utsArrayOf(new Function1<T, Unit>() { // from class: io.dcloud.uts.UTSPromiseKt.doResolveUTSPromise.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj) {
                    invoke2((AnonymousClass1<T>) obj);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(T t) {
                    if (booleanRef.element) {
                        return;
                    }
                    booleanRef.element = true;
                    UTSPromiseKt.resolveUTSPromise(self, t);
                }
            }, new Function1<java.lang.Object, Unit>() { // from class: io.dcloud.uts.UTSPromiseKt.doResolveUTSPromise.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj) {
                    invoke2(obj);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(java.lang.Object obj) {
                    if (booleanRef.element) {
                        return;
                    }
                    booleanRef.element = true;
                    UTSPromiseKt.rejectUTSPromise(self, obj);
                }
            }));
        } catch (Throwable th) {
            if (booleanRef.element) {
                return;
            }
            booleanRef.element = true;
            rejectUTSPromise(self, th);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v18, types: [T, io.dcloud.uts.UTSPromise] */
    public static final void handleUTSPromise(UTSPromise<?> promise, final UTSPromiseHandler deferred) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        Intrinsics.checkNotNullParameter(deferred, "deferred");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = promise;
        while (Intrinsics.areEqual((java.lang.Object) ((UTSPromise) objectRef.element).get_state(), (java.lang.Object) 3)) {
            java.lang.Object obj = ((UTSPromise) objectRef.element).get_value();
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type io.dcloud.uts.UTSPromise<*>");
            objectRef.element = (UTSPromise) obj;
        }
        if (Intrinsics.areEqual((java.lang.Object) ((UTSPromise) objectRef.element).get_state(), (java.lang.Object) 0)) {
            UTSArray<UTSPromiseHandler> uTSArray = ((UTSPromise) objectRef.element).get_deferreds();
            Intrinsics.checkNotNull(uTSArray);
            uTSArray.push(deferred);
        } else {
            ((UTSPromise) objectRef.element).set_handled(true);
            UTSPromise.INSTANCE._immediateFn(new Function0<Unit>() { // from class: io.dcloud.uts.UTSPromiseKt.handleUTSPromise.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Function<?> onRejected;
                    if (Intrinsics.areEqual((java.lang.Object) objectRef.element.get_state(), (java.lang.Object) 1)) {
                        onRejected = deferred.getOnFulfilled();
                    } else {
                        onRejected = deferred.getOnRejected();
                    }
                    if (onRejected == null) {
                        if (Intrinsics.areEqual((java.lang.Object) objectRef.element.get_state(), (java.lang.Object) 1)) {
                            UTSPromiseKt.resolveUTSPromise(deferred.getPromise(), objectRef.element.get_value());
                            return;
                        } else {
                            UTSPromiseKt.rejectUTSPromise(deferred.getPromise(), objectRef.element.get_value());
                            return;
                        }
                    }
                    try {
                        UTSPromiseKt.resolveUTSPromise(deferred.getPromise(), UTSPromiseKt.callFunction(onRejected, UTSArrayKt.utsArrayOf(objectRef.element.get_value())));
                    } catch (Throwable th) {
                        UTSPromiseKt.rejectUTSPromise(deferred.getPromise(), th);
                    }
                }
            });
        }
    }

    public static final void resolveUTSPromise(UTSPromise<?> self, java.lang.Object obj) {
        Intrinsics.checkNotNullParameter(self, "self");
        try {
            if (obj == self) {
                throw new UTSError("A promise cannot be resolved with itself.");
            }
            if (obj != null && (obj instanceof UTSPromise)) {
                self.set_state((Number) 3);
                self.set_value(obj);
                finaleUTSPromise(self);
            } else {
                self.set_state((Number) 1);
                self.set_value(obj);
                finaleUTSPromise(self);
            }
        } catch (Throwable th) {
            rejectUTSPromise(self, th);
        }
    }

    public static final void rejectUTSPromise(UTSPromise<?> self, java.lang.Object obj) {
        Intrinsics.checkNotNullParameter(self, "self");
        self.set_state((Number) 2);
        self.set_value(obj);
        finaleUTSPromise(self);
    }

    public static final void finaleUTSPromise(final UTSPromise<?> self) {
        Intrinsics.checkNotNullParameter(self, "self");
        if (Intrinsics.areEqual((java.lang.Object) self.get_state(), (java.lang.Object) 2)) {
            UTSArray<UTSPromiseHandler> uTSArray = self.get_deferreds();
            Intrinsics.checkNotNull(uTSArray);
            if (Intrinsics.areEqual((java.lang.Object) uTSArray.getLength(), (java.lang.Object) 0)) {
                UTSPromise.INSTANCE._immediateFn(new Function0<Unit>() { // from class: io.dcloud.uts.UTSPromiseKt.finaleUTSPromise.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        if (self.get_handled()) {
                            return;
                        }
                        UTSPromise.INSTANCE._unhandledRejectionFn(self.get_value());
                    }
                });
            }
        }
        UTSArray<UTSPromiseHandler> uTSArray2 = self.get_deferreds();
        Intrinsics.checkNotNull(uTSArray2);
        Number length = uTSArray2.getLength();
        for (Integer numValueOf = (Number) 0; NumberKt.compareTo(numValueOf, length) < 0; numValueOf = Integer.valueOf(numValueOf.intValue() + 1)) {
            UTSArray<UTSPromiseHandler> uTSArray3 = self.get_deferreds();
            Intrinsics.checkNotNull(uTSArray3);
            UTSPromiseHandler uTSPromiseHandler = uTSArray3.get(numValueOf.intValue());
            Intrinsics.checkNotNullExpressionValue(uTSPromiseHandler, "self._deferreds!![i as Int]");
            handleUTSPromise(self, uTSPromiseHandler);
        }
        self.set_deferreds(null);
    }
}
