package io.dcloud.uniapp.extapi;

import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import uts.sdk.modules.DCloudUniExit.ExitOptions;
import uts.sdk.modules.DCloudUniExit.IndexKt;

/* compiled from: uniExit.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\"2\u0010\u0000\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t*\u000e\u0010\n\"\u0002`\u000b2\u00060\fj\u0002`\u000b*\u000e\u0010\r\"\u0002`\u000e2\u00060\u000fj\u0002`\u000e*\n\u0010\u0010\"\u00020\u00022\u00020\u0002*\n\u0010\u0011\"\u00020\u00122\u00020\u0012*\n\u0010\u0013\"\u00020\u000f2\u00020\u000f¨\u0006\u0014"}, d2 = {BindingXConstants.STATE_EXIT, "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniExit/ExitOptions;", "Lkotlin/ParameterName;", "name", "options", "", "Luts/sdk/modules/DCloudUniExit/Exit;", "getExit", "()Lkotlin/jvm/functions/Function1;", "ExitErrorCode", "Luts/sdk/modules/DCloudUniExit/ExitErrorCode;", "", "ExitFail", "Luts/sdk/modules/DCloudUniExit/ExitFail;", "Luts/sdk/modules/DCloudUniExit/IExitError;", "ExitOptions", "ExitSuccess", "Luts/sdk/modules/DCloudUniExit/ExitSuccess;", "IExitError", "uni-exit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UniExitKt {
    private static final Function1<ExitOptions, Unit> exit = IndexKt.getExit();

    public static final Function1<ExitOptions, Unit> getExit() {
        return exit;
    }
}
