package io.dcloud.common.DHInterface;

import android.app.Application;
import android.content.Context;

/* loaded from: classes3.dex */
public interface IUniInstanceMgr {
    void initUniappPlugin(Application application);

    void initWeexEnv(INativeAppInfo iNativeAppInfo);

    boolean isUniAppAssetsRes();

    void loadWeexToAppid(Context context, String str, boolean z);

    void onCreateProcess(Application application, Boolean bool);

    void registerUniappService(Context context, String str);

    void restartWeex(Application application, ICallBack iCallBack, String str);
}
