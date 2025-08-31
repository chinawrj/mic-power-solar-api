package io.dcloud.uts;

import io.dcloud.common.util.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: UTSArray.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.dcloud.uts.UTSArray$Companion$fromAsyncImpl$p$1$1$allJob$1$1", f = "UTSArray.kt", i = {}, l = {113}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class UTSArray$Companion$fromAsyncImpl$p$1$1$allJob$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super java.lang.Object>, java.lang.Object> {
    final /* synthetic */ java.lang.Object $any;
    final /* synthetic */ Function1<java.lang.Object, Unit> $rejectParam;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UTSArray$Companion$fromAsyncImpl$p$1$1$allJob$1$1(java.lang.Object obj, Function1<java.lang.Object, Unit> function1, Continuation<? super UTSArray$Companion$fromAsyncImpl$p$1$1$allJob$1$1> continuation) {
        super(2, continuation);
        this.$any = obj;
        this.$rejectParam = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(java.lang.Object obj, Continuation<?> continuation) {
        return new UTSArray$Companion$fromAsyncImpl$p$1$1$allJob$1$1(this.$any, this.$rejectParam, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ java.lang.Object invoke(CoroutineScope coroutineScope, Continuation<? super java.lang.Object> continuation) {
        return invoke2(coroutineScope, (Continuation<java.lang.Object>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final java.lang.Object invoke2(CoroutineScope coroutineScope, Continuation<java.lang.Object> continuation) {
        return ((UTSArray$Companion$fromAsyncImpl$p$1$1$allJob$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object obj) throws Throwable {
        java.lang.Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            UTSPromise uTSPromise = (UTSPromise) this.$any;
            final Function1<java.lang.Object, Unit> function1 = this.$rejectParam;
            this.label = 1;
            obj = UTSPromiseHelperKt.await(uTSPromise.m317catch(new Function1<java.lang.Object, Unit>() { // from class: io.dcloud.uts.UTSArray$Companion$fromAsyncImpl$p$1$1$allJob$1$1$promiseReal$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(java.lang.Object obj2) {
                    invoke2(obj2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(java.lang.Object obj2) {
                    function1.invoke(obj2);
                }
            }), (Continuation) this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
