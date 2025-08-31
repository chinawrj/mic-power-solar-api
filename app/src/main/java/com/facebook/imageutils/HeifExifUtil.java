package com.facebook.imageutils;

import android.os.Build;
import com.facebook.common.logging.FLog;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class HeifExifUtil {
    public static final String TAG = "HeifExifUtil";

    public static int getOrientation(final InputStream inputStream) {
        if (Build.VERSION.SDK_INT >= 24) {
            return HeifExifUtilAndroidN.getOrientation(inputStream);
        }
        FLog.d(TAG, "Trying to read Heif Exif information before Android N -> ignoring");
        return 0;
    }

    private static class HeifExifUtilAndroidN {
        private HeifExifUtilAndroidN() {
        }

        static int getOrientation(final InputStream inputStream) {
            try {
                Chip$$ExternalSyntheticApiModelOutline0.m219m();
                return Chip$$ExternalSyntheticApiModelOutline0.m(inputStream).getAttributeInt("Orientation", 1);
            } catch (IOException e) {
                FLog.d(HeifExifUtil.TAG, "Failed reading Heif Exif orientation -> ignoring", (Throwable) e);
                return 0;
            }
        }
    }
}
