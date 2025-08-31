package io.dcloud.feature.nativeObj;

import android.view.View;
import io.dcloud.common.DHInterface.IReflectAble;

/* loaded from: classes3.dex */
public interface INativeViewChildView extends IReflectAble {
    View obtainMainView();

    void updateLayout();
}
