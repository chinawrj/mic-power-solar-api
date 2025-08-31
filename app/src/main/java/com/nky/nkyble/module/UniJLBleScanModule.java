package com.nky.nkyble.module;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.fastjson.JSONObject;
import com.ginlong.near_tool_sdk.activity.BleConnectActivity;
import com.ginlong.near_tool_sdk.config.GlConfig;
import com.nky.nkyble.permiss.RequestPermissionCallback;
import com.nky.nkyble.permiss.RequestPermissionHelper;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public class UniJLBleScanModule extends UniModule {
    @UniJSMethod(uiThread = true)
    public void toNativePage(final JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        final FragmentActivity fragmentActivity = (FragmentActivity) this.mUniSDKInstance.getContext();
        RequestPermissionHelper.getInstance().requestBLEPermission(fragmentActivity, new RequestPermissionCallback() { // from class: com.nky.nkyble.module.UniJLBleScanModule$$ExternalSyntheticLambda0
            @Override // com.nky.nkyble.permiss.RequestPermissionCallback
            public final void onResult(boolean z) {
                this.f$0.m234lambda$toNativePage$1$comnkynkyblemoduleUniJLBleScanModule(jSONObject, uniJSCallback, fragmentActivity, z);
            }
        });
    }

    /* renamed from: lambda$toNativePage$1$com-nky-nkyble-module-UniJLBleScanModule, reason: not valid java name */
    /* synthetic */ void m234lambda$toNativePage$1$comnkynkyblemoduleUniJLBleScanModule(JSONObject jSONObject, final UniJSCallback uniJSCallback, FragmentActivity fragmentActivity, boolean z) {
        try {
            if (z) {
                new GlConfig.ConfigBuilder().setLanguage(jSONObject.getString(IApp.ConfigProperty.CONFIG_LANGUAGE)).setMainColor(jSONObject.getString("mainColor")).setBtnColor(jSONObject.getString("btnColor")).setAppKey(jSONObject.getString("appKey")).setApiKey(jSONObject.getString("apiKey")).setApiSecret(jSONObject.getString("apiSecret")).setAdapterType(jSONObject.getString("adapterType")).setSnCallback(new GlConfig.ConfigBuilder.SnCallback() { // from class: com.nky.nkyble.module.UniJLBleScanModule$$ExternalSyntheticLambda1
                    public final void onReceiveSn(String str, Function0 function0) {
                        UniJLBleScanModule.lambda$null$0(uniJSCallback, str, function0);
                    }
                }).init(fragmentActivity);
                fragmentActivity.startActivity(new Intent(fragmentActivity, (Class<?>) BleConnectActivity.class));
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("code", (Object) (-1));
                jSONObject2.put("data", (Object) "");
                jSONObject2.put(NotificationCompat.CATEGORY_MESSAGE, (Object) "权限请求失败");
            } catch (Exception e) {
                e.printStackTrace();
            }
            uniJSCallback.invoke(jSONObject2.toString());
        } catch (Exception e2) {
            showDialog(fragmentActivity, e2.toString());
        }
    }

    static /* synthetic */ void lambda$null$0(UniJSCallback uniJSCallback, String str, Function0 function0) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", (Object) 0);
            jSONObject.put("data", (Object) str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        function0.invoke();
        uniJSCallback.invoke(jSONObject.toString());
    }

    private void showDialog(FragmentActivity fragmentActivity, String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity);
        builder.setTitle("前端传参").setMessage(str).setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.nky.nkyble.module.UniJLBleScanModule.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.nky.nkyble.module.UniJLBleScanModule.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }
}
