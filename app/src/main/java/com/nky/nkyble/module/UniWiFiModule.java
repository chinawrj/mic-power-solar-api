package com.nky.nkyble.module;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.nky.nkyble.permiss.RequestPermissionCallback;
import com.nky.nkyble.permiss.RequestPermissionHelper;
import com.nky.nkyble.util.LocalUtils;
import com.nky.nkyble.wifi.WiFiScanCallback;
import com.nky.nkyble.wifi.WifiScanHelper;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;
import java.util.List;

/* loaded from: classes.dex */
public class UniWiFiModule extends UniModule {
    public UniJSCallback initCallback;

    static /* synthetic */ void lambda$requestScanWifi$0(DialogInterface dialogInterface) {
    }

    @UniJSMethod(uiThread = true)
    public void requestScanWifi(JSONObject jSONObject, UniJSCallback uniJSCallback) {
        this.initCallback = uniJSCallback;
        String string = jSONObject.getString("ok");
        String string2 = jSONObject.getString(BindingXConstants.STATE_CANCEL);
        final FragmentActivity fragmentActivity = (FragmentActivity) this.mUniSDKInstance.getContext();
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("tips"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_notice"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_camera"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_location"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_bluetooth"));
        RequestPermissionHelper.getInstance().setTips(string);
        String string3 = jSONObject.getString("gps_title");
        String string4 = jSONObject.getString("gps_content");
        if (LocalUtils.checkGPSIsOpen(fragmentActivity)) {
            toScan(fragmentActivity, uniJSCallback);
        } else if (uniJSCallback != null) {
            try {
                new AlertDialog.Builder(fragmentActivity).setTitle(string3).setMessage(string4).setPositiveButton(string, new DialogInterface.OnClickListener() { // from class: com.nky.nkyble.module.UniWiFiModule.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LocalUtils.goToOpenGPS(fragmentActivity);
                    }
                }).setNegativeButton(string2, new DialogInterface.OnClickListener() { // from class: com.nky.nkyble.module.UniWiFiModule.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.nky.nkyble.module.UniWiFiModule$$ExternalSyntheticLambda1
                    @Override // android.content.DialogInterface.OnCancelListener
                    public final void onCancel(DialogInterface dialogInterface) {
                        UniWiFiModule.lambda$requestScanWifi$0(dialogInterface);
                    }
                }).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void toScan(final Activity activity, final UniJSCallback uniJSCallback) {
        RequestPermissionHelper.getInstance().requestLocationPermission(activity, new RequestPermissionCallback() { // from class: com.nky.nkyble.module.UniWiFiModule$$ExternalSyntheticLambda2
            @Override // com.nky.nkyble.permiss.RequestPermissionCallback
            public final void onResult(boolean z) {
                UniWiFiModule.lambda$toScan$2(activity, uniJSCallback, z);
            }
        });
    }

    static /* synthetic */ void lambda$toScan$2(Activity activity, final UniJSCallback uniJSCallback, boolean z) {
        if (z) {
            WifiScanHelper.getInstance().requestScan(activity, new WiFiScanCallback() { // from class: com.nky.nkyble.module.UniWiFiModule$$ExternalSyntheticLambda0
                @Override // com.nky.nkyble.wifi.WiFiScanCallback
                public final void onResult(List list) {
                    UniWiFiModule.lambda$null$1(uniJSCallback, list);
                }
            });
        }
    }

    static /* synthetic */ void lambda$null$1(UniJSCallback uniJSCallback, List list) {
        String json = new Gson().toJson(list);
        if (uniJSCallback != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, (Object) "扫描wifi结果");
                jSONObject.put("code", (Object) "1");
                jSONObject.put("data", (Object) json);
                uniJSCallback.invoke(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 102) {
            toScan((FragmentActivity) this.mUniSDKInstance.getContext(), this.initCallback);
        }
    }
}
