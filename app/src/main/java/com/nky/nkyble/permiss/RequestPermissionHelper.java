package com.nky.nkyble.permiss;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.hjq.permissions.Permission;

/* loaded from: classes.dex */
public class RequestPermissionHelper {
    private RequestPermissionCallback callback;
    private String ok;
    private String permission_bluetooth;
    private String permission_camera;
    private String permission_location;
    private String permission_notice;
    private String tips;

    public static class RequestHelper {
        public static RequestPermissionHelper instance = new RequestPermissionHelper();
    }

    public static RequestPermissionHelper getInstance() {
        return RequestHelper.instance;
    }

    public RequestPermissionCallback getCallback() {
        return this.callback;
    }

    public void requestCameraPermission(Context context, RequestPermissionCallback requestPermissionCallback) {
        this.callback = requestPermissionCallback;
        String[] strArr = {Permission.CAMERA};
        Intent intent = new Intent(context, (Class<?>) RequestPermission.class);
        intent.putExtra("permissions", strArr);
        context.startActivity(intent);
    }

    public void requestAlbumPermission(Context context, RequestPermissionCallback requestPermissionCallback) {
        this.callback = requestPermissionCallback;
        String[] strArr = {getAlbumPermission()};
        Intent intent = new Intent(context, (Class<?>) RequestPermission.class);
        intent.putExtra("permissions", strArr);
        context.startActivity(intent);
    }

    public void requestLocationPermission(Context context, RequestPermissionCallback requestPermissionCallback) {
        this.callback = requestPermissionCallback;
        String[] strArr = {Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_FINE_LOCATION};
        Intent intent = new Intent(context, (Class<?>) RequestPermission.class);
        intent.putExtra("permissions", strArr);
        context.startActivity(intent);
    }

    public void requestBLEPermission(Context context, RequestPermissionCallback requestPermissionCallback) {
        this.callback = requestPermissionCallback;
        String[] strArr = {Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION};
        if (Build.VERSION.SDK_INT >= 31) {
            strArr = new String[]{Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION, Permission.BLUETOOTH_SCAN, Permission.BLUETOOTH_CONNECT};
        }
        requestPermission(context, true, strArr);
    }

    public static void requestPermission(Context context, boolean z, String... strArr) {
        Intent intent = new Intent(context, (Class<?>) RequestPermission.class);
        intent.putExtra("permissions", strArr);
        intent.putExtra("promptToSet", z);
        context.startActivity(intent);
    }

    public static String getAlbumPermission() {
        return Build.VERSION.SDK_INT >= 33 ? Permission.READ_MEDIA_IMAGES : Permission.READ_EXTERNAL_STORAGE;
    }

    public String getTips() {
        return this.tips;
    }

    public void setTips(String str) {
        this.tips = str;
    }

    public String getPermission_notice() {
        return this.permission_notice;
    }

    public void setPermission_notice(String str) {
        this.permission_notice = str;
    }

    public String getPermission_camera() {
        return this.permission_camera;
    }

    public void setPermission_camera(String str) {
        this.permission_camera = str;
    }

    public String getPermission_location() {
        return this.permission_location;
    }

    public void setPermission_location(String str) {
        this.permission_location = str;
    }

    public String getPermission_bluetooth() {
        return this.permission_bluetooth;
    }

    public void setPermission_bluetooth(String str) {
        this.permission_bluetooth = str;
    }

    public String getOk() {
        return this.ok;
    }

    public void setOk(String str) {
        this.ok = str;
    }
}
