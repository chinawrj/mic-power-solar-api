package com.nky.nkyble.permiss;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.nky.nkyble.util.LanguageUtil;

/* loaded from: classes.dex */
public class RequestPermission extends AppCompatActivity {
    private static final int APP_SETTING_REQUEST_CODE = 4851;
    private static final int PERMISSION_REQUEST_CODE = 4896;
    private String[] permissions;
    private boolean promptToSet = true;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.promptToSet = intent.getBooleanExtra("promptToSet", false);
            String[] stringArrayExtra = intent.getStringArrayExtra("permissions");
            this.permissions = stringArrayExtra;
            if (stringArrayExtra == null || stringArrayExtra.length == 0) {
                handleCallback(true);
                return;
            } else if (hasPermission(this, stringArrayExtra)) {
                handleCallback(true);
                detach();
                return;
            } else {
                requestPermissions(this.permissions, PERMISSION_REQUEST_CODE);
                return;
            }
        }
        handleCallback(true);
        finish();
    }

    public static boolean hasPermission(Context context, String... strArr) {
        for (String str : strArr) {
            if (ActivityCompat.checkSelfPermission(context, str) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageUtil.getNewLocalContext(context));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
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
        RequestPermissionCallback callback = RequestPermissionHelper.getInstance().getCallback();
        if (callback != null) {
            callback.onResult(z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void showOpenAppSettingDialog(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 1
            com.nky.nkyble.permiss.RequestPermissionHelper r1 = com.nky.nkyble.permiss.RequestPermissionHelper.getInstance()     // Catch: java.lang.Exception -> L9b
            java.lang.String r2 = ""
            int r3 = r9.hashCode()     // Catch: java.lang.Exception -> L9b
            r4 = 5
            r5 = 4
            r6 = 3
            r7 = 2
            switch(r3) {
                case -1925850455: goto L45;
                case -1888586689: goto L3b;
                case -798669607: goto L31;
                case -63024214: goto L27;
                case 463403621: goto L1d;
                case 2062356686: goto L13;
                default: goto L12;
            }     // Catch: java.lang.Exception -> L9b
        L12:
            goto L4f
        L13:
            java.lang.String r3 = "android.permission.BLUETOOTH_SCAN"
            boolean r9 = r9.equals(r3)     // Catch: java.lang.Exception -> L9b
            if (r9 == 0) goto L4f
            r9 = r5
            goto L50
        L1d:
            java.lang.String r3 = "android.permission.CAMERA"
            boolean r9 = r9.equals(r3)     // Catch: java.lang.Exception -> L9b
            if (r9 == 0) goto L4f
            r9 = r0
            goto L50
        L27:
            java.lang.String r3 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r9 = r9.equals(r3)     // Catch: java.lang.Exception -> L9b
            if (r9 == 0) goto L4f
            r9 = r6
            goto L50
        L31:
            java.lang.String r3 = "android.permission.BLUETOOTH_CONNECT"
            boolean r9 = r9.equals(r3)     // Catch: java.lang.Exception -> L9b
            if (r9 == 0) goto L4f
            r9 = r4
            goto L50
        L3b:
            java.lang.String r3 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r9 = r9.equals(r3)     // Catch: java.lang.Exception -> L9b
            if (r9 == 0) goto L4f
            r9 = r7
            goto L50
        L45:
            java.lang.String r3 = "android.permission.POST_NOTIFICATIONS"
            boolean r9 = r9.equals(r3)     // Catch: java.lang.Exception -> L9b
            if (r9 == 0) goto L4f
            r9 = 0
            goto L50
        L4f:
            r9 = -1
        L50:
            if (r9 == 0) goto L6c
            if (r9 == r0) goto L67
            if (r9 == r7) goto L62
            if (r9 == r6) goto L62
            if (r9 == r5) goto L5d
            if (r9 == r4) goto L5d
            goto L70
        L5d:
            java.lang.String r2 = r1.getPermission_bluetooth()     // Catch: java.lang.Exception -> L9b
            goto L70
        L62:
            java.lang.String r2 = r1.getPermission_location()     // Catch: java.lang.Exception -> L9b
            goto L70
        L67:
            java.lang.String r2 = r1.getPermission_camera()     // Catch: java.lang.Exception -> L9b
            goto L70
        L6c:
            java.lang.String r2 = r1.getPermission_notice()     // Catch: java.lang.Exception -> L9b
        L70:
            android.app.AlertDialog$Builder r9 = new android.app.AlertDialog$Builder     // Catch: java.lang.Exception -> L9b
            r9.<init>(r8)     // Catch: java.lang.Exception -> L9b
            java.lang.String r3 = r1.getTips()     // Catch: java.lang.Exception -> L9b
            android.app.AlertDialog$Builder r9 = r9.setTitle(r3)     // Catch: java.lang.Exception -> L9b
            android.app.AlertDialog$Builder r9 = r9.setMessage(r2)     // Catch: java.lang.Exception -> L9b
            java.lang.String r1 = r1.getOk()     // Catch: java.lang.Exception -> L9b
            com.nky.nkyble.permiss.RequestPermission$$ExternalSyntheticLambda0 r2 = new com.nky.nkyble.permiss.RequestPermission$$ExternalSyntheticLambda0     // Catch: java.lang.Exception -> L9b
            r2.<init>()     // Catch: java.lang.Exception -> L9b
            android.app.AlertDialog$Builder r9 = r9.setPositiveButton(r1, r2)     // Catch: java.lang.Exception -> L9b
            com.nky.nkyble.permiss.RequestPermission$$ExternalSyntheticLambda1 r1 = new com.nky.nkyble.permiss.RequestPermission$$ExternalSyntheticLambda1     // Catch: java.lang.Exception -> L9b
            r1.<init>()     // Catch: java.lang.Exception -> L9b
            android.app.AlertDialog$Builder r9 = r9.setOnCancelListener(r1)     // Catch: java.lang.Exception -> L9b
            r9.show()     // Catch: java.lang.Exception -> L9b
            goto Lab
        L9b:
            r9 = move-exception
            android.content.Context r1 = io.dcloud.feature.audio.recorder.RecorderUtil.getContext()
            java.lang.String r9 = r9.getMessage()
            android.widget.Toast r9 = android.widget.Toast.makeText(r1, r9, r0)
            r9.show()
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nky.nkyble.permiss.RequestPermission.showOpenAppSettingDialog(java.lang.String):void");
    }

    /* renamed from: lambda$showOpenAppSettingDialog$0$com-nky-nkyble-permiss-RequestPermission, reason: not valid java name */
    /* synthetic */ void m235xb6b41796(DialogInterface dialogInterface, int i) {
        openAppSetting();
    }

    /* renamed from: lambda$showOpenAppSettingDialog$1$com-nky-nkyble-permiss-RequestPermission, reason: not valid java name */
    /* synthetic */ void m236xbe194cb5(DialogInterface dialogInterface) {
        detach();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        detach();
    }

    private void openAppSetting() {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        startActivityForResult(intent, APP_SETTING_REQUEST_CODE);
        finish();
    }

    private void detach() {
        finish();
    }
}
