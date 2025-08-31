package com.nky.nkyble.module;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.fastjson.JSONObject;
import com.nky.nkyble.permiss.RequestPermissionCallback;
import com.nky.nkyble.permiss.RequestPermissionHelper;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;
import uni.dcloud.io.uniplugin_module.R;

/* loaded from: classes.dex */
public class UniPermissionModule extends UniModule {
    private UniJSCallback callback;

    @UniJSMethod(uiThread = true)
    public void requestBlePermission(JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        final FragmentActivity fragmentActivity = (FragmentActivity) this.mUniSDKInstance.getContext();
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("tips"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_notice"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_camera"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_location"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_bluetooth"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("ok"));
        RequestPermissionHelper.getInstance().requestBLEPermission(fragmentActivity, new RequestPermissionCallback() { // from class: com.nky.nkyble.module.UniPermissionModule$$ExternalSyntheticLambda2
            @Override // com.nky.nkyble.permiss.RequestPermissionCallback
            public final void onResult(boolean z) {
                UniPermissionModule.lambda$requestBlePermission$0(uniJSCallback, fragmentActivity, z);
            }
        });
    }

    static /* synthetic */ void lambda$requestBlePermission$0(UniJSCallback uniJSCallback, FragmentActivity fragmentActivity, boolean z) {
        if (uniJSCallback != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("result", (Object) Boolean.valueOf(z));
                if (z) {
                    jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, (Object) fragmentActivity.getString(R.string.success));
                } else {
                    jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, (Object) fragmentActivity.getString(R.string.fail));
                }
                jSONObject.put("data", (Object) "");
                uniJSCallback.invoke(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @UniJSMethod(uiThread = true)
    public void requestLocationPermission(JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        final FragmentActivity fragmentActivity = (FragmentActivity) this.mUniSDKInstance.getContext();
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("tips"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_notice"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_camera"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_location"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_bluetooth"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("ok"));
        RequestPermissionHelper.getInstance().requestLocationPermission(fragmentActivity, new RequestPermissionCallback() { // from class: com.nky.nkyble.module.UniPermissionModule$$ExternalSyntheticLambda3
            @Override // com.nky.nkyble.permiss.RequestPermissionCallback
            public final void onResult(boolean z) {
                UniPermissionModule.lambda$requestLocationPermission$1(uniJSCallback, fragmentActivity, z);
            }
        });
    }

    static /* synthetic */ void lambda$requestLocationPermission$1(UniJSCallback uniJSCallback, FragmentActivity fragmentActivity, boolean z) {
        if (uniJSCallback != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("result", (Object) Boolean.valueOf(z));
                if (z) {
                    jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, (Object) fragmentActivity.getString(R.string.success));
                } else {
                    jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, (Object) fragmentActivity.getString(R.string.fail));
                }
                jSONObject.put("data", (Object) "");
                uniJSCallback.invoke(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @UniJSMethod(uiThread = true)
    public void requestAlbumPermission(JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        final FragmentActivity fragmentActivity = (FragmentActivity) this.mUniSDKInstance.getContext();
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("tips"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_notice"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_camera"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_location"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_bluetooth"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("ok"));
        RequestPermissionHelper.getInstance().requestAlbumPermission(fragmentActivity, new RequestPermissionCallback() { // from class: com.nky.nkyble.module.UniPermissionModule$$ExternalSyntheticLambda1
            @Override // com.nky.nkyble.permiss.RequestPermissionCallback
            public final void onResult(boolean z) {
                UniPermissionModule.lambda$requestAlbumPermission$2(uniJSCallback, fragmentActivity, z);
            }
        });
    }

    static /* synthetic */ void lambda$requestAlbumPermission$2(UniJSCallback uniJSCallback, FragmentActivity fragmentActivity, boolean z) {
        if (uniJSCallback != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("result", (Object) Boolean.valueOf(z));
                if (z) {
                    jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, (Object) fragmentActivity.getString(R.string.success));
                } else {
                    jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, (Object) fragmentActivity.getString(R.string.fail));
                }
                jSONObject.put("data", (Object) "");
                uniJSCallback.invoke(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @UniJSMethod(uiThread = true)
    public void requestCameraPermission(JSONObject jSONObject, final UniJSCallback uniJSCallback) {
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("tips"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_notice"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_camera"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_location"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("permission_bluetooth"));
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("ok"));
        final FragmentActivity fragmentActivity = (FragmentActivity) this.mUniSDKInstance.getContext();
        RequestPermissionHelper.getInstance().requestCameraPermission(fragmentActivity, new RequestPermissionCallback() { // from class: com.nky.nkyble.module.UniPermissionModule$$ExternalSyntheticLambda0
            @Override // com.nky.nkyble.permiss.RequestPermissionCallback
            public final void onResult(boolean z) {
                UniPermissionModule.lambda$requestCameraPermission$3(uniJSCallback, fragmentActivity, z);
            }
        });
    }

    static /* synthetic */ void lambda$requestCameraPermission$3(UniJSCallback uniJSCallback, FragmentActivity fragmentActivity, boolean z) {
        if (uniJSCallback != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("result", (Object) Boolean.valueOf(z));
                if (z) {
                    jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, (Object) fragmentActivity.getString(R.string.success));
                } else {
                    jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, (Object) fragmentActivity.getString(R.string.fail));
                }
                jSONObject.put("data", (Object) "");
                uniJSCallback.invoke(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
