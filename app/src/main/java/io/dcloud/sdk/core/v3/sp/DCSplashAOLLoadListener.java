package io.dcloud.sdk.core.v3.sp;

import android.view.View;
import android.widget.FrameLayout;
import io.dcloud.sdk.core.v3.base.DCBaseAOLLoadListener;

/* loaded from: classes3.dex */
public interface DCSplashAOLLoadListener extends DCBaseAOLLoadListener {
    void onSplashAOLLoad();

    void redBag(View view, FrameLayout.LayoutParams layoutParams);
}
