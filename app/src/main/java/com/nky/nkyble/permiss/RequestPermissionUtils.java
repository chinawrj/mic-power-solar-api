package com.nky.nkyble.permiss;

import android.content.Context;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.hjq.permissions.Permission;

/* loaded from: classes.dex */
public class RequestPermissionUtils {
    private static final int PERMISSION_REQUEST_CODE = 4896;

    public interface Callback {
        void onResult(boolean z);
    }

    public static void requestBLEPermission(FragmentActivity fragmentActivity, Callback callback) {
        String[] strArr = {Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION};
        if (Build.VERSION.SDK_INT >= 31) {
            strArr = new String[]{Permission.ACCESS_FINE_LOCATION, Permission.ACCESS_COARSE_LOCATION, Permission.BLUETOOTH_SCAN, Permission.BLUETOOTH_CONNECT};
        }
        if (hasPermission(fragmentActivity, strArr)) {
            callback.onResult(true);
        } else {
            fragmentActivity.requestPermissions(strArr, PERMISSION_REQUEST_CODE);
        }
    }

    public static boolean hasPermission(Context context, String... strArr) {
        for (String str : strArr) {
            if (ActivityCompat.checkSelfPermission(context, str) == -1) {
                return false;
            }
        }
        return true;
    }
}
