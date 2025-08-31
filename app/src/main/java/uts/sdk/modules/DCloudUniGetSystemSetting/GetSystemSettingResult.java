package uts.sdk.modules.DCloudUniGetSystemSetting;

import io.dcloud.uts.JsonNotNull;
import io.dcloud.uts.UTSObject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\b\u0016\u0018\u00002\u00020\u0001BG\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\t\u001a\u00020\u00058\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001e\u0010\u0006\u001a\u00020\u00038\u0016@\u0016X\u0097\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u001a\u0010\f\"\u0004\b\u001b\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0011\"\u0004\b\u001d\u0010\u0013¨\u0006\u001e"}, d2 = {"Luts/sdk/modules/DCloudUniGetSystemSetting/GetSystemSettingResult;", "Lio/dcloud/uts/UTSObject;", "bluetoothEnabled", "", "bluetoothError", "", "locationEnabled", "wifiEnabled", "wifiError", "deviceOrientation", "(Ljava/lang/Boolean;Ljava/lang/String;ZLjava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "getBluetoothEnabled", "()Ljava/lang/Boolean;", "setBluetoothEnabled", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getBluetoothError", "()Ljava/lang/String;", "setBluetoothError", "(Ljava/lang/String;)V", "getDeviceOrientation", "setDeviceOrientation", "getLocationEnabled", "()Z", "setLocationEnabled", "(Z)V", "getWifiEnabled", "setWifiEnabled", "getWifiError", "setWifiError", "uni-getSystemSetting_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class GetSystemSettingResult extends UTSObject {
    private Boolean bluetoothEnabled;
    private String bluetoothError;

    @JsonNotNull
    private String deviceOrientation;

    @JsonNotNull
    private boolean locationEnabled;
    private Boolean wifiEnabled;
    private String wifiError;

    public /* synthetic */ GetSystemSettingResult(Boolean bool, String str, boolean z, Boolean bool2, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? null : str, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : bool2, (i & 16) != 0 ? null : str2, str3);
    }

    public Boolean getBluetoothEnabled() {
        return this.bluetoothEnabled;
    }

    public void setBluetoothEnabled(Boolean bool) {
        this.bluetoothEnabled = bool;
    }

    public String getBluetoothError() {
        return this.bluetoothError;
    }

    public void setBluetoothError(String str) {
        this.bluetoothError = str;
    }

    public boolean getLocationEnabled() {
        return this.locationEnabled;
    }

    public void setLocationEnabled(boolean z) {
        this.locationEnabled = z;
    }

    public Boolean getWifiEnabled() {
        return this.wifiEnabled;
    }

    public void setWifiEnabled(Boolean bool) {
        this.wifiEnabled = bool;
    }

    public String getWifiError() {
        return this.wifiError;
    }

    public void setWifiError(String str) {
        this.wifiError = str;
    }

    public String getDeviceOrientation() {
        return this.deviceOrientation;
    }

    public void setDeviceOrientation(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceOrientation = str;
    }

    public GetSystemSettingResult(Boolean bool, String str, boolean z, Boolean bool2, String str2, String deviceOrientation) {
        Intrinsics.checkNotNullParameter(deviceOrientation, "deviceOrientation");
        this.bluetoothEnabled = bool;
        this.bluetoothError = str;
        this.locationEnabled = z;
        this.wifiEnabled = bool2;
        this.wifiError = str2;
        this.deviceOrientation = deviceOrientation;
    }
}
