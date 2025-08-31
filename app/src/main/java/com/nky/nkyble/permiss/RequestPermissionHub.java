package com.nky.nkyble.permiss;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.hjq.permissions.Permission;

/* loaded from: classes.dex */
public class RequestPermissionHub extends Fragment {
    private static final int APP_SETTING_REQUEST_CODE = 4851;
    private static final int PERMISSION_REQUEST_CODE = 4896;
    private Callback callback;
    private String[] permissions;
    private boolean promptToSet = true;

    public interface Callback {
        void onResult(boolean z);
    }

    public static boolean hasPermission(Context context, String... strArr) {
        for (String str : strArr) {
            if (ActivityCompat.checkSelfPermission(context, str) == -1) {
                return false;
            }
        }
        return true;
    }

    public static void requestPermission(FragmentManager fragmentManager, Callback callback, boolean z, String... strArr) {
        RequestPermissionHub requestPermissionHub = new RequestPermissionHub();
        requestPermissionHub.permissions = strArr;
        requestPermissionHub.callback = callback;
        requestPermissionHub.promptToSet = z;
        fragmentManager.beginTransaction().add(requestPermissionHub, RequestPermissionHub.class.getName()).commitAllowingStateLoss();
    }

    public static void requestCameraPermission(FragmentManager fragmentManager, Callback callback) {
        RequestPermissionHub requestPermissionHub = new RequestPermissionHub();
        requestPermissionHub.permissions = new String[]{Permission.CAMERA};
        requestPermissionHub.callback = callback;
        requestPermissionHub.promptToSet = true;
        fragmentManager.beginTransaction().add(requestPermissionHub, RequestPermissionHub.class.getName()).commitAllowingStateLoss();
    }

    public static void requestAlbumPermission(FragmentManager fragmentManager, Callback callback) {
        RequestPermissionHub requestPermissionHub = new RequestPermissionHub();
        requestPermissionHub.permissions = new String[]{getAlbumPermission()};
        requestPermissionHub.callback = callback;
        requestPermissionHub.promptToSet = true;
        fragmentManager.beginTransaction().add(requestPermissionHub, RequestPermissionHub.class.getName()).commitAllowingStateLoss();
    }

    public static void requestLocationPermission(FragmentManager fragmentManager, Callback callback) {
        RequestPermissionHub requestPermissionHub = new RequestPermissionHub();
        requestPermissionHub.permissions = new String[]{Permission.ACCESS_COARSE_LOCATION, Permission.ACCESS_FINE_LOCATION};
        requestPermissionHub.callback = callback;
        requestPermissionHub.promptToSet = true;
        fragmentManager.beginTransaction().add(requestPermissionHub, RequestPermissionHub.class.getName()).commitAllowingStateLoss();
    }

    public static String getAlbumPermission() {
        return Build.VERSION.SDK_INT >= 33 ? Permission.READ_MEDIA_IMAGES : Permission.READ_EXTERNAL_STORAGE;
    }

    public static void requestNotificationPermission(FragmentManager fragmentManager, Callback callback, boolean z) {
        if (Build.VERSION.SDK_INT >= 33) {
            requestPermission(fragmentManager, callback, z, Permission.POST_NOTIFICATIONS);
        } else if (callback != null) {
            callback.onResult(true);
        }
    }

    public static void requestBLEPermission(FragmentManager fragmentManager, Callback callback) {
        if (Build.VERSION.SDK_INT >= 31) {
            requestPermission(fragmentManager, callback, true, Permission.BLUETOOTH_SCAN, Permission.BLUETOOTH_CONNECT);
        } else {
            requestPermission(fragmentManager, callback, true, Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (hasPermission(requireContext(), this.permissions)) {
            handleCallback(true);
            detach();
        } else {
            requestPermissions(this.permissions, PERMISSION_REQUEST_CODE);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == PERMISSION_REQUEST_CODE) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr[i2] == -1) {
                    handleCallback(false);
                    if (this.promptToSet) {
                        showOpenAppSettingDialog(strArr[i2]);
                        return;
                    }
                    return;
                }
            }
            handleCallback(true);
            detach();
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    private void handleCallback(boolean z) {
        Callback callback = this.callback;
        if (callback != null) {
            callback.onResult(z);
        }
    }

    private void showOpenAppSettingDialog(String str) {
        String str2;
        str.hashCode();
        switch (str) {
            case "android.permission.POST_NOTIFICATIONS":
                str2 = "您未开启通知权限，请在系统设置中开启";
                break;
            case "android.permission.ACCESS_FINE_LOCATION":
            case "android.permission.ACCESS_COARSE_LOCATION":
                str2 = "您未开启位置权限，请在系统设置中开启";
                break;
            case "android.permission.BLUETOOTH_CONNECT":
            case "android.permission.BLUETOOTH_SCAN":
                str2 = "您未开启蓝牙权限，请在系统设置中开启";
                break;
            case "android.permission.CAMERA":
                str2 = "您未开启相机权限，请在系统设置中开启";
                break;
            default:
                str2 = "";
                break;
        }
        new AlertDialog.Builder(getContext()).setTitle("温馨提示").setMessage(str2).setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.nky.nkyble.permiss.RequestPermissionHub.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                RequestPermissionHub.this.openAppSetting();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.nky.nkyble.permiss.RequestPermissionHub.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                RequestPermissionHub.this.detach();
            }
        }).show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        detach();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openAppSetting() {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", requireContext().getPackageName(), null));
        startActivityForResult(intent, APP_SETTING_REQUEST_CODE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void detach() {
        getParentFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }
}
