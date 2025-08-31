package com.nky.nkyble.util;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;

/* loaded from: classes.dex */
public class LocalUtils {
    public static final int OPEN_GPS_CODE = 102;

    public static boolean checkGPSIsOpen(Activity activity) {
        LocationManager locationManager = (LocationManager) activity.getSystemService("location");
        if (locationManager == null) {
            return false;
        }
        return locationManager.isProviderEnabled("gps");
    }

    public static void goToOpenGPS(Activity activity) {
        activity.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 102);
    }
}
