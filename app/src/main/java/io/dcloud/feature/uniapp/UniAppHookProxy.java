package io.dcloud.feature.uniapp;

import android.app.Application;
import io.dcloud.weex.AppHookProxy;

/* loaded from: classes3.dex */
public interface UniAppHookProxy extends AppHookProxy {
    void onSubProcessCreate(Application application);
}
