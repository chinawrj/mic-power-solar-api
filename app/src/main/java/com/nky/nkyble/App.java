package com.nky.nkyble;

import android.app.Application;
import io.dcloud.feature.uniapp.UniAppHookProxy;

/* loaded from: classes.dex */
public class App implements UniAppHookProxy {
    public static Application context;

    @Override // io.dcloud.feature.uniapp.UniAppHookProxy
    public void onSubProcessCreate(Application application) {
    }

    @Override // io.dcloud.weex.AppHookProxy
    public void onCreate(Application application) {
        context = application;
    }
}
