package io.dcloud.uniapp.extapi;

import com.facebook.common.util.UriUtil;
import com.taobao.weex.bridge.WXBridgeManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import uts.sdk.modules.DCloudUniPrivacy.GetPrivacySettingOptions;
import uts.sdk.modules.DCloudUniPrivacy.IndexKt;
import uts.sdk.modules.DCloudUniPrivacy.PrivacyChangeResult;

/* compiled from: uniPrivacy.kt */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\"0\u0010\u0000\u001a!\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"0\u0010\n\u001a!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\t\"^\u0010\u000f\u001aO\u0012A\u0012?\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u0001j\u0011`\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0013¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u000b0\u0001j\u0002`\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\t\"\u001b\u0010\u0016\u001a\f\u0012\u0004\u0012\u00020\u00060\u0017j\u0002`\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a*\n\u0010\u001b\"\u00020\u00022\u00020\u0002*\n\u0010\u001c\"\u00020\u001d2\u00020\u001d*\n\u0010\u001e\"\u00020\u00102\u00020\u0010¨\u0006\u001f"}, d2 = {"getPrivacySetting", "Lkotlin/Function1;", "Luts/sdk/modules/DCloudUniPrivacy/GetPrivacySettingOptions;", "Lkotlin/ParameterName;", "name", "options", "", "Luts/sdk/modules/DCloudUniPrivacy/GetPrivacySetting;", "getGetPrivacySetting", "()Lkotlin/jvm/functions/Function1;", "offPrivacyAuthorizationChange", "", "id", "Luts/sdk/modules/DCloudUniPrivacy/OffPrivacyAuthorizationChange;", "getOffPrivacyAuthorizationChange", "onPrivacyAuthorizationChange", "Luts/sdk/modules/DCloudUniPrivacy/PrivacyChangeResult;", UriUtil.LOCAL_RESOURCE_SCHEME, "Luts/sdk/modules/DCloudUniPrivacy/OnPrivacyAuthorizationChangeCallback;", WXBridgeManager.METHOD_CALLBACK, "Luts/sdk/modules/DCloudUniPrivacy/OnPrivacyAuthorizationChange;", "getOnPrivacyAuthorizationChange", "resetPrivacyAuthorization", "Lkotlin/Function0;", "Luts/sdk/modules/DCloudUniPrivacy/ResetPrivacyAuthorization;", "getResetPrivacyAuthorization", "()Lkotlin/jvm/functions/Function0;", "GetPrivacySettingOptions", "GetPrivacySettingSuccessResult", "Luts/sdk/modules/DCloudUniPrivacy/GetPrivacySettingSuccessResult;", "PrivacyChangeResult", "uni-privacy_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UniPrivacyKt {
    private static final Function1<Function1<? super PrivacyChangeResult, Unit>, Number> onPrivacyAuthorizationChange = IndexKt.getOnPrivacyAuthorizationChange();
    private static final Function1<Number, Unit> offPrivacyAuthorizationChange = IndexKt.getOffPrivacyAuthorizationChange();
    private static final Function1<GetPrivacySettingOptions, Unit> getPrivacySetting = IndexKt.getGetPrivacySetting();
    private static final Function0<Unit> resetPrivacyAuthorization = IndexKt.getResetPrivacyAuthorization();

    public static final Function1<Function1<? super PrivacyChangeResult, Unit>, Number> getOnPrivacyAuthorizationChange() {
        return onPrivacyAuthorizationChange;
    }

    public static final Function1<Number, Unit> getOffPrivacyAuthorizationChange() {
        return offPrivacyAuthorizationChange;
    }

    public static final Function1<GetPrivacySettingOptions, Unit> getGetPrivacySetting() {
        return getPrivacySetting;
    }

    public static final Function0<Unit> getResetPrivacyAuthorization() {
        return resetPrivacyAuthorization;
    }
}
