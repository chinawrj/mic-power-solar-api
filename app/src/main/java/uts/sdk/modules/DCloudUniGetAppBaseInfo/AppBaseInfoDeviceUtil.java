package uts.sdk.modules.DCloudUniGetAppBaseInfo;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import io.dcloud.uts.NumberKt;
import io.dcloud.uts.StringKt;
import io.dcloud.uts.UTSAndroid;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: index.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Luts/sdk/modules/DCloudUniGetAppBaseInfo/AppBaseInfoDeviceUtil;", "", "()V", "Companion", "uni-getAppBaseInfo_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public class AppBaseInfoDeviceUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: index.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019¨\u0006\u001a"}, d2 = {"Luts/sdk/modules/DCloudUniGetAppBaseInfo/AppBaseInfoDeviceUtil$Companion;", "", "()V", "getAppID", "", "getAppInnerVersion", "getAppName", "context", "Landroid/content/Context;", "getAppSignatureSHA1", "getAppVersionCode", "getAppVersionName", "getChannel", "getHostCode", "getHostVersion", "getOsLanguage", "getOsLanguageNormal", "getPackageName", "getSignatureString", "sign", "Landroid/content/pm/Signature;", "type", "isSystemNightMode", "", "activity", "Landroid/app/Activity;", "uni-getAppBaseInfo_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getAppID() {
            return UTSAndroid.INSTANCE.getAppId();
        }

        public final String getAppName(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
        }

        public final String getPackageName(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "context.getPackageName()");
            return packageName;
        }

        public final String getAppVersionName() {
            return NumberKt.toString_number_nullable$default(UTSAndroid.INSTANCE.getAppVersion().get("name"), (Number) null, 1, (Object) null);
        }

        public final String getAppVersionCode() {
            return NumberKt.toString_number_nullable$default(UTSAndroid.INSTANCE.getAppVersion().get("code"), (Number) null, 1, (Object) null);
        }

        public final String getHostVersion(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).versionName;
            return str == null ? "" : str;
        }

        public final String getHostCode(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return NumberKt.plus(Integer.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 1).versionCode), "");
        }

        public final boolean isSystemNightMode(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Object systemService = activity.getSystemService("uimode");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.UiModeManager");
            return ((UiModeManager) systemService).getNightMode() == 2;
        }

        public final String getOsLanguage(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return NumberKt.toString_number_nullable$default(UTSAndroid.INSTANCE.getLanguageInfo(context).get("osLanguage"), (Number) null, 1, (Object) null);
        }

        public final String getOsLanguageNormal(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String string_number_nullable$default = NumberKt.toString_number_nullable$default(UTSAndroid.INSTANCE.getLanguageInfo(context).get("appLanguage"), (Number) null, 1, (Object) null);
            return NumberKt.numberEquals(StringKt.indexOf$default(string_number_nullable$default, "zh", null, 2, null), 0) ? NumberKt.compareTo(StringKt.indexOf$default(string_number_nullable$default, "-hans", null, 2, null), (Number) (-1)) > 0 ? "zh-Hans" : (NumberKt.compareTo(StringKt.indexOf$default(string_number_nullable$default, "-hant", null, 2, null), (Number) (-1)) > 0 || StringKt.includes$default(string_number_nullable$default, "-tw", null, 2, null) || StringKt.includes$default(string_number_nullable$default, "-hk", null, 2, null) || StringKt.includes$default(string_number_nullable$default, "-mo", null, 2, null) || StringKt.includes$default(string_number_nullable$default, "-cht", null, 2, null)) ? "zh-Hant" : "zh-Hans" : string_number_nullable$default;
        }

        public final String getAppInnerVersion() {
            return UTSAndroid.INSTANCE.getInnerVersion();
        }

        public final String getAppSignatureSHA1(Context context) throws PackageManager.NameNotFoundException {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
                Intrinsics.checkNotNull(packageInfo, "null cannot be cast to non-null type android.content.pm.PackageInfo");
                Signature[] signatureArr = packageInfo.signatures;
                if (signatureArr == null) {
                    return "";
                }
                String signatureString = "";
                for (Signature value : signatureArr) {
                    Companion companion = AppBaseInfoDeviceUtil.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(value, "value");
                    signatureString = companion.getSignatureString(value, "SHA1");
                }
                return signatureString;
            } catch (Exception unused) {
                return "";
            }
        }

        public final String getChannel(Context context) throws PackageManager.NameNotFoundException {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                Intrinsics.checkNotNullExpressionValue(applicationInfo, "packageManager.getApplic…ageManager.GET_META_DATA)");
                String string = applicationInfo.metaData.getString("DCLOUD_CHANNEL");
                return string == null ? "" : string;
            } catch (Exception unused) {
                return "";
            }
        }

        private final String getSignatureString(Signature sign, String type) throws NoSuchAlgorithmException {
            byte[] byteArray = sign.toByteArray();
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(type);
                if (messageDigest != null) {
                    byte[] digestBytes = messageDigest.digest(byteArray);
                    StringBuffer stringBuffer = new StringBuffer();
                    Intrinsics.checkNotNullExpressionValue(digestBytes, "digestBytes");
                    for (byte b : digestBytes) {
                        String hexString = Integer.toHexString(NumberKt.or(NumberKt.and(Byte.valueOf(b), (Number) 255), (Number) 256).intValue());
                        Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(((digestByte… 0xFF) or 0x100).toInt())");
                        stringBuffer.append(StringKt.substring(hexString, (Number) 1, (Number) 3));
                    }
                    String string = stringBuffer.toString();
                    Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
                    return string;
                }
            } catch (Exception unused) {
            }
            return "error!";
        }
    }
}
