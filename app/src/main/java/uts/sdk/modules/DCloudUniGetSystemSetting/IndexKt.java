package uts.sdk.modules.DCloudUniGetSystemSetting;

import android.content.Context;
import io.dcloud.uts.UTSAndroid;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import uts.sdk.modules.DCloudUniGetSystemSetting.DeviceUtil;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0006\u0010\u0006\u001a\u00020\u0002\"\u001b\u0010\u0000\u001a\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005*\u0016\u0010\u0007\"\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\b"}, d2 = {"getSystemSetting", "Lkotlin/Function0;", "Luts/sdk/modules/DCloudUniGetSystemSetting/GetSystemSettingResult;", "Luts/sdk/modules/DCloudUniGetSystemSetting/GetSystemSetting;", "getGetSystemSetting", "()Lkotlin/jvm/functions/Function0;", "getSystemSettingByJs", "GetSystemSetting", "uni-getSystemSetting_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IndexKt {
    private static final Function0<GetSystemSettingResult> getSystemSetting = new Function0<GetSystemSettingResult>() { // from class: uts.sdk.modules.DCloudUniGetSystemSetting.IndexKt$getSystemSetting$1
        @Override // kotlin.jvm.functions.Function0
        public final GetSystemSettingResult invoke() {
            Context appContext = UTSAndroid.INSTANCE.getAppContext();
            DeviceUtil.Companion companion = DeviceUtil.INSTANCE;
            Intrinsics.checkNotNull(appContext);
            GetSystemSettingResult getSystemSettingResult = new GetSystemSettingResult(null, null, DeviceUtil.INSTANCE.locationEnable(appContext), null, null, companion.deviceOrientation(appContext), 27, null);
            try {
                getSystemSettingResult.setBluetoothEnabled(Boolean.valueOf(DeviceUtil.INSTANCE.blueToothEnable(appContext)));
            } catch (Exception unused) {
                getSystemSettingResult.setBluetoothError("Missing permissions required by BluetoothAdapter.isEnabled: android.permission.BLUETOOTH");
            }
            try {
                getSystemSettingResult.setWifiEnabled(Boolean.valueOf(DeviceUtil.INSTANCE.wifiEnable(appContext)));
            } catch (Exception unused2) {
                getSystemSettingResult.setWifiError("Missing permissions required by WifiManager.isWifiEnabled: android.permission.ACCESS_WIFI_STATE");
            }
            return getSystemSettingResult;
        }
    };

    public static final Function0<GetSystemSettingResult> getGetSystemSetting() {
        return getSystemSetting;
    }

    public static final GetSystemSettingResult getSystemSettingByJs() {
        return getSystemSetting.invoke();
    }
}
