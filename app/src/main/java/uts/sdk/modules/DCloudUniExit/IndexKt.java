package uts.sdk.modules.DCloudUniExit;

import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.facebook.common.util.UriUtil;
import io.dcloud.uts.UTSAndroid;
import io.dcloud.uts.UTSCallback;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\n\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u000b\"2\u0010\u0000\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t*D\u0010\f\"\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u00012\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001*@\u0010\r\"\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00060\u00012\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00060\u0001*\n\u0010\u0010\"\u00020\u00112\u00020\u0011*\n\u0010\u0012\"\u00020\u00132\u00020\u0013*S\u0010\u0014\"\u001d\u0012\u0013\u0012\u0011`\u0015¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00060\u000120\u0012&\u0012$0\u0013j\u0011`\u0015¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000f¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00060\u0001*@\u0010\u0016\"\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00060\u00012\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00060\u0001¨\u0006\u0018"}, d2 = {BindingXConstants.STATE_EXIT, "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniExit/ExitOptions;", "Lkotlin/ParameterName;", "name", "options", "", "Luts/sdk/modules/DCloudUniExit/Exit;", "getExit", "()Lkotlin/jvm/functions/Function1;", "exitByJs", "Luts/sdk/modules/DCloudUniExit/ExitOptionsJSONObject;", "Exit", "ExitCompleteCallback", "", UriUtil.LOCAL_RESOURCE_SCHEME, "ExitErrorCode", "", "ExitFail", "Luts/sdk/modules/DCloudUniExit/IExitError;", "ExitFailCallback", "Luts/sdk/modules/DCloudUniExit/ExitFail;", "ExitSuccessCallback", "Luts/sdk/modules/DCloudUniExit/ExitSuccess;", "uni-exit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IndexKt {
    private static final Function1<ExitOptions, Unit> exit = new Function1<ExitOptions, Unit>() { // from class: uts.sdk.modules.DCloudUniExit.IndexKt$exit$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ExitOptions exitOptions) {
            invoke2(exitOptions);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(ExitOptions exitOptions) {
            Function1<Object, Unit> complete;
            Function1<ExitSuccess, Unit> success;
            ExitSuccess exitSuccess = new ExitSuccess("exit:ok");
            if (exitOptions != null && (success = exitOptions.getSuccess()) != null) {
                success.invoke(exitSuccess);
            }
            if (exitOptions != null && (complete = exitOptions.getComplete()) != null) {
                complete.invoke(exitSuccess);
            }
            UTSAndroid.INSTANCE.exit();
        }
    };

    public static final Function1<ExitOptions, Unit> getExit() {
        return exit;
    }

    public static final void exitByJs(final ExitOptionsJSONObject exitOptionsJSONObject) {
        exit.invoke(exitOptionsJSONObject != null ? new ExitOptions(new Function1<ExitSuccess, Unit>() { // from class: uts.sdk.modules.DCloudUniExit.IndexKt.exitByJs.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ExitSuccess exitSuccess) throws SecurityException {
                invoke2(exitSuccess);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ExitSuccess res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                ExitOptionsJSONObject exitOptionsJSONObject2 = exitOptionsJSONObject;
                Intrinsics.checkNotNull(exitOptionsJSONObject2);
                UTSCallback success = exitOptionsJSONObject2.getSuccess();
                if (success != null) {
                    success.invoke(res);
                }
            }
        }, new Function1<IExitError, Unit>() { // from class: uts.sdk.modules.DCloudUniExit.IndexKt.exitByJs.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IExitError iExitError) throws SecurityException {
                invoke2(iExitError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IExitError res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                ExitOptionsJSONObject exitOptionsJSONObject2 = exitOptionsJSONObject;
                Intrinsics.checkNotNull(exitOptionsJSONObject2);
                UTSCallback fail = exitOptionsJSONObject2.getFail();
                if (fail != null) {
                    fail.invoke(res);
                }
            }
        }, new Function1<Object, Unit>() { // from class: uts.sdk.modules.DCloudUniExit.IndexKt.exitByJs.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) throws SecurityException {
                invoke2(obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Object res) throws SecurityException {
                Intrinsics.checkNotNullParameter(res, "res");
                ExitOptionsJSONObject exitOptionsJSONObject2 = exitOptionsJSONObject;
                Intrinsics.checkNotNull(exitOptionsJSONObject2);
                UTSCallback complete = exitOptionsJSONObject2.getComplete();
                if (complete != null) {
                    complete.invoke(res);
                }
            }
        }) : null);
    }
}
