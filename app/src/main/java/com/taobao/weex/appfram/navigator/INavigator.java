package com.taobao.weex.appfram.navigator;

import android.app.Activity;

/* loaded from: classes.dex */
public interface INavigator {
    boolean pop(Activity activity, String str);

    boolean push(Activity activity, String str);
}
