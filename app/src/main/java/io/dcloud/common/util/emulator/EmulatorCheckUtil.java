package io.dcloud.common.util.emulator;

import android.content.Context;
import android.hardware.SensorManager;
import android.text.TextUtils;
import com.taobao.weex.WXEnvironment;
import java.io.File;
import java.util.Locale;

/* loaded from: classes3.dex */
public class EmulatorCheckUtil {
    public static final int RESULT_EMULATOR = 1;
    public static final int RESULT_MAYBE_EMULATOR = 0;
    public static final int RESULT_UNKNOWN = 2;
    private static String[] known_pkgNames = {"sdcard/Android/data/com.bluestacks.home", "sdcard/Android/data/com.bluestacks.settings", "sdcard/Android/data/com.microvirt.guide", "sdcard/Android/data/com.microvirt.launcher2"};

    private static class SingletonHolder {
        private static final EmulatorCheckUtil INSTANCE = new EmulatorCheckUtil();

        private SingletonHolder() {
        }
    }

    private CheckResult checkFeaturesByBaseBand() {
        String property = getProperty("gsm.version.baseband");
        if (property == null) {
            return new CheckResult(0, null);
        }
        return new CheckResult(property.contains("1.0.0.0") ? 1 : 2, property);
    }

    private CheckResult checkFeaturesByBoard() {
        String property = getProperty("ro.product.board");
        if (property == null) {
            return new CheckResult(0, null);
        }
        String lowerCase = property.toLowerCase(Locale.ENGLISH);
        return new CheckResult((lowerCase.contains(WXEnvironment.OS) || lowerCase.contains("goldfish")) ? 1 : 2, property);
    }

    private CheckResult checkFeaturesByFlavor() {
        String property = getProperty("ro.build.flavor");
        if (property == null) {
            return new CheckResult(0, null);
        }
        String lowerCase = property.toLowerCase(Locale.ENGLISH);
        return new CheckResult((lowerCase.contains("vbox") || lowerCase.contains("sdk_gphone")) ? 1 : 2, property);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private io.dcloud.common.util.emulator.CheckResult checkFeaturesByHardware() {
        /*
            r7 = this;
            java.lang.String r0 = "ro.hardware"
            java.lang.String r0 = r7.getProperty(r0)
            r1 = 0
            if (r0 != 0) goto L10
            io.dcloud.common.util.emulator.CheckResult r0 = new io.dcloud.common.util.emulator.CheckResult
            r2 = 0
            r0.<init>(r1, r2)
            return r0
        L10:
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r2 = r0.toLowerCase(r2)
            r2.hashCode()
            r2.hashCode()
            int r3 = r2.hashCode()
            r4 = 2
            r5 = 1
            r6 = -1
            switch(r3) {
                case -1367724016: goto L6a;
                case -822798509: goto L5f;
                case 109271: goto L54;
                case 3570999: goto L49;
                case 3613077: goto L3e;
                case 100361430: goto L33;
                case 937844646: goto L28;
                default: goto L26;
            }
        L26:
            r1 = r6
            goto L73
        L28:
            java.lang.String r1 = "android_x86"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L31
            goto L26
        L31:
            r1 = 6
            goto L73
        L33:
            java.lang.String r1 = "intel"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L3c
            goto L26
        L3c:
            r1 = 5
            goto L73
        L3e:
            java.lang.String r1 = "vbox"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L47
            goto L26
        L47:
            r1 = 4
            goto L73
        L49:
            java.lang.String r1 = "ttvm"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L52
            goto L26
        L52:
            r1 = 3
            goto L73
        L54:
            java.lang.String r1 = "nox"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L5d
            goto L26
        L5d:
            r1 = r4
            goto L73
        L5f:
            java.lang.String r1 = "vbox86"
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L68
            goto L26
        L68:
            r1 = r5
            goto L73
        L6a:
            java.lang.String r3 = "cancro"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L73
            goto L26
        L73:
            switch(r1) {
                case 0: goto L77;
                case 1: goto L77;
                case 2: goto L77;
                case 3: goto L77;
                case 4: goto L77;
                case 5: goto L77;
                case 6: goto L77;
                default: goto L76;
            }
        L76:
            goto L78
        L77:
            r4 = r5
        L78:
            io.dcloud.common.util.emulator.CheckResult r1 = new io.dcloud.common.util.emulator.CheckResult
            r1.<init>(r4, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.common.util.emulator.EmulatorCheckUtil.checkFeaturesByHardware():io.dcloud.common.util.emulator.CheckResult");
    }

    private CheckResult checkFeaturesByManufacturer() {
        String property = getProperty("ro.product.manufacturer");
        if (property == null) {
            return new CheckResult(0, null);
        }
        String lowerCase = property.toLowerCase(Locale.ENGLISH);
        return new CheckResult((lowerCase.contains("genymotion") || lowerCase.contains("netease")) ? 1 : 2, property);
    }

    private CheckResult checkFeaturesByModel() {
        String property = getProperty("ro.product.model");
        if (property == null) {
            return new CheckResult(0, null);
        }
        String lowerCase = property.toLowerCase(Locale.ENGLISH);
        return new CheckResult((lowerCase.contains("google_sdk") || lowerCase.contains("emulator") || lowerCase.contains("android sdk built for x86")) ? 1 : 2, property);
    }

    private CheckResult checkFeaturesByPlatform() {
        String property = getProperty("ro.board.platform");
        if (property == null) {
            return new CheckResult(0, null);
        }
        return new CheckResult(property.toLowerCase(Locale.ENGLISH).contains(WXEnvironment.OS) ? 1 : 2, property);
    }

    public static CheckResult checkPkgNameForEmulator() {
        int i = 2;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            String[] strArr = known_pkgNames;
            if (i2 >= strArr.length) {
                break;
            }
            if (new File(strArr[i2]).exists()) {
                i3++;
            } else {
                i = 0;
            }
            if (i3 > 2) {
                break;
            }
            i2++;
        }
        return new CheckResult(i3 != 1 ? i3 != 2 ? i : 1 : 0, "PkgName");
    }

    private String getProperty(String str) {
        String property = CommandUtil.getSingleInstance().getProperty(str);
        if (TextUtils.isEmpty(property)) {
            return null;
        }
        return property;
    }

    private int getSensorNumber(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getSensorList(-1).size();
    }

    public static final EmulatorCheckUtil getSingleInstance() {
        return SingletonHolder.INSTANCE;
    }

    private int getUserAppNum(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return str.split("package:").length;
    }

    private boolean hasLightSensor(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(5) != null;
    }

    private boolean supportBluetooth(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth");
    }

    private boolean supportCameraFlash(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    public boolean emulatorCheck(Context context) {
        int i;
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        int i2 = checkFeaturesByHardware().result;
        if (i2 == 0) {
            i = 1;
        } else {
            if (i2 == 1) {
                return true;
            }
            i = 0;
        }
        int i3 = checkPkgNameForEmulator().result;
        if (i3 == 0) {
            i++;
        } else if (i3 == 1) {
            return true;
        }
        int i4 = checkFeaturesByFlavor().result;
        if (i4 == 0) {
            i++;
        } else if (i4 == 1) {
            return true;
        }
        int i5 = checkFeaturesByModel().result;
        if (i5 == 0) {
            i++;
        } else if (i5 == 1) {
            return true;
        }
        int i6 = checkFeaturesByManufacturer().result;
        if (i6 == 0) {
            i++;
        } else if (i6 == 1) {
            return true;
        }
        int i7 = checkFeaturesByBoard().result;
        if (i7 == 0) {
            i++;
        } else if (i7 == 1) {
            return true;
        }
        int i8 = checkFeaturesByPlatform().result;
        if (i8 == 0) {
            i++;
        } else if (i8 == 1) {
            return true;
        }
        int i9 = checkFeaturesByBaseBand().result;
        if (i9 == 0) {
            i += 2;
        } else if (i9 == 1) {
            return true;
        }
        if (getSensorNumber(context) <= 7) {
            i++;
        }
        if (!supportCameraFlash(context)) {
            i++;
        }
        if (!supportBluetooth(context)) {
            i++;
        }
        if (!hasLightSensor(context)) {
            i++;
        }
        return i > 3;
    }

    private EmulatorCheckUtil() {
    }
}
