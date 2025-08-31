package io.dcloud.sdk.core.util;

import android.content.Context;
import android.text.TextUtils;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.p.d4;
import io.dcloud.sdk.core.DCloudAOLManager;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class AdUtil {
    private static DCloudAOLManager.PrivacyConfig a;

    public static String getCustomPrivacyConfig(Context context) {
        return d4.a(context, "dcloud-ads", "CustomPrivacyConfig");
    }

    public static DCloudAOLManager.PrivacyConfig getDCloudPrivacyConfig(Context context) {
        String customPrivacyConfig = getCustomPrivacyConfig(context);
        if (TextUtils.isEmpty(customPrivacyConfig)) {
            return null;
        }
        try {
            return getPrivacyConfig(new JSONObject(customPrivacyConfig));
        } catch (Exception unused) {
            return null;
        }
    }

    public static <T> T getOrDefault(Map<String, T> map, Object obj, T t) {
        if (map == null) {
            return t;
        }
        T t2 = map.get(obj);
        return (t2 != null || map.containsKey(obj)) ? t2 : t;
    }

    public static boolean getPersonalAd(Context context) {
        String strA = d4.a(context, "dcloud-ads", "PersonalizedAdEnable");
        if (TextUtils.isEmpty(strA)) {
            strA = AbsoluteConst.TRUE;
        }
        return Boolean.parseBoolean(strA);
    }

    public static DCloudAOLManager.PrivacyConfig getPrivacyConfig(final JSONObject jSONObject) {
        return new DCloudAOLManager.PrivacyConfig() { // from class: io.dcloud.sdk.core.util.AdUtil.1
            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isAdult() {
                return jSONObject.optBoolean("isAdult", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanGetAndroidId() {
                return jSONObject.optBoolean("isCanGetAndroidId", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanGetIP() {
                return jSONObject.optBoolean("isCanGetIP", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanGetInstallAppList() {
                return jSONObject.optBoolean("isCanGetInstallAppList", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanGetMacAddress() {
                return jSONObject.optBoolean("isCanGetMacAddress", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanGetOAID() {
                return jSONObject.optBoolean("isCanGetOAID", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanGetRunningApps() {
                return jSONObject.optBoolean("isCanGetRunningApps", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanUseLocation() {
                return jSONObject.optBoolean("isCanUseLocation", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanUsePhoneState() {
                return jSONObject.optBoolean("isCanUsePhoneState", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanUseRecordPermission() {
                return jSONObject.optBoolean("isCanUseRecordPermission", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanUseSensor() {
                return jSONObject.optBoolean("isCanUseSensor", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanUseSimOperator() {
                return jSONObject.optBoolean("isCanUseSimOperator", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanUseStorage() {
                return jSONObject.optBoolean("isCanUseStorage", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isCanUseWifiState() {
                return jSONObject.optBoolean("isCanUseWifiState", true);
            }

            @Override // io.dcloud.sdk.core.DCloudAOLManager.PrivacyConfig
            public boolean isGDTAgreeStrategy() {
                return jSONObject.optBoolean("isGDTAgreeStrategy", true);
            }
        };
    }

    public static void setCustomPrivacyConfig(Context context, String str) {
        d4.a(context, "dcloud-ads", "CustomPrivacyConfig", str);
    }

    public static void setPersonalAd(Context context, boolean z) {
        d4.a(context, "dcloud-ads", "PersonalizedAdEnable", String.valueOf(z));
    }

    public static DCloudAOLManager.PrivacyConfig getCustomPrivacyConfig() {
        return a;
    }

    public static void setCustomPrivacyConfig(DCloudAOLManager.PrivacyConfig privacyConfig) {
        a = privacyConfig;
    }
}
