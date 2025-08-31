package com.taobao.weex;

@Deprecated
/* loaded from: classes.dex */
public interface IWXActivityStateListener {
    boolean onActivityBack();

    void onActivityCreate();

    void onActivityDestroy();

    void onActivityPause();

    void onActivityResume();

    void onActivityStart();

    void onActivityStop();
}
