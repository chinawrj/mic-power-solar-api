package uts.sdk.modules.DCloudUniGetSystemSetting;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Luts/sdk/modules/DCloudUniGetSystemSetting/DeviceUtil;", "", "()V", "Companion", "uni-getSystemSetting_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class DeviceUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: index.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u000b"}, d2 = {"Luts/sdk/modules/DCloudUniGetSystemSetting/DeviceUtil$Companion;", "", "()V", "blueToothEnable", "", "context", "Landroid/content/Context;", "deviceOrientation", "", "locationEnable", "wifiEnable", "uni-getSystemSetting_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean blueToothEnable(Context context) throws Exception {
            Intrinsics.checkNotNullParameter(context, "context");
            if (context.checkSelfPermission("android.permission.BLUETOOTH") == -1) {
                throw new Exception();
            }
            Object systemService = context.getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            return ((BluetoothManager) systemService).getAdapter().isEnabled();
        }

        public final boolean locationEnable(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (Build.VERSION.SDK_INT < 28) {
                return Settings.Secure.getInt(context.getContentResolver(), "location_mode", 0) != 0;
            }
            Object systemService = context.getSystemService("location");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
            return ((LocationManager) systemService).isLocationEnabled();
        }

        public final boolean wifiEnable(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getApplicationContext().getSystemService("wifi");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.wifi.WifiManager");
            return ((WifiManager) systemService).getWifiState() == 3;
        }

        public final String deviceOrientation(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            int i = context.getResources().getConfiguration().orientation;
            if (i == 1) {
                return "portrait";
            }
            if (i == 2) {
                return "landscape";
            }
            return "";
        }
    }
}
