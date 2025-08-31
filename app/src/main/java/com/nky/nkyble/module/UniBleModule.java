package com.nky.nkyble.module;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.fastjson.JSONObject;
import com.clj.fastble.data.BleDevice;
import com.google.gson.Gson;
import com.hjq.permissions.Permission;
import com.nky.nkyble.bean.BleBean;
import com.nky.nkyble.bean.ConnectError;
import com.nky.nkyble.ble.BleClient2;
import com.nky.nkyble.ble.BleConnetLiseners;
import com.nky.nkyble.ble.BleRequestCallback;
import com.nky.nkyble.ble.BleRequestException;
import com.nky.nkyble.ble.BleScanManager;
import com.nky.nkyble.permiss.RequestPermissionCallback;
import com.nky.nkyble.permiss.RequestPermissionHelper;
import com.nky.nkyble.util.BluetoothUtils;
import com.nky.nkyble.util.LocalUtils;
import com.taobao.weex.common.Constants;
import com.taobao.weex.performance.WXInstanceApm;
import io.dcloud.common.util.ExifInterface;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class UniBleModule extends UniModule {
    private BleClient2 bleClient2;
    private List<BleBean> bleList = new ArrayList();
    private BleScanManager bleScanManager;
    public UniJSCallback initCallback;

    @UniJSMethod(uiThread = true)
    public void initBleManager(JSONObject jSONObject, UniJSCallback uniJSCallback) {
        this.initCallback = uniJSCallback;
        RequestPermissionHelper.getInstance().setTips(jSONObject.getString("tips"));
        RequestPermissionHelper.getInstance().setPermission_notice(jSONObject.getString("permission_notice"));
        RequestPermissionHelper.getInstance().setPermission_camera(jSONObject.getString("permission_camera"));
        RequestPermissionHelper.getInstance().setPermission_location(jSONObject.getString("permission_location"));
        RequestPermissionHelper.getInstance().setPermission_bluetooth(jSONObject.getString("permission_bluetooth"));
        RequestPermissionHelper.getInstance().setOk(jSONObject.getString("ok"));
        FragmentActivity fragmentActivity = (FragmentActivity) this.mUniSDKInstance.getContext();
        if (!LocalUtils.checkGPSIsOpen(fragmentActivity)) {
            LocalUtils.goToOpenGPS(fragmentActivity);
        } else {
            checkPermission();
        }
    }

    private void checkPermission() {
        FragmentActivity fragmentActivity = (FragmentActivity) this.mUniSDKInstance.getContext();
        if (!hasPermission(fragmentActivity, getBLEPermission())) {
            RequestPermissionHelper.getInstance().requestBLEPermission(fragmentActivity, new RequestPermissionCallback() { // from class: com.nky.nkyble.module.UniBleModule$$ExternalSyntheticLambda0
                @Override // com.nky.nkyble.permiss.RequestPermissionCallback
                public final void onResult(boolean z) {
                    this.f$0.m233lambda$checkPermission$0$comnkynkyblemoduleUniBleModule(z);
                }
            });
        } else if (BluetoothUtils.isBluetoothOpen()) {
            toInitBleManage();
        } else {
            toOpenBlueTooth();
        }
    }

    /* renamed from: lambda$checkPermission$0$com-nky-nkyble-module-UniBleModule, reason: not valid java name */
    /* synthetic */ void m233lambda$checkPermission$0$comnkynkyblemoduleUniBleModule(boolean z) {
        if (z) {
            if (BluetoothUtils.isBluetoothOpen()) {
                toInitBleManage();
                return;
            } else {
                toOpenBlueTooth();
                return;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, (Object) "拒绝了权限");
            jSONObject.put("code", (Object) WXInstanceApm.VALUE_ERROR_CODE_DEFAULT);
            jSONObject.put("data", (Object) "");
            this.initCallback.invoke(jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getBLEPermission() {
        return Build.VERSION.SDK_INT >= 31 ? new String[]{Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION, Permission.BLUETOOTH_SCAN, Permission.BLUETOOTH_CONNECT} : new String[]{Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION};
    }

    public static boolean hasPermission(Context context, String... strArr) {
        for (String str : strArr) {
            if (ActivityCompat.checkSelfPermission(context, str) == -1) {
                return false;
            }
        }
        return true;
    }

    private void toInitBleManage() {
        this.bleClient2 = BleClient2.getInstance();
        this.bleScanManager = BleScanManager.getInstance();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NotificationCompat.CATEGORY_MESSAGE, (Object) "蓝牙SDK初始化");
            jSONObject.put("code", (Object) "1");
            jSONObject.put("data", (Object) "");
            this.initCallback.invoke(jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toOpenBlueTooth() {
        BluetoothUtils.openBluetooth((FragmentActivity) this.mUniSDKInstance.getContext(), 10000);
    }

    @UniJSMethod(uiThread = true)
    public void scanBle() {
        this.bleScanManager.startScan(new BleScanManager.Scanlisteners() { // from class: com.nky.nkyble.module.UniBleModule.1
            @Override // com.nky.nkyble.ble.BleScanManager.Scanlisteners
            public void scanStart() {
                UniBleModule.this.bleList.clear();
                HashMap map = new HashMap();
                map.put(NotificationCompat.CATEGORY_MESSAGE, "开始扫描");
                map.put("code", "1");
                map.put("data", "");
                UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("scanBle", map);
            }

            @Override // com.nky.nkyble.ble.BleScanManager.Scanlisteners
            public void addBleData(BleBean bleBean) {
                UniBleModule.this.bleList.add(bleBean);
                String json = new Gson().toJson(bleBean);
                HashMap map = new HashMap();
                map.put(NotificationCompat.CATEGORY_MESSAGE, "扫描到蓝牙");
                map.put("code", ExifInterface.GPS_MEASUREMENT_2D);
                map.put("data", json);
                UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("scanBle", map);
            }

            @Override // com.nky.nkyble.ble.BleScanManager.Scanlisteners
            public void scanFail() {
                HashMap map = new HashMap();
                map.put(NotificationCompat.CATEGORY_MESSAGE, "蓝牙扫描失败");
                map.put("code", ExifInterface.GPS_MEASUREMENT_3D);
                map.put("data", "");
                UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("scanBle", map);
            }

            @Override // com.nky.nkyble.ble.BleScanManager.Scanlisteners
            public void scanFinish(List<BleDevice> list) {
                String json = new Gson().toJson(list);
                HashMap map = new HashMap();
                map.put(NotificationCompat.CATEGORY_MESSAGE, "蓝牙扫描完成");
                map.put("code", "4");
                map.put("data", json);
                UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("scanBle", map);
            }
        });
    }

    @UniJSMethod(uiThread = true)
    public void stopScan() {
        try {
            this.bleScanManager.stoptScan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @UniJSMethod(uiThread = true)
    public void connectBle(String str) {
        BleDevice device;
        try {
            this.bleScanManager.stoptScan();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        while (true) {
            if (i >= this.bleList.size()) {
                device = null;
                break;
            }
            BleBean bleBean = this.bleList.get(i);
            if (str.equals(bleBean.getAddress())) {
                device = bleBean.getDevice();
                break;
            }
            i++;
        }
        if (device != null) {
            this.bleClient2.connet(device, new BleConnetLiseners() { // from class: com.nky.nkyble.module.UniBleModule.2
                @Override // com.nky.nkyble.ble.BleConnetLiseners
                public void bleConnectedStart() {
                    HashMap map = new HashMap();
                    map.put(NotificationCompat.CATEGORY_MESSAGE, "开始连接");
                    map.put("code", "1");
                    map.put("data", "");
                    UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("connectBle", map);
                }

                @Override // com.nky.nkyble.ble.BleConnetLiseners
                public void bleConnectedSuccess() {
                    HashMap map = new HashMap();
                    map.put(NotificationCompat.CATEGORY_MESSAGE, "蓝牙已连接");
                    map.put("code", ExifInterface.GPS_MEASUREMENT_2D);
                    map.put("data", "");
                    UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("connectBle", map);
                }

                @Override // com.nky.nkyble.ble.BleConnetLiseners
                public void bleConnectedFail(ConnectError connectError) {
                    HashMap map = new HashMap();
                    map.put("code", connectError.code + "");
                    map.put(NotificationCompat.CATEGORY_MESSAGE, connectError.msg);
                    map.put("data", Constants.Event.FAIL);
                    UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("connectBle", map);
                }

                @Override // com.nky.nkyble.ble.BleConnetLiseners
                public void bleDisConnected() {
                    HashMap map = new HashMap();
                    map.put(NotificationCompat.CATEGORY_MESSAGE, "蓝牙连接已断开");
                    map.put("code", WXInstanceApm.VALUE_ERROR_CODE_DEFAULT);
                    map.put("data", "");
                    UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("connectBle", map);
                }
            });
        }
    }

    @UniJSMethod(uiThread = true)
    public void bleSend(String str) {
        receiveData(hexStringToByteArray(str));
    }

    public byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public void receiveData(byte[] bArr) {
        this.bleClient2.sendDataResepone(bArr, new BleRequestCallback() { // from class: com.nky.nkyble.module.UniBleModule.3
            @Override // com.nky.nkyble.ble.BleRequestCallback
            public void requstSuccess() {
            }

            @Override // com.nky.nkyble.ble.BleRequestCallback
            public void requestResepon(byte[] bArr2) {
                HashMap map = new HashMap();
                map.put(NotificationCompat.CATEGORY_MESSAGE, "蓝牙接收到数据");
                map.put("code", "1");
                map.put("data", UniBleModule.bytesToHexString(bArr2));
                UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("bleSend", map);
            }

            @Override // com.nky.nkyble.ble.BleRequestCallback
            public void sendMsgFail(BleRequestException bleRequestException) {
                HashMap map = new HashMap();
                map.put(NotificationCompat.CATEGORY_MESSAGE, "蓝牙发送数据失败");
                map.put("code", Integer.valueOf(bleRequestException.getCode()));
                map.put("data", bleRequestException.getDescription());
                UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("bleSend", map);
            }

            @Override // com.nky.nkyble.ble.BleRequestCallback
            public void writeSuccess(int i, int i2, byte[] bArr2) {
                try {
                    new JSONObject();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("current", (Object) Integer.valueOf(i));
                    jSONObject.put("total", (Object) Integer.valueOf(i2));
                    jSONObject.put("justWrite", (Object) UniBleModule.bytesToHexString(bArr2));
                    HashMap map = new HashMap();
                    map.put(NotificationCompat.CATEGORY_MESSAGE, "写入数据成功");
                    map.put("code", ExifInterface.GPS_MEASUREMENT_2D);
                    map.put("data", jSONObject);
                    UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("bleSend", map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.nky.nkyble.ble.BleRequestCallback
            public void writeFail() {
                HashMap map = new HashMap();
                map.put("code", ExifInterface.GPS_MEASUREMENT_3D);
                map.put(NotificationCompat.CATEGORY_MESSAGE, "-1");
                map.put("data", "");
                UniBleModule.this.mUniSDKInstance.fireGlobalEventCallback("bleSend", map);
            }
        });
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    @UniJSMethod(uiThread = true)
    public void disConnetBle() {
        this.bleClient2.disConnet();
    }

    @Override // com.taobao.weex.common.WXModule
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 102) {
            checkPermission();
        }
        if (i == 10000 && i2 == -1) {
            toInitBleManage();
        }
    }
}
