package com.facebook.drawee.backends.pipeline.info;

import com.taobao.weex.ui.component.WXImage;

/* loaded from: classes.dex */
public class ImagePerfUtils {
    public static String toString(int imageLoadStatus) {
        if (imageLoadStatus == 0) {
            return "requested";
        }
        if (imageLoadStatus == 1) {
            return "origin_available";
        }
        if (imageLoadStatus == 2) {
            return "intermediate_available";
        }
        if (imageLoadStatus == 3) {
            return WXImage.SUCCEED;
        }
        if (imageLoadStatus == 4) {
            return "canceled";
        }
        if (imageLoadStatus == 5) {
            return "error";
        }
        return "unknown";
    }

    private ImagePerfUtils() {
    }
}
