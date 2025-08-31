package io.dcloud.uts;

import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.DebugKt;

/* compiled from: UniCallbackWrapper.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004B\u0013\b\u0016\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\bJ$\u0010\u001d\u001a\u0004\u0018\u00010\u00012\u0012\u0010\u001e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001f\"\u00020\u0001H\u0086\u0002¢\u0006\u0002\u0010 J\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020$J\u001e\u0010%\u001a\u00020$2\u0016\u0010&\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\bR*\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR \u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0006R \u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0004R \u0010\u0016\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006'"}, d2 = {"Lio/dcloud/uts/UniCallbackWrapper;", "", "callFunc", "Lkotlin/reflect/KFunction;", "(Lkotlin/reflect/KFunction;)V", "Lkotlin/Function;", "(Lkotlin/Function;)V", "holderArgs", "", "", "getHolderArgs", "()Ljava/util/Map;", "setHolderArgs", "(Ljava/util/Map;)V", "holderFun", "getHolderFun", "()Lkotlin/Function;", "setHolderFun", "holderFunc", "getHolderFunc", "()Lkotlin/reflect/KFunction;", "setHolderFunc", "hostClass", "Ljava/lang/Class;", "getHostClass", "()Ljava/lang/Class;", "setHostClass", "(Ljava/lang/Class;)V", "getArgs", "invoke", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "isValid", "", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "", "setArgs", "param", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UniCallbackWrapper {
    private java.util.Map<String, ? extends java.lang.Object> holderArgs;
    private Function<?> holderFun;
    private KFunction<?> holderFunc;
    private Class<?> hostClass;

    public final KFunction<?> getHolderFunc() {
        return this.holderFunc;
    }

    public final void setHolderFunc(KFunction<?> kFunction) {
        this.holderFunc = kFunction;
    }

    public final Function<?> getHolderFun() {
        return this.holderFun;
    }

    public final void setHolderFun(Function<?> function) {
        this.holderFun = function;
    }

    public final Class<?> getHostClass() {
        return this.hostClass;
    }

    public final void setHostClass(Class<?> cls) {
        this.hostClass = cls;
    }

    public final java.util.Map<String, java.lang.Object> getHolderArgs() {
        return this.holderArgs;
    }

    public final void setHolderArgs(java.util.Map<String, ? extends java.lang.Object> map) {
        this.holderArgs = map;
    }

    public UniCallbackWrapper(KFunction<?> callFunc) {
        Intrinsics.checkNotNullParameter(callFunc, "callFunc");
        this.holderFunc = callFunc;
    }

    public UniCallbackWrapper(Function<?> callFunc) {
        Intrinsics.checkNotNullParameter(callFunc, "callFunc");
        this.holderFun = callFunc;
        this.hostClass = callFunc.getClass();
    }

    public final java.lang.Object invoke(java.lang.Object... args) {
        Method method;
        Method method2;
        Method[] declaredMethods;
        Intrinsics.checkNotNullParameter(args, "args");
        if (this.holderFun != null) {
            Class<?> cls = this.hostClass;
            if (cls == null || (declaredMethods = cls.getDeclaredMethods()) == null) {
                method = null;
                method2 = null;
            } else {
                method = null;
                method2 = null;
                for (Method method3 : declaredMethods) {
                    if (Intrinsics.areEqual("invoke", method3.getName())) {
                        if (method3.isBridge()) {
                            method2 = method3;
                        } else {
                            method = method3;
                        }
                    }
                }
            }
            if (method == null && method2 != null) {
                method = method2;
            }
            if (method != null) {
                int length = method.getParameterTypes().length;
                return (length == 1 && Intrinsics.areEqual("[Ljava.lang.Object;", method.getParameterTypes()[0].getName())) ? method.invoke(this.holderFun, args) : (args.length >= length && args.length <= length) ? method.invoke(this.holderFun, Arrays.copyOf(args, args.length)) : "";
            }
        }
        KFunction<?> kFunction = this.holderFunc;
        if (kFunction != null) {
            return kFunction.call(Arrays.copyOf(args, args.length));
        }
        return null;
    }

    public final void setArgs(java.util.Map<String, ? extends java.lang.Object> param) {
        this.holderArgs = param;
    }

    public final java.util.Map<String, java.lang.Object> getArgs() {
        return this.holderArgs;
    }

    public final void off() {
        this.holderFunc = null;
        this.holderFun = null;
        this.holderArgs = null;
    }

    public final boolean isValid() {
        return (this.holderFunc == null && this.holderFun == null && this.holderArgs == null) ? false : true;
    }
}
