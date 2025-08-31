package io.dcloud.uniapp.extapi;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.reflect.KFunction;
import uts.sdk.modules.DCloudUniGetDeviceInfo.GetDeviceInfoOptions;
import uts.sdk.modules.DCloudUniGetDeviceInfo.GetDeviceInfoResult;
import uts.sdk.modules.DCloudUniGetDeviceInfo.IndexKt;

/* compiled from: uniGetDeviceInfo.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\"2\u0010\u0000\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\r*\n\u0010\u000e\"\u00020\u00022\u00020\u0002*\n\u0010\u000f\"\u00020\u00062\u00020\u0006¨\u0006\u0010"}, d2 = {"getDeviceInfo", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniGetDeviceInfo/GetDeviceInfoOptions;", "Lkotlin/ParameterName;", "name", "options", "Luts/sdk/modules/DCloudUniGetDeviceInfo/GetDeviceInfoResult;", "Luts/sdk/modules/DCloudUniGetDeviceInfo/GetDeviceInfo;", "getGetDeviceInfo", "()Lkotlin/jvm/functions/Function1;", "isSimulator", "Lkotlin/reflect/KFunction0;", "", "()Lkotlin/reflect/KFunction;", "GetDeviceInfoOptions", "GetDeviceInfoResult", "uni-getDeviceInfo_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UniGetDeviceInfoKt {
    private static final Function1<GetDeviceInfoOptions, GetDeviceInfoResult> getDeviceInfo = IndexKt.getGetDeviceInfo();
    private static final KFunction<Boolean> isSimulator = AnonymousClass1.INSTANCE;

    /* compiled from: uniGetDeviceInfo.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: io.dcloud.uniapp.extapi.UniGetDeviceInfoKt$isSimulator$1, reason: invalid class name */
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(0, IndexKt.class, "isSimulator", "isSimulator()Z", 1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(IndexKt.isSimulator());
        }
    }

    public static final Function1<GetDeviceInfoOptions, GetDeviceInfoResult> getGetDeviceInfo() {
        return getDeviceInfo;
    }

    public static final KFunction<Boolean> isSimulator() {
        return isSimulator;
    }
}
