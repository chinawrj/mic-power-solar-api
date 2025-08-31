package io.dcloud.uts;

import androidx.core.app.NotificationCompat;
import com.facebook.common.util.UriUtil;
import com.taobao.weex.bridge.WXBridgeManager;
import io.dcloud.common.util.ExifInterface;
import io.dcloud.uts.UTSPromise;
import java.util.Iterator;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UTSPromise.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0004\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 <*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001<BE\b\u0016\u0012<\u0010\u0003\u001a8\u0012.\u0012,\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\b0\u0004¢\u0006\u0002\u0010\nBw\b\u0016\u0012n\u0010\u0003\u001aj\u0012.\u0012,\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u00120\u0012.\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\b0\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\b0\u000b¢\u0006\u0002\u0010\u000eJ\"\u0010'\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2\f\u0010)\u001a\b\u0012\u0002\b\u0003\u0018\u00010*H\u0002J4\u0010+\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2\u000e\b\u0002\u0010,\u001a\b\u0012\u0002\b\u0003\u0018\u00010*2\u000e\b\u0002\u0010)\u001a\b\u0012\u0002\b\u0003\u0018\u00010*H\u0002J\u000e\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016J\u0019\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000\"\u0004\b\u0001\u0010(H\u0017¢\u0006\u0002\b.J\"\u0010-\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H(0/H\u0016J-\u0010-\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2\u0012\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H(0\u00000/H\u0017¢\u0006\u0002\b0J9\u0010-\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2#\u0010)\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(1\u0012\u0004\u0012\u0002H(0\u0004H\u0016JD\u0010-\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2)\u0010)\u001a%\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(1\u0012\n\u0012\b\u0012\u0004\u0012\u0002H(0\u00000\u0004H\u0017¢\u0006\u0002\b2J$\u00103\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2\u000e\b\u0002\u0010)\u001a\b\u0012\u0002\b\u0003\u0018\u00010*H\u0016J\u0014\u00104\u001a\u00020\b2\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030*H\u0016J\u001a\u00105\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\u00106\u001a\u0006\u0012\u0002\b\u00030*H\u0016J\u000e\u00107\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016J\u0019\u00107\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000\"\u0004\b\u0001\u0010(H\u0017¢\u0006\u0002\b8J2\u00107\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2\f\u0010,\u001a\b\u0012\u0004\u0012\u0002H(0/2\u000e\b\u0002\u0010)\u001a\b\u0012\u0002\b\u0003\u0018\u00010*H\u0016J=\u00107\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2\u0012\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H(0\u00000/2\u000e\b\u0002\u0010)\u001a\b\u0012\u0002\b\u0003\u0018\u00010*H\u0017¢\u0006\u0002\b9JG\u00107\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2!\u0010,\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(1\u0012\u0004\u0012\u0002H(0\u00042\u000e\b\u0002\u0010)\u001a\b\u0012\u0002\b\u0003\u0018\u00010*H\u0016JR\u00107\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2'\u0010,\u001a#\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(1\u0012\n\u0012\b\u0012\u0004\u0012\u0002H(0\u00000\u00042\u000e\b\u0002\u0010)\u001a\b\u0012\u0002\b\u0003\u0018\u00010*H\u0017¢\u0006\u0002\b:J4\u0010;\u001a\b\u0012\u0004\u0012\u0002H(0\u0000\"\u0004\b\u0001\u0010(2\u000e\b\u0002\u0010,\u001a\b\u0012\u0002\b\u0003\u0018\u00010*2\u000e\b\u0002\u0010)\u001a\b\u0012\u0002\b\u0003\u0018\u00010*H\u0016R\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006="}, d2 = {"Lio/dcloud/uts/UTSPromise;", ExifInterface.GPS_DIRECTION_TRUE, "", "fn", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "", "resolve", "(Lkotlin/jvm/functions/Function1;)V", "Lkotlin/Function2;", "reason", "reject", "(Lkotlin/jvm/functions/Function2;)V", "_deferreds", "Lio/dcloud/uts/UTSArray;", "Lio/dcloud/uts/UTSPromiseHandler;", "get_deferreds", "()Lio/dcloud/uts/UTSArray;", "set_deferreds", "(Lio/dcloud/uts/UTSArray;)V", "_handled", "", "get_handled", "()Z", "set_handled", "(Z)V", "_state", "", "get_state", "()Ljava/lang/Number;", "set_state", "(Ljava/lang/Number;)V", "_value", "get_value", "()Ljava/lang/Object;", "set_value", "(Ljava/lang/Object;)V", "_catch", "R", "onRejected", "Lkotlin/Function;", "_then", "onFulfilled", "catch", "catch0", "Lkotlin/Function0;", "catch1", UriUtil.LOCAL_RESOURCE_SCHEME, "catch2", "catch_origin", "constructor_origin", "finally", WXBridgeManager.METHOD_CALLBACK, "then", "then0", "then1", "then2", "then_origin", "Companion", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UTSPromise<T> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private UTSArray<UTSPromiseHandler> _deferreds;
    private boolean _handled;
    private Number _state;
    private java.lang.Object _value;

    public UTSPromise(Function1<? super Function1<? super T, Unit>, Unit> fn) {
        Intrinsics.checkNotNullParameter(fn, "fn");
        this._state = (Number) 0;
        this._deferreds = new UTSArray<>();
        constructor_origin(fn);
    }

    public UTSPromise(Function2<? super Function1<? super T, Unit>, ? super Function1<java.lang.Object, Unit>, Unit> fn) {
        Intrinsics.checkNotNullParameter(fn, "fn");
        this._state = (Number) 0;
        this._deferreds = new UTSArray<>();
        constructor_origin(fn);
    }

    public void constructor_origin(Function<?> fn) {
        Intrinsics.checkNotNullParameter(fn, "fn");
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type io.dcloud.uts.UTSPromise<*>");
        UTSPromiseKt.doResolveUTSPromise(fn, this);
    }

    public Number get_state() {
        return this._state;
    }

    public void set_state(Number number) {
        Intrinsics.checkNotNullParameter(number, "<set-?>");
        this._state = number;
    }

    public boolean get_handled() {
        return this._handled;
    }

    public void set_handled(boolean z) {
        this._handled = z;
    }

    public java.lang.Object get_value() {
        return this._value;
    }

    public void set_value(java.lang.Object obj) {
        this._value = obj;
    }

    public UTSArray<UTSPromiseHandler> get_deferreds() {
        return this._deferreds;
    }

    public void set_deferreds(UTSArray<UTSPromiseHandler> uTSArray) {
        this._deferreds = uTSArray;
    }

    public UTSPromise<T> then() {
        return (UTSPromise<T>) then_origin(null, null);
    }

    public <R> UTSPromise<T> then0() {
        return then_origin(null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UTSPromise then$default(UTSPromise uTSPromise, Function0 function0, Function function, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            function = null;
        }
        return uTSPromise.then(function0, (Function<?>) function);
    }

    public <R> UTSPromise<R> then(Function0<? extends R> onFulfilled, Function<?> onRejected) {
        Intrinsics.checkNotNullParameter(onFulfilled, "onFulfilled");
        return then_origin(onFulfilled, onRejected);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UTSPromise then1$default(UTSPromise uTSPromise, Function0 function0, Function function, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            function = null;
        }
        return uTSPromise.then1(function0, function);
    }

    public <R> UTSPromise<R> then1(Function0<UTSPromise<R>> onFulfilled, Function<?> onRejected) {
        Intrinsics.checkNotNullParameter(onFulfilled, "onFulfilled");
        return then_origin(onFulfilled, onRejected);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UTSPromise then$default(UTSPromise uTSPromise, Function1 function1, Function function, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            function = null;
        }
        return uTSPromise.then(function1, (Function<?>) function);
    }

    public <R> UTSPromise<R> then(Function1<? super T, ? extends R> onFulfilled, Function<?> onRejected) {
        Intrinsics.checkNotNullParameter(onFulfilled, "onFulfilled");
        return then_origin(onFulfilled, onRejected);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UTSPromise then2$default(UTSPromise uTSPromise, Function1 function1, Function function, int i, java.lang.Object obj) {
        if ((i & 2) != 0) {
            function = null;
        }
        return uTSPromise.then2(function1, function);
    }

    public <R> UTSPromise<R> then2(Function1<? super T, UTSPromise<R>> onFulfilled, Function<?> onRejected) {
        Intrinsics.checkNotNullParameter(onFulfilled, "onFulfilled");
        return then_origin(onFulfilled, onRejected);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UTSPromise then_origin$default(UTSPromise uTSPromise, Function function, Function function2, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            function = null;
        }
        if ((i & 2) != 0) {
            function2 = null;
        }
        return uTSPromise.then_origin(function, function2);
    }

    public <R> UTSPromise<R> then_origin(Function<?> onFulfilled, Function<?> onRejected) {
        return _then(onFulfilled, onRejected);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ UTSPromise _then$default(UTSPromise uTSPromise, Function function, Function function2, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            function = null;
        }
        if ((i & 2) != 0) {
            function2 = null;
        }
        return uTSPromise._then(function, function2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> UTSPromise<R> _then(Function<?> onFulfilled, Function<?> onRejected) {
        UTSPromise<R> uTSPromise = new UTSPromise<>(new Function2<Function1<? super R, ? extends Unit>, Function1<? super java.lang.Object, ? extends Unit>, Unit>() { // from class: io.dcloud.uts.UTSPromise$_then$prom$1
            public final void invoke(Function1<? super R, Unit> resolve, Function1<java.lang.Object, Unit> reject) {
                Intrinsics.checkNotNullParameter(resolve, "resolve");
                Intrinsics.checkNotNullParameter(reject, "reject");
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj, Function1<? super java.lang.Object, ? extends Unit> function1) {
                invoke((Function1) obj, (Function1<java.lang.Object, Unit>) function1);
                return Unit.INSTANCE;
            }
        });
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type io.dcloud.uts.UTSPromise<*>");
        UTSPromiseKt.handleUTSPromise(this, new UTSPromiseHandler(onFulfilled, onRejected, uTSPromise));
        return uTSPromise;
    }

    /* renamed from: catch, reason: not valid java name */
    public UTSPromise<T> m315catch() {
        return catch_origin$default(this, null, 1, null);
    }

    public <R> UTSPromise<T> catch0() {
        return catch_origin$default(this, null, 1, null);
    }

    /* renamed from: catch, reason: not valid java name */
    public <R> UTSPromise<R> m316catch(Function0<? extends R> onRejected) {
        Intrinsics.checkNotNullParameter(onRejected, "onRejected");
        return catch_origin(onRejected);
    }

    public <R> UTSPromise<R> catch1(Function0<UTSPromise<R>> onRejected) {
        Intrinsics.checkNotNullParameter(onRejected, "onRejected");
        return catch_origin(onRejected);
    }

    /* renamed from: catch, reason: not valid java name */
    public <R> UTSPromise<R> m317catch(Function1<java.lang.Object, ? extends R> onRejected) {
        Intrinsics.checkNotNullParameter(onRejected, "onRejected");
        return catch_origin(onRejected);
    }

    public <R> UTSPromise<R> catch2(Function1<java.lang.Object, UTSPromise<R>> onRejected) {
        Intrinsics.checkNotNullParameter(onRejected, "onRejected");
        return catch_origin(onRejected);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UTSPromise catch_origin$default(UTSPromise uTSPromise, Function function, int i, java.lang.Object obj) {
        if ((i & 1) != 0) {
            function = null;
        }
        return uTSPromise.catch_origin(function);
    }

    public <R> UTSPromise<R> catch_origin(Function<?> onRejected) {
        return _catch(onRejected);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> UTSPromise<R> _catch(Function<?> onRejected) {
        return _then(null, onRejected);
    }

    /* renamed from: finally, reason: not valid java name */
    public UTSPromise<T> m318finally(final Function<?> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return (UTSPromise<T>) _then(new Function1<java.lang.Object, java.lang.Object>() { // from class: io.dcloud.uts.UTSPromise.finally.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final java.lang.Object invoke(final java.lang.Object obj) {
                return UTSPromise._then$default(UTSPromise.INSTANCE._resolve(UTSPromiseKt.callFunction(callback, new UTSArray())), new Function0<java.lang.Object>() { // from class: io.dcloud.uts.UTSPromise.finally.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final java.lang.Object invoke() {
                        return obj;
                    }
                }, null, 2, null);
            }
        }, new Function1<java.lang.Object, java.lang.Object>() { // from class: io.dcloud.uts.UTSPromise.finally.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final java.lang.Object invoke(final java.lang.Object obj) {
                return UTSPromise._then$default(UTSPromise.INSTANCE._resolve(UTSPromiseKt.callFunction(callback, new UTSArray())), new Function0<java.lang.Object>() { // from class: io.dcloud.uts.UTSPromise.finally.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final java.lang.Object invoke() {
                        return UTSPromise.INSTANCE.reject(obj);
                    }
                }, null, 2, null);
            }
        });
    }

    /* compiled from: UTSPromise.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0001\u0010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0001J,\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u000e0\b\"\u0004\b\u0001\u0010\t2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\b0\u000eJ2\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u00110\u000e0\b\"\u0004\b\u0001\u0010\t2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\b0\u000eJ*\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u00110\u000e0\b\"\u0004\b\u0001\u0010\t2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000eJ\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0001\u0010\t2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000eJ&\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0001\u0010\t2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\b0\u000eJ\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0001\u0010\t2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000eJ&\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0001\u0010\t2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\b0\u000eJ\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0001\u0010\t2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000eJ\u0018\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\bJ\u001f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0001\u0010\t2\u0006\u0010\n\u001a\u0002H\t¢\u0006\u0002\u0010\u001aJ \u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0001\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\bJ\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0001\u0010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001¨\u0006\u001c"}, d2 = {"Lio/dcloud/uts/UTSPromise$Companion;", "", "()V", "_immediateFn", "", "fn", "Lkotlin/Function;", "_resolve", "Lio/dcloud/uts/UTSPromise;", ExifInterface.GPS_DIRECTION_TRUE, "value", "_unhandledRejectionFn", NotificationCompat.CATEGORY_ERROR, "all", "Lio/dcloud/uts/UTSArray;", "arr", "allSettled", "Lio/dcloud/uts/UTSPromiseSettledResult;", "allSettled_origin", "all_origin", "any", "any_origin", "race", "race_origin", "reject", "resolve", "(Ljava/lang/Object;)Lio/dcloud/uts/UTSPromise;", "resolve_origin", "utsplugin_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UTSPromise<Unit> resolve() {
            return resolve_origin$default(UTSPromise.INSTANCE, null, 1, null);
        }

        public final <T> UTSPromise<T> resolve(T value) {
            return UTSPromise.INSTANCE.resolve_origin(value);
        }

        public final <T> UTSPromise<T> resolve(UTSPromise<T> value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return UTSPromise.INSTANCE.resolve_origin(value);
        }

        public static /* synthetic */ UTSPromise resolve_origin$default(Companion companion, java.lang.Object obj, int i, java.lang.Object obj2) {
            if ((i & 1) != 0) {
                obj = null;
            }
            return companion.resolve_origin(obj);
        }

        public final <T> UTSPromise<T> resolve_origin(java.lang.Object value) {
            return UTSPromise.INSTANCE._resolve(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <T> UTSPromise<T> _resolve(final java.lang.Object value) {
            if (value != null && (value instanceof UTSPromise)) {
                return (UTSPromise) value;
            }
            return new UTSPromise<>(new Function2<Function1<? super T, ? extends Unit>, Function1<? super java.lang.Object, ? extends Unit>, Unit>() { // from class: io.dcloud.uts.UTSPromise$Companion$_resolve$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj, Function1<? super java.lang.Object, ? extends Unit> function1) throws SecurityException {
                    invoke((Function1) obj, (Function1<java.lang.Object, Unit>) function1);
                    return Unit.INSTANCE;
                }

                public final void invoke(Function1<? super T, Unit> resolve, Function1<java.lang.Object, Unit> reject) throws SecurityException {
                    Intrinsics.checkNotNullParameter(resolve, "resolve");
                    Intrinsics.checkNotNullParameter(reject, "reject");
                    UTSPromiseKt.callFunction(resolve, UTSArrayKt.utsArrayOf(value));
                }
            });
        }

        public static /* synthetic */ UTSPromise reject$default(Companion companion, java.lang.Object obj, int i, java.lang.Object obj2) {
            if ((i & 1) != 0) {
                obj = null;
            }
            return companion.reject(obj);
        }

        public final UTSPromise<Unit> reject(final java.lang.Object value) {
            return new UTSPromise<>(new Function2<Function1<? super Unit, ? extends Unit>, Function1<? super java.lang.Object, ? extends Unit>, Unit>() { // from class: io.dcloud.uts.UTSPromise$Companion$reject$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Unit, ? extends Unit> function1, Function1<? super java.lang.Object, ? extends Unit> function12) throws SecurityException {
                    invoke2((Function1<? super Unit, Unit>) function1, (Function1<java.lang.Object, Unit>) function12);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Function1<? super Unit, Unit> function1, Function1<java.lang.Object, Unit> reject) throws SecurityException {
                    Intrinsics.checkNotNullParameter(function1, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(reject, "reject");
                    UTSPromiseKt.callFunction(reject, UTSArrayKt.utsArrayOf(value));
                }
            });
        }

        public final <T> UTSPromise<UTSArray<T>> all(UTSArray<UTSPromise<T>> arr) {
            Intrinsics.checkNotNullParameter(arr, "arr");
            return UTSPromise.INSTANCE.all_origin(arr);
        }

        public final <T> UTSPromise<T> all_origin(UTSArray<?> arr) {
            Intrinsics.checkNotNullParameter(arr, "arr");
            return new UTSPromise<>(new UTSPromise$Companion$all_origin$1(arr));
        }

        public final <T> UTSPromise<T> race(UTSArray<UTSPromise<T>> arr) {
            Intrinsics.checkNotNullParameter(arr, "arr");
            return UTSPromise.INSTANCE.race_origin(arr);
        }

        public final <T> UTSPromise<T> race_origin(final UTSArray<?> arr) {
            Intrinsics.checkNotNullParameter(arr, "arr");
            return new UTSPromise<>(new Function2<Function1<? super T, ? extends Unit>, Function1<? super java.lang.Object, ? extends Unit>, Unit>() { // from class: io.dcloud.uts.UTSPromise$Companion$race_origin$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj, Function1<? super java.lang.Object, ? extends Unit> function1) {
                    invoke((Function1) obj, (Function1<java.lang.Object, Unit>) function1);
                    return Unit.INSTANCE;
                }

                public final void invoke(Function1<? super T, Unit> resolve, Function1<java.lang.Object, Unit> reject) {
                    Intrinsics.checkNotNullParameter(resolve, "resolve");
                    Intrinsics.checkNotNullParameter(reject, "reject");
                    UTSPromise.Companion companion = UTSPromise.INSTANCE;
                    UTSArray<?> uTSArray = arr;
                    Number length = uTSArray.getLength();
                    for (Integer numValueOf = (Number) 0; NumberKt.compareTo(numValueOf, length) < 0; numValueOf = Integer.valueOf(numValueOf.intValue() + 1)) {
                        UTSPromise.INSTANCE._resolve(uTSArray.get(numValueOf.intValue()))._then(resolve, reject);
                    }
                }
            });
        }

        public final <T> UTSPromise<T> any(UTSArray<UTSPromise<T>> arr) {
            Intrinsics.checkNotNullParameter(arr, "arr");
            return UTSPromise.INSTANCE.any_origin(arr);
        }

        public final <T> UTSPromise<T> any_origin(final UTSArray<?> arr) {
            Intrinsics.checkNotNullParameter(arr, "arr");
            return new UTSPromise<>(new Function2<Function1<? super T, ? extends Unit>, Function1<? super java.lang.Object, ? extends Unit>, Unit>() { // from class: io.dcloud.uts.UTSPromise$Companion$any_origin$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj, Function1<? super java.lang.Object, ? extends Unit> function1) throws SecurityException {
                    invoke((Function1) obj, (Function1<java.lang.Object, Unit>) function1);
                    return Unit.INSTANCE;
                }

                public final void invoke(Function1<? super T, Unit> resolve, final Function1<java.lang.Object, Unit> reject) throws SecurityException {
                    Intrinsics.checkNotNullParameter(resolve, "resolve");
                    Intrinsics.checkNotNullParameter(reject, "reject");
                    if (Intrinsics.areEqual((java.lang.Object) arr.getLength(), (java.lang.Object) 0)) {
                        UTSPromiseKt.callFunction(reject, new UTSArray());
                        return;
                    }
                    final UTSArray uTSArray = new UTSArray();
                    Iterator<?> it = arr.iterator();
                    while (it.hasNext()) {
                        uTSArray.push(it.next());
                    }
                    final UTSArray uTSArray2 = new UTSArray();
                    UTSPromise.Companion companion = UTSPromise.INSTANCE;
                    for (Number numberInc = (Number) 0; NumberKt.compareTo(numberInc, uTSArray.getLength()) < 0; numberInc = NumberKt.inc(numberInc)) {
                        try {
                            UTSPromise.Companion companion2 = UTSPromise.INSTANCE;
                            Intrinsics.checkNotNull(numberInc, "null cannot be cast to non-null type kotlin.Int");
                            UTSPromise._then$default(companion2._resolve(uTSArray.get(((Integer) numberInc).intValue())), resolve, null, 2, null)._catch(new Function1<java.lang.Object, Unit>() { // from class: io.dcloud.uts.UTSPromise$Companion$any_origin$1$2$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj) throws SecurityException {
                                    invoke2(obj);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(java.lang.Object obj) throws SecurityException {
                                    uTSArray2.push(obj);
                                    if (Intrinsics.areEqual(uTSArray2.getLength(), uTSArray.getLength())) {
                                        UTSPromiseKt.callFunction(reject, UTSArrayKt.utsArrayOf(new UTSPromiseAggregateError(uTSArray2, "All promises were rejected")));
                                    }
                                }
                            });
                        } catch (Throwable th) {
                            UTSPromiseKt.callFunction(reject, UTSArrayKt.utsArrayOf(th));
                        }
                    }
                }
            });
        }

        public final <T> UTSPromise<UTSArray<UTSPromiseSettledResult<T>>> allSettled(UTSArray<UTSPromise<T>> arr) {
            Intrinsics.checkNotNullParameter(arr, "arr");
            return UTSPromise.INSTANCE.allSettled_origin(arr);
        }

        public final <T> UTSPromise<UTSArray<UTSPromiseSettledResult<T>>> allSettled_origin(UTSArray<?> arr) {
            Intrinsics.checkNotNullParameter(arr, "arr");
            return new UTSPromise<>(new UTSPromise$Companion$allSettled_origin$1(arr));
        }

        public final void _immediateFn(final Function<?> fn) {
            Intrinsics.checkNotNullParameter(fn, "fn");
            UTSTimerKt.setTimeout(new Function0<Unit>() { // from class: io.dcloud.uts.UTSPromise$Companion$_immediateFn$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() throws SecurityException {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() throws SecurityException {
                    UTSPromiseKt.callFunction(fn, new UTSArray());
                }
            }, (Number) 0);
        }

        public final void _unhandledRejectionFn(java.lang.Object err) {
            console.warn("Possible Unhandled Promise Rejection:", err);
            if (err instanceof Throwable) {
                console.INSTANCE.errorV1WithStack(err);
            }
        }
    }
}
