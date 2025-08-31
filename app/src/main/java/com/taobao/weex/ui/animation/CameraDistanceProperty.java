package com.taobao.weex.ui.animation;

import android.util.Property;
import android.view.View;

/* loaded from: classes3.dex */
class CameraDistanceProperty extends Property<View, Float> {
    private static final String TAG = "CameraDistance";
    private static CameraDistanceProperty instance;

    private CameraDistanceProperty() {
        super(Float.class, TAG);
    }

    static Property<View, Float> getInstance() {
        return instance;
    }

    @Override // android.util.Property
    public void set(View view, Float f) {
        view.setCameraDistance(f.floatValue());
    }

    @Override // android.util.Property
    public Float get(View view) {
        return Float.valueOf(view.getCameraDistance());
    }
}
